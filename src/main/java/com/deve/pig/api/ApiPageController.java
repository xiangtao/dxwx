package com.deve.pig.api;

import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
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
}
