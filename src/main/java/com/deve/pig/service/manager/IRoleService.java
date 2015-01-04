package com.deve.pig.service.manager;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import my.utils.Pagination;

import com.deve.pig.model.Priv;
import com.deve.pig.model.Role;
import com.deve.pig.model.RoleExample;

public interface IRoleService {
    /**
* @Description:
* @param entity
*/
    int add(Role entity);

    /**
* @Description:
* @param entity
*/
    int delete(Role entity);

    /**
* @Description:
* @param entity
*/
    int update(Role entity);
    
    int update(Role role, Role oldRole);

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
    Role findById(long id);

    /**
* @Description:
*/
    List<Role> findAll();

    /**
* @Description:
*/
    List<Role> findByCriteria(RoleExample criteria);

    /**
* @Description: 获取导出到Excel的数据
*/
    @SuppressWarnings("unchecked")
    LinkedHashMap<String, List> getExportData();
    
    List<Priv> findPrivsByRoleId(Long id);
}