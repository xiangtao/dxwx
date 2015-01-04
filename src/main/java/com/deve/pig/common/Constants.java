package com.deve.pig.common;

/**
 * @organization: Microsoft
 * @author: tanyouzhong
 * @fileName: Constants.java
 * @package: com.deve.pig.common
 * @description: 通用常量类
 * @date: 2014年7月15日下午3:42:23
 * @modifyauthor: TODO
 * @modifydate: TODO
 * @modifycontent: TODO
 * @version: 1.0
 */
public class Constants
{
	/**
	 * @Description: 基础常量
	 * @Author: 谭又中
	 * @Since: 2014-7-15
	 */
	public static class Base
	{
		public static final int SUCCESS = 0;		// 成功
		public static final int FAIL = 1;			// 失败
		public static final int ERROR = 2;			// 错误
		public static final int REPEAT = 3;			// 重复
	}
	
	/**
	 * @Description: 任务状态
	 * @Author: 谭又中
	 * @Since: 2014-7-17
	 */
	public static class TaskStatus
	{
		/** 已发布 **/
		public static final short PUBLISHED = 0;
		/** 投标中 **/
		public static final short BIDDING = 1;
		/** 已中标 **/
		public static final short BADE = 2;
		/** 工作中 **/
		public static final short WORKING = 3;
		/** 已验收 **/
		public static final short ACCEPTED = 4;
		/** 已评价 **/
		public static final short APPRAISED = 5;
		/** 已失败 **/
		public static final short FAILED = 6;
	}
	
	/**
	 * @Description: 图片类型
	 * @Author: 谭又中
	 * @Since: 2014-7-17
	 */
	public static class PictureType
	{
		/** 用户相册 **/
		public static final short ALBUM = 0;
		/** 评价照片 **/
		public static final short APPRAISE = 1;
	}
	
	/**
	 * @Description: 用户身份认证状态
	 * @Author: 谭又中
	 * @Since: 2014-7-19
	 */
	public static class AuthStatus
	{
		/** 已通过 **/
		public static final short PASSED = 0;
		/** 审核中 **/
		public static final short VERIFING = 1;
		/** 未通过 **/
		public static final short UNPASS = 2;
	}
}