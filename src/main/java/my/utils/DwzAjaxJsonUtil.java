package my.utils;

import java.util.HashMap;
import java.util.Map;

/**
 *@Title:
 *@Description:
 *@Author:xt
 *@Since:2012-6-23
 *@Version:1.1.0
 */
public class DwzAjaxJsonUtil
{
	public static final String KEY_STATUSCODE = "statusCode";

	public static final String KEY_MESSAGE = "message";

	public static final String KEY_NAVTABID = "navTabId";

	public static final String KEY_DIALOGID = "dialogId";

	public static final String KEY_REL = "rel";

	public static final String KEY_CALLBACKTYPE = "callbackType";

	public static final String KEY_FORWARDURL = "forwardUrl";
	
	public static final int STATUS_CODE_300 = 300;
	

	public static final Map<String, Object> getDefaultAjaxJson()
	{
		Map<String, Object> json = new HashMap<String, Object>();
		json.put("statusCode", 200);
		json.put("message", "操作成功！");
		json.put("navTabId", "");
		json.put("dialogId", "");
		json.put("rel", "");
		json.put("callbackType", "closeCurrent");
		json.put("forwardUrl", "");
		return json;
	}
}
