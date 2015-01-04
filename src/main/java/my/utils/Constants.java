package my.utils;

/**
 * @author: tanyouzhong
 * @fileName: Constants.java
 * @package: com.tyz.axj.common
 * @description: 通用常量类
 * @date: 2014年5月19日下午4:21:31
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
	 * @Since: 2012-6-19
	 */
	public static class Base
	{
		public static final int SUCCESS = 0;		// 成功
		public static final int FAIL = 1;			// 失败
		public static final int ERROR = 2;			// 错误
		public static final int REPEAT = 3;			// 重复
	}

	/**
	 * @Description: 后台用户类型
	 * @Author: 谭又中
	 * @Since: 2014-07-06
	 */
	public static class UserType
	{
		/**
		 * @Description: 管理员
		 */
		public static final short ADMIN = 1;
		
		/**
		 * @Description: 领读人
		 */
		public static final short LEADER = 2;
	}
	
	/**
	 * @Description: 是否启用
	 * @Author: 谭又中
	 * @Since: 2014-07-06
	 */
	public static class IsEnabled
	{
		/**
		 * @Description: 未启用
		 */
		public static final byte UNABLED = 0;
		
		/**
		 * @Description: 已启用
		 */
		public static final byte ENABLED = 1;
	}
	
	/**
	 * @Description: 文献分类状态
	 * @Author: 谭又中
	 * @Since: 2014-07-07
	 */
	public static class CategoryStatus
	{
		/**
		 * @Description: 屏蔽
		 */
		public static final byte SHIELD = 0;
		
		/**
		 * @Description: 正常
		 */
		public static final byte NORMAL = 1;
	}
	
	/**
	 * @Description: 是否置顶
	 * @Author: 谭又中
	 * @Since: 2014-07-08
	 */
	public static class IsTop
	{
		/**
		 * @Description: 不置顶
		 */
		public static final byte NO = 0;
		
		/**
		 * @Description: 置顶
		 */
		public static final byte YES = 1;
	}
	
	/**
	 * @Description: 是否发布
	 * @Author: 谭又中
	 * @Since: 2014-07-08
	 */
	public static class IsPublish
	{
		/**
		 * @Description: 未发布
		 */
		public static final byte NO = 0;
		
		/**
		 * @Description: 已发布
		 */
		public static final byte YES = 1;
	}
	
	/**
	 * @Description: 录音状态
	 * @Author: 谭又中
	 * @Since: 2014-07-09
	 */
	public static class AudioStatus
	{
		/**
		 * @Description: 屏蔽
		 */
		public static final byte SHIELD = -1;
		
		/**
		 * @Description: 不置顶
		 */
		public static final byte UNTOP = 0;
		
		
		/**
		 * @Description: 置顶
		 */
		public static final byte TOP = 1;
	}
	
	/**
	 * @Description: 是否为阅读标兵
	 * @Author: 谭又中
	 * @Since: 2014-07-09
	 */
	public static class IsReadStandard
	{
		/**
		 * @Description: 否
		 */
		public static final byte NO = 0;
		
		/**
		 * @Description: 是
		 */
		public static final byte YES = 1;
	}
}