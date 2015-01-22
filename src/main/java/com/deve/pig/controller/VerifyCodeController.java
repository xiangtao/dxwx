package com.deve.pig.controller;

import java.awt.Color;
import java.io.ByteArrayOutputStream;

import javax.imageio.ImageIO;
import javax.imageio.stream.ImageOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import my.utils.CreateVerificationCode;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

//@Controller
@RequestMapping("/verify")
public class VerifyCodeController extends BaseController{
	
	private static final Log log = LogFactory.getLog(VerifyCodeController.class);
	
	// 保存服务器端生成的验证码值（由com.eatle.utils.CreateVerificationCode.java产生验证码图片时赋值）
	public static String verifyCode_s = "";
	
	@RequestMapping("/getVerifyCode")
	public void  getVerifyCode(HttpServletRequest request,HttpServletResponse response) throws Exception {
		// 验证码图片包装输出流
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		// 验证码图片输出流
		ImageOutputStream iops = ImageIO.createImageOutputStream(baos);
		// 将获取的验证码图片写到输出流
		ImageIO.write(CreateVerificationCode.getNumCheckCode(75, 25,
				new Color(255, 255, 255), new Color(255, 255, 255), 4, 0), "gif", iops);
		
		log.info("--------->>> Verify Code : " + verifyCode_s);
		
		// 将服务器端生成的验证码值存入Session
		request.getSession().setAttribute("verifyCode_s", verifyCode_s);
		response.getOutputStream().write(baos.toByteArray());
	}
}
