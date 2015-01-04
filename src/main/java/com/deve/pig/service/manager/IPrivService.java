package com.deve.pig.service.manager;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import my.utils.Pagination;

import com.deve.pig.model.Priv;
import com.deve.pig.model.PrivExample;
import com.deve.pig.model.PrivTree;

public interface IPrivService {
    /**
* @Description:
* @param entity
*/
    int add(Priv entity);

    /**
* @Description:
* @param entity
*/
    int delete(Priv entity);

    /**
* @Description:
* @param entity
*/
    int update(Priv entity);
    
    int update(Priv priv, Priv oldPiv);

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
    Priv findById(long id);

    /**
* @Description:
*/
    List<Priv> findAll();

    /**
* @Description:
*/
    List<Priv> findByCriteria(PrivExample criteria);

    /**
* @Description: 获取导出到Excel的数据
*/
    @SuppressWarnings("unchecked")
    LinkedHashMap<String, List> getExportData();
    
    /**
	 * @Description:
	 * 
	 * @return
	 */
	List<PrivTree> findPrivTree();
	
	public String findAllPrivMenu(String contextName, Map<String, Priv> allPrivs);
	
	public List<Priv> findRootPrivMenu();
}