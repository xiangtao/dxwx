package com.deve.pig.service.manager;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import my.utils.Pagination;

import com.deve.pig.model.Priv;
import com.deve.pig.model.RolePrivilege;
import com.deve.pig.model.RolePrivilegeExample;

public interface IRolePrivilegeService {
    /**
* @Description:
* @param entity
*/
    int add(RolePrivilege entity);

    /**
* @Description:
* @param entity
*/
    int delete(RolePrivilege entity);

    /**
* @Description:
* @param entity
*/
    int update(RolePrivilege entity);

    /**
* @Description:
* @param queryMap 查询参数
* @param currentPage 当前页
* @param pageSize 每页大小
*/
    Pagination findPagination(Map<String, Object> queryMap, int currentPage, int pageSize);

    /**
* @Description:
* @param id
*/
    RolePrivilege findById(long id);

    /**
* @Description:
*/
    List<RolePrivilege> findAll();

    /**
* @Description:
*/
    List<RolePrivilege> findByCriteria(RolePrivilegeExample criteria);

    /**
* @Description: 获取导出到Excel的数据
*/
    @SuppressWarnings("unchecked")
    LinkedHashMap<String, List> getExportData();
    
    
    /**
	 * @Description: 根据角色ID查询出所拥有的权限集合
	 * @param id 角色id
	 * @return: 该角色所拥有的所有权限集合
	 */
	Map<String, Priv> findPrivsByRoleId(Long id);
	
	/**
	 * @Description: 根据角色ID和权限ID删除
	 * @param roleId 角色ID
	 * @param privId 权限ID
	 */
	void deleteByRoleIdAndPrivId(Long roleId, Long privId);
}