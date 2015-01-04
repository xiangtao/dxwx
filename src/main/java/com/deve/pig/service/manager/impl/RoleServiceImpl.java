package com.deve.pig.service.manager.impl;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import my.utils.Constants;
import my.utils.Pagination;

import org.springframework.stereotype.Service;

import com.deve.pig.mapper.PrivMapper;
import com.deve.pig.mapper.RoleMapper;
import com.deve.pig.model.Priv;
import com.deve.pig.model.Role;
import com.deve.pig.model.RoleExample;
import com.deve.pig.model.RoleExample.Criteria;
import com.deve.pig.service.manager.IRoleService;

@Service("roleService")
public class RoleServiceImpl implements IRoleService
{
	@Resource
	private RoleMapper roleMapper;

	@Resource
	private PrivMapper privMapper;

	@Override
	public int add(Role entity)
	{
		return roleMapper.insert(entity);
	}

	@Override
	public int delete(Role entity)
	{
		return roleMapper.deleteByPrimaryKey(entity.getId());
	}

	@Override
	public int update(Role entity)
	{
		return roleMapper.updateByPrimaryKeySelective(entity);
	}

	@Override
	public Pagination findPagination(Map<String, Object> queryMap,
			int currentPage, int pageSize)
	{
		RoleExample roleExample = new RoleExample();
		Criteria criteria = roleExample.createCriteria();
		// 设置搜索条件参数
		if (queryMap != null)
		{
			if (queryMap.containsKey("roleName"))
			{
				criteria.andRoleNameLike("%" + (String) queryMap.get("roleName") + "%");
			}
		}
		// 设置分页参数
		roleExample.setPageSize(pageSize);
		roleExample.setStartIndex((currentPage - 1) * pageSize);
		List<Role> items = roleMapper.selectByExample(roleExample);
		int totalCount = (int) roleMapper.countByExample(roleExample);
		return new Pagination(pageSize, currentPage, totalCount, items);
	}

	@Override
	public Role findById(long id)
	{
		return roleMapper.selectByPrimaryKey(id);
	}

	@Override
	public List<Role> findAll()
	{
		return roleMapper.selectByExample(null);
	}

	@Override
	public List<Role> findByCriteria(RoleExample criteria)
	{
		return roleMapper.selectByExample(criteria);
	}

	@Override
	@SuppressWarnings("unchecked")
	public LinkedHashMap<String, List> getExportData()
	{
		LinkedHashMap<String, List> map = new LinkedHashMap<String, List>();
		map.put("SheetName_1", findAll());
		map.put("SheetName_2", findAll());
		map.put("SheetName_3", findAll());
		map.put("SheetName_N", findAll());
		return map;
	}

	@Override
	public int update(Role role, Role oldRole)
	{
		int result = Constants.Base.FAIL;

		RoleExample roleCriteria = new RoleExample();
		Criteria criteria = roleCriteria.createCriteria();
		criteria.andRoleNameEqualTo(role.getRoleName());
		List<Role> roles = roleMapper.selectByExample(roleCriteria);
		if (roles.size() < 1)
		{
			if (roleMapper.updateByPrimaryKeySelective(role) > 0)
			{
				result = Constants.Base.SUCCESS;
			}
		}
		else
		{
			if (roles.get(0).getRoleName().equals(oldRole.getRoleName()))
			{
				if (roleMapper.updateByPrimaryKeySelective(role) > 0)
				{
					result = Constants.Base.SUCCESS;
				}
			}
			else
			{
				result = Constants.Base.REPEAT;
			}
		}
		return result;
	}

	@Override
	public List<Priv> findPrivsByRoleId(Long id)
	{
		return privMapper.selectByRoleId(id);
	}
}