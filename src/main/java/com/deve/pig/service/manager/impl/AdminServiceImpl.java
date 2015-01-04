package com.deve.pig.service.manager.impl;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import my.utils.Constants;
import my.utils.Pagination;

import org.springframework.stereotype.Service;

import com.deve.pig.mapper.AdminMapper;
import com.deve.pig.mapper.RoleMapper;
import com.deve.pig.model.Admin;
import com.deve.pig.model.AdminExample;
import com.deve.pig.model.AdminExample.Criteria;
import com.deve.pig.model.Role;
import com.deve.pig.service.manager.IAdminService;

@Service("adminService")
public class AdminServiceImpl implements IAdminService {
    @Resource
    private AdminMapper adminMapper;
    
    @Resource
	private RoleMapper roleMapper;

	@Resource(name = "userTypeHtml")
	private LinkedHashMap<String, String> userType;

    @Override
    public int add(Admin user) {
    	int result = Constants.Base.FAIL;
		
		AdminExample userCriteria = new AdminExample();
		Criteria criteria = userCriteria.createCriteria();
		criteria.andUserNameEqualTo(user.getUserName());
		List<Admin> users = adminMapper.selectByExample(userCriteria);
		if (users.size() < 1)
		{
			if(adminMapper.insert(user) > 0)
			{
				result = Constants.Base.SUCCESS;
			}
		}
		else
		{
			result = Constants.Base.REPEAT;
		}
		return result;
    }

    @Override
    public int delete(Admin entity) {
        return adminMapper.deleteByPrimaryKey(entity.getId());
    }

    @Override
    public int update(Admin entity) {
        return adminMapper.updateByPrimaryKeySelective(entity);
    }

    @Override
    public Pagination findPagination(Map<String, Object> queryMap, int currentPage, int pageSize) {
        AdminExample adminExample = new AdminExample();
        Criteria criteria = adminExample.createCriteria();
        // 设置搜索条件参数
	        if (queryMap != null){
				if (queryMap.containsKey("userName")){
					criteria.andUserNameLike("%" + queryMap.get("userName") + "%");
				}
				if (queryMap.containsKey("email")){
					criteria.andEmailLike("%" + queryMap.get("email") + "%");
				}
				if (queryMap.containsKey("type")){
					criteria.andTypeEqualTo(Byte.parseByte((String) queryMap.get("type")));
				}
				if (queryMap.containsKey("roleId")){
					criteria.andRoleIdEqualTo(Long.parseLong((String) queryMap.get("roleId")));
				}
			}
             //设置分页参数
            adminExample.setPageSize(pageSize);
            adminExample.setStartIndex((currentPage-1)*pageSize);
            List<Admin> items = adminMapper.selectByExample(adminExample);
            List<Role> roles = roleMapper.selectByExample(null);
    		for(Admin admin : items){
    			for(Role role : roles){
    				if(admin.getRoleId() == role.getId()){
    					admin.setRoleStr(role.getRoleName());
    					admin.setTypeStr(userType.get(admin.getType() + ""));
    					break;
    				}
    			}
    		}
    		
            int totalCount = (int)adminMapper.countByExample(adminExample);
            return new Pagination(pageSize, currentPage, totalCount, items);
     }

    @Override
    public Admin findById(long id) {
        return adminMapper.selectByPrimaryKey(id);
    }

    @Override
    public List<Admin> findAll() {
        return adminMapper.selectByExample(null);
    }

    @Override
    public List<Admin> findByCriteria(AdminExample criteria) {
        return adminMapper.selectByExample(criteria);
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
	public Admin find(Admin admin) {
		AdminExample example = new AdminExample();
		example.createCriteria().andUserNameEqualTo(admin.getUserName());
		List<Admin> lst = adminMapper.selectByExample(example);
		if(lst==null ||lst.size()<=0) return null;
		return lst.get(0);
	}
	
	@Override
	public int update(Admin user, Admin oldUser)
	{
		int result = Constants.Base.FAIL;
		
		AdminExample userCriteria = new AdminExample();
		Criteria criteria = userCriteria.createCriteria();
		criteria.andUserNameEqualTo(user.getUserName());
		List<Admin> users = adminMapper.selectByExample(userCriteria);
		if (users.size() < 1)
		{
			if(adminMapper.updateByPrimaryKeySelective(user) > 0)
			{
				result = Constants.Base.SUCCESS;
			}
		}
		else
		{
			if(users.get(0).getUserName().equals(oldUser.getUserName()))
			{
				if(adminMapper.updateByPrimaryKeySelective(user) > 0)
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
}