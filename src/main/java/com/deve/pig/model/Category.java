package com.deve.pig.model;

import java.util.ArrayList;
import java.util.List;

public class Category
{
	private Integer id;

	private String name;

	private Byte status;

	private Integer pid;

	/** 临时属性 **/

	private String statusStr; // 启用状态字符串
	
	private List<Category> childs = new ArrayList<Category>();

	public Integer getId()
	{
		return id;
	}

	public void setId(Integer id)
	{
		this.id = id;
	}

	public String getName()
	{
		return name;
	}

	public void setName(String name)
	{
		this.name = name == null ? null : name.trim();
	}

	public Byte getStatus()
	{
		return status;
	}

	public void setStatus(Byte status)
	{
		this.status = status;
	}

	public Integer getPid()
	{
		return pid;
	}

	public void setPid(Integer pid)
	{
		this.pid = pid;
	}

	public String getStatusStr()
	{
		return statusStr;
	}

	public void setStatusStr(String statusStr)
	{
		this.statusStr = statusStr;
	}

	public List<Category> getChilds() {
		return childs;
	}

	public void setChilds(List<Category> childs) {
		this.childs = childs;
	}
}