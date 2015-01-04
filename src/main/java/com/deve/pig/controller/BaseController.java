package com.deve.pig.controller;

import java.io.IOException;
import java.io.Writer;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import my.utils.JacksonUtil;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.deve.pig.model.Admin;

public abstract class BaseController {
	
	private static final Log log = LogFactory.getLog(BaseController.class);
	
	protected Map<String,Object> jsonMap = new HashMap<String,Object>();
	
	/**
	 * 获取请求参数
	 */
	protected Map<String, Object> getRequestParameters(HttpServletRequest request) {
		Map<String, Object> map = new HashMap<String, Object>();
		Enumeration<?> parameters = request.getParameterNames();
		while (parameters.hasMoreElements()){
			String key = parameters.nextElement().toString();
			String value = request.getParameter(key);
			if (value != null && !"".equals(value)){
				map.put(key, value);
			}
		}
		return map;
	}
	
	protected HttpSession getSession(HttpServletRequest request) {
		return request.getSession();
	}
	
	protected Admin getAdmin(HttpServletRequest request) {
		return (Admin) request.getSession().getAttribute("user");
	}
	
	protected String getNavTabId(HttpServletRequest request) {
		return request.getParameter("navTabId");
	}
	
	protected String getDialogId(HttpServletRequest request) {
		return request.getParameter("dialogId");
	}
	
	protected void writeInResponse(String content, HttpServletResponse response) throws IOException {
		this.writeInResponse("text/json;charset=utf-8", content, response);
	}

	protected void writeInResponse(String contentType, String content, HttpServletResponse response)
			throws IOException {
		if (contentType == null || "".equals(contentType))
			contentType = "text/html;charset=utf-8";
		response.setContentType(contentType);
		response.setHeader("cache-control", "no-cache");
		response.setHeader("pragma", "no-cache");
		response.setHeader("expires", "0");
		Writer writer = response.getWriter();
		writer.write(content);
		log.info("--------->>> Response content : " + content);
		writer.close();
	}

	protected void writeMap(Map<?, ?> map, HttpServletResponse response) throws IOException {
		String json = JacksonUtil.toJson(map);
		writeInResponse(json, response);
	}

	protected void writeList(List<?> list, HttpServletResponse response)
			throws IOException {
		HashMap<String, Object> h = new HashMap<String, Object>();
		h.put("total", list.size());
		h.put("rows", list);
		String json = JacksonUtil.toJson(h);
		writeInResponse(json, response);
	}

	protected void writeObject(Object obj, HttpServletResponse response)
			throws IOException {
		String json = JacksonUtil.toJson(obj);
		writeInResponse(json, response);
	}
	
	public Map<String, Object> getJsonMap() {
		return jsonMap;
	}

	public void setJsonMap(Map<String, Object> jsonMap) {
		this.jsonMap = jsonMap;
	}
	
	
	protected List<MultipartFile>  getMultipartFiles(HttpServletRequest request) {
		List<MultipartFile> inputFiles = new ArrayList<MultipartFile>();
		MultipartFile inputFile = null;
		if(request instanceof MultipartHttpServletRequest){
			MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;
			java.util.Iterator<String> ite = multipartRequest.getFileNames();
			for(;ite.hasNext();){
			    inputFile = multipartRequest.getFile(ite.next());
			    inputFiles.add(inputFile);
			}
		}
		return inputFiles;
	}
	
	protected Object default_0_IfNull(Object src) {
		return defaultIfNull(src,0);
	}
	
	protected Object defaultIfNull(Object src,Object defaultValue) {
		if(src == null){
			return defaultValue;
		}else{
			return src;
		}
	}
	
}