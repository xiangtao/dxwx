package com.deve.pig.controller;

import java.util.Date;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import my.utils.Constants;
import my.utils.JacksonUtil;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.request.WebRequest;

import com.deve.pig.model.Admin;
import com.deve.pig.model.Priv;
import com.deve.pig.service.manager.IPrivService;


@Controller
@RequestMapping("/")
public class HomeController extends BaseController{
	
	private static final Log log = LogFactory.getLog(HomeController.class);
	
	@Resource
	private IPrivService privService;
	
	@ExceptionHandler({Exception.class})
	@ResponseBody
	public String handleException(Exception e, WebRequest request){
		log.error("----------->>> handleException Error", e);
		jsonMap.clear();
		jsonMap.put("st", Constants.Base.FAIL);
		jsonMap.put("msg", "System Failed:" + e.getMessage());
		String resp = JacksonUtil.toJson(jsonMap);
		log.info("--------->>> resp is " + resp);
		return resp;
	}
	/**
	 * 
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping("/home")
	public String home(HttpServletRequest request,HttpServletResponse response){
		log.info("--------->>> start home");
		// 当前登录用户
		Admin user = getAdmin(request); 

		// 当前登录用户所属角色拥有的权限集合
		Map<String, Priv> allPrivs = user.getAllPrivs();
		String contextPath = request.getContextPath();

		// 存入菜单HTML
		String allMenuHtml = privService.findAllPrivMenu(contextPath, allPrivs);
		request.setAttribute("allMenuHtml", allMenuHtml);
		
		// 当前日期
		request.setAttribute("currentDate", new Date());
		
		return "admin/index";
	}
	
	public void printProcessTime(long _startTime,String ctx){
	    long _endTime = System.currentTimeMillis();
	    log.info("----------->>> Process Time:" + (_endTime - _startTime) + "ms " + ctx);
	}
}
