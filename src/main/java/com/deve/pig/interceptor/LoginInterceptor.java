package com.deve.pig.interceptor;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import my.utils.DwzAjaxJsonUtil;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import com.deve.pig.controller.BaseController;


public class LoginInterceptor extends BaseController implements HandlerInterceptor{
	private static final Log log = LogFactory.getLog(LoginInterceptor.class);
	
	@Override
	public boolean preHandle(HttpServletRequest request,
			HttpServletResponse response, Object handler) throws Exception {
		String uri = request.getRequestURI().toLowerCase();
		Controller annoClass = handler.getClass().getAnnotation(Controller.class);
		log.info("--------->>> LoginInterceptor  uri=" + request.getRequestURI() + " annoClass=" + annoClass);
		if(uri.startsWith("/login") || uri.startsWith("/verify") || uri.startsWith("/api") || annoClass ==null){
			return true;
		}else{
			HttpSession session = request.getSession();
			// 请求来源URL
			String referer = request.getHeader("referer");
			if(session.getAttribute("user") == null){
				if(referer == null || "".equals(referer)
						|| referer.endsWith("nopower.jsp")){
					response.sendRedirect(request.getContextPath() + "/timeout.jsp");
				}else{
					Map<String, Object> json = DwzAjaxJsonUtil.getDefaultAjaxJson();
				    json.put(DwzAjaxJsonUtil.KEY_STATUSCODE, 301);
				    json.put("message", "登录超时，请您重新登录！");
				    writeMap(json,response);
				}
				return false;
			}else{
				return true;
			}
		}
	}

	@Override
	public void postHandle(HttpServletRequest request,
			HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
	}

	@Override
	public void afterCompletion(HttpServletRequest request,
			HttpServletResponse response, Object handler, Exception ex)
			throws Exception {
	}
	
	
	
	
}
