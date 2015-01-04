/**
 */
package my.utils;

import java.util.List;

/**
 *@Title: 封装分页对象
 *@Description:
 *@Author:xt
 *@Since:2012-6-22
 *@Version:1.1.0
 */
public class Pagination
{
	public static final int PAGESIZE = 15; 		// 每页默认条数

	public static final int CURRENTPAGE = 1;	// 默认当前页码

	private int pageSize = PAGESIZE; 			// 每页记录数

	private int currentPage = CURRENTPAGE; 		// 当前页号

	private int totalCount; 					// 总记录数

	private List<?> items; 						// 记录对象列表

	public Pagination()
	{
		super();
	}

	/**
	 * @param pageSize
	 * @param currentPage
	 * @param totalCount
	 * @param items
	 */
	public Pagination(int pageSize, int currentPage, int totalCount, List<?> items)
	{
		super();
		this.pageSize = pageSize;
		this.currentPage = currentPage;
		this.totalCount = totalCount;
		this.items = items;
	}

	public int getPageSize()
	{
		return pageSize;
	}

	public void setPageSize(int pageSize)
	{
		this.pageSize = pageSize;
	}

	public int getCurrentPage()
	{
		return currentPage;
	}

	public void setCurrentPage(int currentPage)
	{
		this.currentPage = currentPage;
	}

	public int getTotalCount()
	{
		return totalCount;
	}

	public void setTotalCount(int totalCount)
	{
		this.totalCount = totalCount;
	}

	public List<?> getItems()
	{
		return items;
	}

	public void setItems(List<?> items)
	{
		this.items = items;
	}
}
