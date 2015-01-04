package com.deve.pig.service.manager.impl;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import my.utils.Constants;
import my.utils.Pagination;

import org.springframework.stereotype.Service;

import com.deve.pig.mapper.PrivMapper;
import com.deve.pig.model.Priv;
import com.deve.pig.model.PrivExample;
import com.deve.pig.model.PrivExample.Criteria;
import com.deve.pig.model.PrivTree;
import com.deve.pig.service.manager.IPrivService;

@Service("privService")
public class PrivServiceImpl implements IPrivService {
    @Resource
    private PrivMapper privMapper;

    
    private Map<String, Priv> allPrivs;
    
    @Override
    public int add(Priv entity) {
        return privMapper.insert(entity);
    }

    @Override
    public int delete(Priv entity) {
        return privMapper.deleteByPrimaryKey(entity.getId());
    }

    @Override
    public int update(Priv entity) {
        return privMapper.updateByPrimaryKeySelective(entity);
    }

    @Override
    public Pagination findPagination(Map<String, Object> queryMap, int currentPage, int pageSize) {
        PrivExample privExample = new PrivExample();
        Criteria criteria = privExample.createCriteria();
        if (queryMap != null)
		{
			if (queryMap.containsKey("privName"))
			{
				criteria.andPrivNameLike("%" + (String) queryMap.get("privName") + "%");
			}
			if (queryMap.containsKey("actionCmd"))
			{
				criteria.andActionLike("%" + (String) queryMap.get("actionCmd") + "%");
			}
			if (queryMap.containsKey("pidisnull"))
			{
				criteria.andPIdIsNull();
			}
		}
        // 设置分页参数
        privExample.setPageSize(pageSize);
        privExample.setStartIndex((currentPage-1)*pageSize);
        List<Priv> items = privMapper.selectByExample(privExample);
        int totalCount = (int)privMapper.countByExample(privExample);
        return new Pagination(pageSize, currentPage, totalCount, items);
    }

    @Override
    public Priv findById(long id) {
        return privMapper.selectByPrimaryKey(id);
    }

    @Override
    public List<Priv> findAll() {
        return privMapper.selectByExample(null);
    }

