package com.deve.pig.controller;

import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.deve.pig.model.Admin;
import com.deve.pig.service.manager.IAdminService;
import com.deve.pig.service.manager.IRolePrivilegeService;

//@Controller
@RequestMapping("/login")
public class LoginController extends BaseController{
	
	private static final Log log = LogFactory.getLog(HomeController.class);
	
	@Resource
    private IAdminService adminService;

    @Resource
    private IRolePrivilegeService rolePrivilegeService;
    
	@RequestMapping("/toLogin")
	public String toLogin(HttpServletRequest request,HttpServletResponse response) {
		log.info("--------->>> start toLogin");
		
		return "admin/login";
	}
	
	@RequestMapping("/ajax/login")
	@ResponseBody
	public Map<String, Object> login(Admin admin,String verifycode_c, HttpServletRequest request,HttpServletResponse response) {
		
		log.info("--------->>> start login");
		
		String verifycode_s = (String) getSession(request).getAttribute("verifyCode_s");
//		if(verifycode_c.equals(verifycode_s)) {
			Admin user = adminService.find(admin);
//			if(user == null) {
//				jsonMap.put("res", "0");
//			} else {
//				if(user.getPwd().equals(admin.getPwd())) {
					// 移除验证码
					getSession(request).removeAttribute("verifyCode_s");
					// 设置权限，存入登录信息
					user.setAllPrivs(rolePrivilegeService.findPrivsByRoleId(user.getRoleId()));
					getSession(request).setAttribute("user", user);
					// 验证结果
					jsonMap.put("res", "1");
					
					log.info("--------->>> [ " + user.getUserName() + " ] login");
//				} else {
//					jsonMap.put("res", "2");
//				}
//			}
//		} else {
//			jsonMap.put("res", "3");
//		}
		
		return jsonMap;
	}
	
	/**
     * @Description: 退出系统
     */
	@RequestMapping("/loginOut")
	public String loginOut(HttpServletRequest request) {
		// 移除user
		getSession(request).removeAttribute("user");
		
		return "redirect:/login/toLogin";
	}
}