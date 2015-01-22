package com.deve.pig.controller;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import my.utils.DateUtil;
import my.utils.DwzAjaxJsonUtil;
import my.utils.PropertiesFilesUtils;

import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.codehaus.jackson.JsonParseException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

/**
 * 文件上传控制器
 * @author taox
 */
//@Controller
@RequestMapping("/upload")
public class UploadController extends BaseController{
	
	private static final Log log = LogFactory.getLog(UploadController.class);
	
	private long allowFileMaxSize = 1024*1024*5;	// 5MB
	private String allowFileTypes = "jpg,png,jpeg,gif,mp3,spx";
	
	/**
	 * 文件上传
	 * @param request
	 * @param response
	 * @return
	 * @throws IOException 
	 * @throws JsonParseException 
	 */
	@RequestMapping(value="/doUpload",method=RequestMethod.POST)
	@ResponseBody
	public Map<String,Object> doupload(HttpServletRequest request,HttpServletResponse response){
		Map<String, Object> json = DwzAjaxJsonUtil.getDefaultAjaxJson();
		try {
			List<MultipartFile> inputFiles = getMultipartFiles(request);
			//校验文件大小和类型
			if(!doValidate(inputFiles,json)){
				json.put(DwzAjaxJsonUtil.KEY_CALLBACKTYPE, "");
				return json;
			}
			String baseStorePath = PropertiesFilesUtils.getInstance()
					.getProperty(PropertiesFilesUtils.defaultPropertiesFileName,"file_store_path");
			List<FileItem> fileItems = new ArrayList<FileItem>();
			for (MultipartFile multipartFile : inputFiles) {
				log.info("文件长度: " + multipartFile.getSize());  
				log.info("文件类型: " + multipartFile.getContentType());  
				log.info("文件名称: " + multipartFile.getName());  
				log.info("文件原名: " + multipartFile.getOriginalFilename()); 
				
				String prefixPath = getPrefixPath(multipartFile);
				String fileSuffix = StringUtils.substringAfterLast(multipartFile.getOriginalFilename(), ".");
				String fileRelativePath = prefixPath + "/" + UUID.randomUUID().toString()
						+ (StringUtils.isBlank(fileSuffix)?"":"."+ fileSuffix);
				//文件存放路径
				String storePath = baseStorePath + File.separator + fileRelativePath;
				FileUtils.copyInputStreamToFile(multipartFile.getInputStream(), new File(storePath));
				fileItems.add(new FileItem(multipartFile.getName(),multipartFile.getSize(),fileRelativePath));
				log.info("文件存放路径: " + storePath);
			}
			json.put("files", fileItems);
		}catch (Exception e) {
		    log.error("--------->>>error ",e);
		    json.put(DwzAjaxJsonUtil.KEY_STATUSCODE, DwzAjaxJsonUtil.STATUS_CODE_300);
		    json.put(DwzAjaxJsonUtil.KEY_MESSAGE, e.getMessage());
		}
		return json;
	}
	
	private boolean doValidate(List<MultipartFile> inputFiles,Map<String, Object> json) {
		if (inputFiles == null || inputFiles.size() < 1) {
			json.put(DwzAjaxJsonUtil.KEY_STATUSCODE, DwzAjaxJsonUtil.STATUS_CODE_300);
			json.put(DwzAjaxJsonUtil.KEY_MESSAGE, "请选择要上传的文件！");
			return false;
		}
		for (MultipartFile multipartFile : inputFiles) {
			long size = multipartFile.getSize();
			String fileSufix = StringUtils.substringAfterLast(multipartFile.getOriginalFilename(), ".");
			if(allowFileMaxSize<size){
				json.put(DwzAjaxJsonUtil.KEY_STATUSCODE, DwzAjaxJsonUtil.STATUS_CODE_300);
				json.put(DwzAjaxJsonUtil.KEY_MESSAGE, "文件大小受限，文件必须小于"+allowFileMaxSize/1024/1024+"MB！");
				return false;
			}
			if(StringUtils.isNotBlank(allowFileTypes)){
				String[] types = allowFileTypes.split(",");
				boolean isContain = Arrays.asList(types).contains(StringUtils.lowerCase(fileSufix));
				if(!isContain){
					json.put(DwzAjaxJsonUtil.KEY_STATUSCODE, DwzAjaxJsonUtil.STATUS_CODE_300);
					json.put(DwzAjaxJsonUtil.KEY_MESSAGE, "请上传" +allowFileTypes + "类型文件" );
					return false;
				}
			}
		}
		return true;
	}

	class FileItem{
		private String inputName;
		private long size;
		private String relativePath;
		
		public FileItem(String inputName, long size, String relativePath) {
			super();
			this.inputName = inputName;
			this.size = size;
			this.relativePath = relativePath;
		}
		
		public String getInputName() {
			return inputName;
		}
		public void setInputName(String inputName) {
			this.inputName = inputName;
		}
		public long getSize() {
			return size;
		}
		public void setSize(long size) {
			this.size = size;
		}

		public String getRelativePath() {
			return relativePath;
		}

		public void setRelativePath(String relativePath) {
			this.relativePath = relativePath;
		}
		
	}

	private String getPrefixPath(MultipartFile multipartFile) {
		String prefixName = "";
		// headerPic__head
		String iptName = multipartFile.getName();
		if(StringUtils.isNotBlank(iptName)){
			int idx = iptName.lastIndexOf("__");
			if(idx!=-1){
				String dirc = StringUtils.substring(iptName, idx+2);
				if(StringUtils.isNotBlank(dirc)){
					prefixName = dirc;
				}
			}
		}
		String[] times = DateUtil.getDate().split("\\-");
		String yyyyMMddPath = times[0] + "/" + times[1] +"/"+times[2];
		String prefixPath = StringUtils.isBlank(prefixName)?yyyyMMddPath:prefixName+"/"+yyyyMMddPath;
		return prefixPath;
	}
}