    @Override
    public List<Priv> findByCriteria(PrivExample criteria) {
        return privMapper.selectByExample(criteria);
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
	public List<PrivTree> findPrivTree()
	{
		List<Priv> list = privMapper.selectByExample(new PrivExample());
		List<PrivTree> ptList = new ArrayList<PrivTree>();
		if (list == null)
			return null;
		for (int i = 0; i < list.size(); i++)
		{
			Priv pri = list.get(i);
			if (pri.getPid() == null)
			{
				ptList.add(findAllChildPrivs(pri, null, list));
			}
		}
		return ptList;
	}

	@SuppressWarnings("unused")
	private Priv findParentPriv(Priv priv, List<Priv> list)
	{
		for (int i = 0; i < list.size(); i++)
		{
			Priv pri = list.get(i);
			if (priv.getPid() == pri.getId())
			{
				return pri;
			}
		}
		return null;
	}

	private PrivTree findAllChildPrivs(Priv cur, PrivTree ptree, List<Priv> list)
	{
		PrivTree topTree = new PrivTree();
		topTree.setParent(ptree);
		topTree.setPriv(cur);

		List<PrivTree> temp = new ArrayList<PrivTree>();
		for (int i = 0; i < list.size(); i++)
		{
			Priv pri = list.get(i);
			if (cur.getId() == pri.getPid())
			{
				temp.add(findAllChildPrivs(pri, topTree, list));
			}
		}
		if (temp.size() == 0)
		{
			topTree.setChildPrivs(null);
		}
		else
		{
			topTree.setChildPrivs(temp);
		}
		return topTree;
	}

	@SuppressWarnings("unused")
	private List<Priv> findChildPrivs(Priv priv, List<Priv> list)
	{
		List<Priv> temp = new ArrayList<Priv>();
		for (int i = 0; i < list.size(); i++)
		{
			Priv pri = list.get(i);
			if (priv.getId() == pri.getPid())
			{
				temp.add(pri);
			}
		}
		return temp;
	}
	
	@Override
	public String findAllPrivMenu(String contextName, Map<String, Priv> allPrivs)
	{
		this.allPrivs = allPrivs;
		StringBuffer allMenuBuffer = new StringBuffer();
		for(Priv priv : findRootPrivMenu())
		{
			if(allPrivs.get(priv.getAction()) != null)
			{
				assembleRootPrivMenu(priv, allMenuBuffer);
				assembleChildPrivMenu(priv, allMenuBuffer,contextName);
			}
		}
		return allMenuBuffer.toString();
	}
	
	@Override
	public List<Priv> findRootPrivMenu()
	{
		PrivExample privCriteria = new PrivExample();
		Criteria criteria = privCriteria.createCriteria();
		criteria.andIsShowEqualTo((byte)1);
		criteria.andPIdIsNull();
		return findByCriteria(privCriteria);
	}
	
	public void assembleRootPrivMenu(Priv priv, StringBuffer allMenuBuffer)
	{
		allMenuBuffer.append("<div class=\"accordionHeader\">\n");
		allMenuBuffer.append("<h2><span>Folder</span>" + priv.getMenuName() + "</h2>\n");
		allMenuBuffer.append("</div>\n");
		allMenuBuffer.append("<div class=\"accordionContent\">\n");
	}
	public void assembleChildPrivMenu(Priv priv, StringBuffer allMenuBuffer, String contextName)
	{
		allMenuBuffer.append("<ul class=\"tree treeFolder collapse\">\n");
		
		PrivExample privCriteria = new PrivExample();
		Criteria criteria = privCriteria.createCriteria();
		criteria.andPIdEqualTo(priv.getId());
		criteria.andIsShowEqualTo((byte)1);
		findChildPrivMenu(findByCriteria(privCriteria), allMenuBuffer,contextName);
		allMenuBuffer.append("</ul>\n");
		allMenuBuffer.append("</div>\n");
	}
	
	public void findChildPrivMenu(List<Priv> parentPrivMenu, StringBuffer allMenuBuffer, String contextName)
	{
		int childMenuSize = 0;
		for(Priv priv : parentPrivMenu)
		{
			if(allPrivs.get(priv.getAction()) != null)
			{
				PrivExample privCriteria = new PrivExample();
				Criteria criteria = privCriteria.createCriteria();
				criteria.andPIdEqualTo(priv.getId());
				criteria.andIsShowEqualTo((byte)1);
				List<Priv> childPrivMenu = findByCriteria(privCriteria);
				childMenuSize = childPrivMenu.size();
				if(childMenuSize > 0)
				{
					allMenuBuffer.append("<li><a>" + priv.getMenuName() + "</a>\n<ul>\n");
					findChildPrivMenu(childPrivMenu, allMenuBuffer,contextName);
				}
				else
				{
					if(priv.getAction().contains("category/list/showAdd.html"))	// 添加产品分类
						allMenuBuffer.append("<li><a href=\"" + contextName+"/"+priv.getAction() + "?dialogId=" + priv.getId() + "&action=" 
								+ priv.getAction() + "\" target=\"dialog\" resizable=\"false\" mask=\"true\" maxable=\"false\" width=\"520\" height=\"280\" rel=\"" 
								+ priv.getId() + "\">" + priv.getMenuName() + "</a></li>\n");
					else if(priv.getAction().contains("product/list/showAdd.html"))	// 添加产品
						allMenuBuffer.append("<li><a href=\"" + contextName+"/"+priv.getAction() + "?dialogId=" + priv.getId() + "&action=" 
								+ priv.getAction() + "\" target=\"dialog\" resizable=\"false\" mask=\"true\" maxable=\"false\" width=\"780\" height=\"450\" rel=\"" 
								+ priv.getId() + "\">" + priv.getMenuName() + "</a></li>\n");
					else if(priv.getAction().contains("store/list/showAdd.html"))	// 添加门店
						allMenuBuffer.append("<li><a href=\"" + contextName+"/"+priv.getAction() + "?dialogId=" + priv.getId() + "&action=" 
								+ priv.getAction() + "\" target=\"dialog\" resizable=\"false\" mask=\"true\" maxable=\"false\" width=\"780\" height=\"350\" rel=\"" 
								+ priv.getId() + "\">" + priv.getMenuName() + "</a></li>\n");
					else
						allMenuBuffer.append("<li><a href=\"" + contextName+"/"+priv.getAction() + "?navTabId=" + priv.getId() 
								+ "&action=" + priv.getAction() + "\" target=\"navTab\" rel=\"" + priv.getId()
								+ "\">" + priv.getMenuName() + "</a></li>\n");
				}
			}
		}
		allMenuBuffer.append("</ul>\n</li>\n");
	}

	@Override
	public int update(Priv priv, Priv oldPiv) {
		int result = Constants.Base.FAIL;
		if(priv.getIsShow() == null)
			priv.setIsShow((byte) 0);
		PrivExample privCriteria = new PrivExample();
		Criteria criteria = privCriteria.createCriteria();
		criteria.andActionEqualTo(priv.getAction());
		List<Priv> privs = privMapper.selectByExample(privCriteria);
		if (privs.size() < 1)
		{
			if(privMapper.updateByPrimaryKeySelective(priv) > 0)
			{
				result = Constants.Base.SUCCESS;
			}
		}
		else
		{
			if(privs.get(0).getAction().equals(oldPiv.getAction()))
			{
				if(privMapper.updateByPrimaryKeySelective(priv) > 0)
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