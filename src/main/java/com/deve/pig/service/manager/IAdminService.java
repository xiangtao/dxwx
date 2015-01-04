package com.deve.pig.service.manager;

import com.deve.pig.model.Admin;
import com.deve.pig.model.AdminExample;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import my.utils.Pagination;

public interface IAdminService
{


	/**
	 * @Description:
	 * @param entity
	 */
	int add(Admin entity);

	/**
	 * @Description:
	 * @param entity
	 */
	int delete(Admin entity);

	/**
	 * @Description:
	 * @param entity
	 */
	int update(Admin entity);

	int update(Admin user, Admin oldUser);

	/**
	 * @Description:
	 * @param queryMap 查询参数
	 * @param currentPage 当前页
	 * @param pageSize 每页大小
	 */
	Pagination findPagination(Map<String, Object> queryMap, int currentPage,
			int pageSize);

	/**
	 * @Description:
	 * @param id
	 */
	Admin findById(long id);

	/**
	 * @Description:
	 */
	List<Admin> findAll();

	/**
	 * @Description:
	 */
	List<Admin> findByCriteria(AdminExample criteria);

	/**
	 * @Description: 获取导出到Excel的数据
	 */
	@SuppressWarnings("unchecked")
	LinkedHashMap<String, List> getExportData();

	Admin find(Admin admin);
}