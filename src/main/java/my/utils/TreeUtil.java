package my.utils;

import java.util.List;

import com.deve.pig.model.Priv;
import com.deve.pig.model.PrivTree;

/**
 *@Title:
 *@Description: 角色权限分配权限树展示HTML拼装工具
 *@Author: xiangtao, tanyouzhong
 *@Since: 2012-6-24
 *@Version: 1.1.0
 */
public class TreeUtil
{
	public static StringBuilder getTreeString(List<PrivTree> topTree)
	{
		StringBuilder sb = new StringBuilder("");
		if (topTree == null)
			return sb;
		for (int i = 0; i < topTree.size(); i++)
		{
			PrivTree pt = topTree.get(i);
			sb.append(outputStr(pt));
		}
		return sb;
	}

	public static StringBuilder getTreeString(List<PrivTree> topTree,
			List<Priv> hasPrivs)
	{
		StringBuilder sb = new StringBuilder("");
		if (topTree == null)
			return sb;
		for (int i = 0; i < topTree.size(); i++)
		{
			PrivTree pt = topTree.get(i);
			sb.append(outputStr(pt, hasPrivs));
		}
		return sb;
	}

	/**
	 * @Description:
	 * 
	 * @param pt
	 * @return
	 */
	private static String outputStr(PrivTree pt)
	{
		StringBuilder sb = new StringBuilder();
		sb.append("<li>");
		sb.append("<a tname=\"priv\" tvalue=\"" + pt.getPriv().getId() + "\">");
		sb.append(pt.getPriv().getPrivName());
		sb.append("</a>");
		List<PrivTree> child = pt.getChildPrivs();
		if (child != null)
		{
			for (int i = 0; i < child.size(); i++)
			{
				sb.append("<ul>");
				sb.append(outputStr(child.get(i)));
				sb.append("</ul>");
			}
		}
		sb.append("</li>");
		return sb.toString();
	}

	private static String outputStr(PrivTree pt, List<Priv> hasPrivs)
	{
		StringBuilder sb = new StringBuilder();
		sb.append("<li>");
		sb.append("<a tname=\"priv\" tvalue=\"" + pt.getPriv().getId() + "\" ");
		if ((hasPrivs == null ? false : hasPrivs.contains(pt.getPriv())))
			sb.append("checked=\"true\"");
		sb.append(">");
		sb.append(pt.getPriv().getPrivName());
		sb.append("</a>");
		List<PrivTree> child = pt.getChildPrivs();
		if (child != null)
		{
			for (int i = 0; i < child.size(); i++)
			{
				sb.append("<ul>");
				sb.append(outputStr(child.get(i), hasPrivs));
				sb.append("</ul>");
			}
		}
		sb.append("</li>");
		return sb.toString();
	}
}
