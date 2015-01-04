package com.deve.pig.service.manager.impl;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import my.utils.Pagination;

import org.springframework.stereotype.Service;

import com.deve.pig.mapper.RolePrivilegeMapper;
import com.deve.pig.model.Priv;
import com.deve.pig.model.RolePrivilege;
import com.deve.pig.model.RolePrivilegeExample;
import com.deve.pig.model.RolePrivilegeExample.Criteria;
import com.deve.pig.service.manager.IRolePrivilegeService;

@Service("rolePrivilegeService")
public class RolePrivilegeServiceImpl implements IRolePrivilegeService {
    @Resource
    private RolePrivilegeMapper rolePrivilegeMapper;

    @Override
    public int add(RolePrivilege entity) {
        return rolePrivilegeMapper.insert(entity);
    }

    @Override
    public int delete(RolePrivilege entity) {
        return rolePrivilegeMapper.deleteByPrimaryKey(entity.getId());
    }

    @Override
    public int update(RolePrivilege entity) {
        return rolePrivilegeMapper.updateByPrimaryKeySelective(entity);
    }

    @Override
    public Pagination findPagination(Map<String, Object> queryMap, int currentPage, int pageSize) {
        RolePrivilegeExample rolePrivilegeExample = new RolePrivilegeExample();
        //Criteria criteria = rolePrivilegeExample.createCriteria();
        // 设置搜索条件参数
        //if(queryMap != null){
            //if(queryMap.containsKey("username")) {
                //criteria.andUserNameLike("%"+(String)queryMap.get("username")+"%");
                //}
                //if(queryMap.containsKey("email")){
                    //criteria.andEmailLike((String)queryMap.get("email"));
                    //}
                    //}
                    // 设置分页参数
                    rolePrivilegeExample.setPageSize(pageSize);
                    rolePrivilegeExample.setStartIndex((currentPage-1)*pageSize);
                    List<RolePrivilege> items = rolePrivilegeMapper.selectByExample(rolePrivilegeExample);
                    int totalCount = (int)rolePrivilegeMapper.countByExample(rolePrivilegeExample);
                    return new Pagination(pageSize, currentPage, totalCount, items);
                }

    @Override
    public RolePrivilege findById(long id) {
        return rolePrivilegeMapper.selectByPrimaryKey(id);
    }

    @Override
    public List<RolePrivilege> findAll() {
        return rolePrivilegeMapper.selectByExample(null);
    }

    @Override
    public List<RolePrivilege> findByCriteria(RolePrivilegeExample criteria) {
        return rolePrivilegeMapper.selectByExample(criteria);
    }

    @Override
    @SuppressWarnings("unchecked")
    public LinkedHashMap<String, List> getExportData() {
        LinkedHashMap<String, List> map = new LinkedHashMap<String, List>();
        map.put("SheetName_1", findAll());
        map.put("SheetName_2", findAll());
        map.put("SheetName_3", findAll());
        map.put("SheetName_N", findAll());
        return map;
    }

	@Override
	public Map<String, Priv> findPrivsByRoleId(Long id) {
		Map<String, Priv> privsMap = new HashMap<String, Priv>();
		for(Priv priv : rolePrivilegeMapper.selectPrivsByRoleId(id))
		{
			privsMap.put(priv.getAction(), priv);
		}
		
		return privsMap;
	}

	@Override
	public void deleteByRoleIdAndPrivId(Long roleId, Long privId) {
		RolePrivilegeExample rpCriteria = new RolePrivilegeExample();
		Criteria criteria = rpCriteria.createCriteria();
		criteria.andRoleIdEqualTo(roleId);
		criteria.andPrivIdEqualTo(privId);
		
		rolePrivilegeMapper.deleteByExample(rpCriteria);
		
	}
}