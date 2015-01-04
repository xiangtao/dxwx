package com.deve.pig.common;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Timer;

import javax.annotation.Resource;

/**
 * @organization: Microsoft
 * @author: tanyouzhong
 * @fileName: BusinessData.java
 * @package: com.deve.pig.common
 * @description: 公共业务数据
 * @date: 2014年7月15日下午3:42:34
 * @modifyauthor: TODO
 * @modifydate: TODO
 * @modifycontent: TODO
 * @version: 1.0
 */
public class BusinessData
{
	/** 短信验证码存储器 **/
	protected static final HashMap<String, String> verifyCodeMap = new HashMap<String, String>();
	/** 短信验证码消除器 **/
	protected static final HashMap<String, Timer> verifyCodeTimerMap = new HashMap<String, Timer>();
	
	/** 启用状态 **/
	@Resource(name = "enableStr")
	protected LinkedHashMap<String, String> enableStr;
	@Resource(name = "enableHtml")
	protected LinkedHashMap<String, String> enableHtml;
}