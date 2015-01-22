package com.deve.pig.api;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import my.utils.HttpclientUtils;
import my.utils.JacksonUtil;
import my.utils.PropertiesFilesUtils;

import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.deve.pig.controller.BaseController;

@Controller
@RequestMapping("/api/dxwx")
@Scope(BeanDefinition.SCOPE_PROTOTYPE)
public class ApiPageController extends BaseController{
   
	@RequestMapping("/showView")
	public String showView(){
		return "api/cgview";
	}
	
	@RequestMapping("/showIntro")
	public String showIntro(){
		return "api/cgintroduce";
	}
	
	
	@RequestMapping("/showIntroDetail")
	public String showIntroDetail(String code,Model model) {
		try {
			Map<String,String> headerMap = new HashMap<String,String>();
			headerMap.put(HttpclientUtils.CONTENT_TYPE,HttpclientUtils.CONTENT_TYPE_VALUE);
			String getUrl = PropertiesFilesUtils.getInstance().getPropertyNoCache(PropertiesFilesUtils.defaultPropertiesFileName
					, "get_url");
			getUrl = getUrl + "/" +  code ;
			String rsp = HttpclientUtils.sendHttpReqUseGet(getUrl, headerMap, null);
			Map<String, Object> jmap = JacksonUtil.parseToMap(rsp);
			List list = (List)jmap.get("obj");
			Map<String, Object> cmap = (Map)list.get(0);
			model.addAttribute("content", cmap.get("content"));
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return "api/cgintroduceDetail";
	}
}
