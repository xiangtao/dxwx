package com.deve.pig.controller.admin;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import my.utils.Constants;
import my.utils.DwzAjaxJsonUtil;
import my.utils.Pagination;
import my.utils.TreeUtil;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.deve.pig.controller.BaseController;
import com.deve.pig.model.Priv;
import com.deve.pig.model.PrivTree;
import com.deve.pig.model.Role;
import com.deve.pig.model.RolePrivilege;
import com.deve.pig.service.manager.IPrivService;
import com.deve.pig.service.manager.IRolePrivilegeService;
import com.deve.pig.service.manager.IRoleService;

/**
 *@Title:
 *@Description:
 *@Author:xiangtao, tanyouzhong
 *@Since:2012-6-20
 *@Version:1.1.0
 */
@Controller
@RequestMapping("/role")
public class RoleController extends BaseController{

	@Resource
	private IRoleService roleService;

	@Resource
	private IPrivService privService;

	@Resource
	private IRolePrivilegeService rolePrivilegeService;

	private Pagination page;

	private Role role;

	private List<PrivTree> topPrivTrees;	// 系统存在的所有权限

	private List<Priv> havePrivs;			// 当前分配角色所拥有的权限

	private String allPrivHtml;				// 权限分配展示树HTML

	private String privsIds; 				// 为角色分配权限需要保存的权限的id集合

	/**
	 * @Description:显示首页
	 * 
	 * @return
	 */
	@RequestMapping("/showIndex")
	public String showIndex(Model model,HttpServletRequest request,HttpServletResponse response)
	{
		Map<String, Object> params = super.getRequestParameters(request);
		int pageNum = Pagination.CURRENTPAGE;
		int pageSize = Pagination.PAGESIZE;
		if (params.containsKey("pageNum"))
		{
			pageNum = Integer.parseInt((String) params.get("pageNum"));
		}
		if (params.containsKey("numPerPage"))
		{
			pageSize = Integer.parseInt((String) params.get("numPerPage"));
		}
		page = roleService.findPagination(params, pageNum, pageSize);

		model.addAttribute("page", page);
		
		return "admin/system/role/role";
	}

	@RequestMapping("/showAdd")
	public String showAdd()
	{
		return "admin/system/role/add";
	}

	@RequestMapping("/ajax/add")
	@ResponseBody
	public Map<String,Object> add(Role role,HttpServletRequest request,HttpServletResponse response) throws IOException
	{
		Map<String, Object> json = DwzAjaxJsonUtil.getDefaultAjaxJson();
		json.put(DwzAjaxJsonUtil.KEY_NAVTABID, getNavTabId(request));
		if (role == null)
		{
			json.put(DwzAjaxJsonUtil.KEY_STATUSCODE, 300);
			json.put(DwzAjaxJsonUtil.KEY_MESSAGE, "操作失败！");
		}
		else
		{
			int result = roleService.add(role);
			if(result == Constants.Base.REPEAT)
			{
				json.put(DwzAjaxJsonUtil.KEY_STATUSCODE, 300);
				json.put(DwzAjaxJsonUtil.KEY_MESSAGE, "角色已存在，请重新输入！");
			}
		}
		return json;
	}
	@RequestMapping("/ajax/delete")
	@ResponseBody
	public Map<String,Object> delete(Role role,HttpServletRequest request) throws IOException
	{
		Map<String, Object> json = DwzAjaxJsonUtil.getDefaultAjaxJson();
		json.put(DwzAjaxJsonUtil.KEY_NAVTABID, getNavTabId(request));
		json.put(DwzAjaxJsonUtil.KEY_CALLBACKTYPE, "");
		if (role == null)
		{
			json.put(DwzAjaxJsonUtil.KEY_STATUSCODE, 300);
			json.put(DwzAjaxJsonUtil.KEY_MESSAGE, "操作失败！");
		}
		else
		{
			if(roleService.delete(role) < 1)
			{
				json.put(DwzAjaxJsonUtil.KEY_STATUSCODE, 300);
				json.put(DwzAjaxJsonUtil.KEY_MESSAGE, "删除失败！");
			}
		}
		return json;
	}
	@RequestMapping("/showUpdate")
	public String showUpdate(Role role,Model model,HttpServletRequest request)
	{
		role = roleService.findById(role.getId());
		
		// 存入修改的角色，执行修改时查重使用
		getSession(request).setAttribute("oldRole", role);
		
		model.addAttribute("role", role);
		
		return "admin/system/role/update";
	}
	
	@RequestMapping("/ajax/update")
	@ResponseBody
	public Map<String,Object> update(Role role,Model model,HttpServletRequest request) throws IOException
	{
		Map<String, Object> json = DwzAjaxJsonUtil.getDefaultAjaxJson();
		json.put(DwzAjaxJsonUtil.KEY_NAVTABID, getNavTabId(request));
		if (role == null)
		{
			json.put(DwzAjaxJsonUtil.KEY_STATUSCODE, 300);
			json.put(DwzAjaxJsonUtil.KEY_MESSAGE, "操作失败！");
		}
		else
		{
			int result = roleService.update(role, (Role) getSession(request).getAttribute("oldRole"));
			if(result == Constants.Base.REPEAT)
			{
				json.put(DwzAjaxJsonUtil.KEY_STATUSCODE, 300);
				json.put(DwzAjaxJsonUtil.KEY_MESSAGE, "角色已存在，请重新输入！");
			}
			else if(result == Constants.Base.SUCCESS)
			{
				getSession(request).removeAttribute("oldRole");
			}
		}
		return json;
	}

	@RequestMapping("/showSetPriv")
	public String showSetPriv(Role role,Model model,HttpServletRequest request)
	{
		havePrivs = roleService.findPrivsByRoleId(role.getId());
		topPrivTrees = privService.findPrivTree();
		allPrivHtml = TreeUtil.getTreeString(topPrivTrees, havePrivs).toString();
		
		// 存入分配前拥有的权限ID集合
		List<Long> oldPrivIds = new ArrayList<Long>();
		for(Priv priv : havePrivs)
		{
			oldPrivIds.add(priv.getId());
		}
		getSession(request).setAttribute("oldPrivIds", oldPrivIds);
		
		model.addAttribute("havePrivs", havePrivs);
		model.addAttribute("topPrivTrees", topPrivTrees);
		model.addAttribute("allPrivHtml", allPrivHtml);
		
		
		return "admin/system/role/setRolePriv";
	}

	@RequestMapping("/ajax/setPriv")
	@ResponseBody
	public Map<String,Object> setPriv(Role role,String privsIds,Model model,HttpServletRequest request) throws IOException
	{
		Map<String, Object> json = DwzAjaxJsonUtil.getDefaultAjaxJson();
		if (privsIds == null || privsIds.equals(""))
		{
			json.put(DwzAjaxJsonUtil.KEY_STATUSCODE, 300);
			json.put(DwzAjaxJsonUtil.KEY_MESSAGE, "未选择权限，无需分配！");
		}
		else
		{
			// 获取分配前拥有的权限
			List<Long> oldPrivIds = (List<Long>) getSession(request).getAttribute("oldPrivIds");
			
			// 执行权限分配
			for (String id : privsIds.split(","))
			{
				// 新分配的权限中包含分配前已有的权限，则不做任何数据库动作，从分配前的权限集合中移除
				if(oldPrivIds.contains(Long.parseLong(id)))
				{
					oldPrivIds.remove(Long.parseLong(id));
				}
				else
				{
					// 新增分配前没有的权限
					RolePrivilege rp = new RolePrivilege();
					rp.setRoleId(role.getId());
					rp.setPrivId(Long.parseLong(id));
					rolePrivilegeService.add(rp);
				}
			}
			// 将分配前有的权限，然而分配后没有的权限删除掉
			for(Long privId : oldPrivIds)
			{
				rolePrivilegeService.deleteByRoleIdAndPrivId(role.getId(), privId);
			}
		}
		// 清理内存，移除分配前拥有的权限ID集合
		getSession(request).removeAttribute("oldPrivIds");
		
		return json;
	}

	public Pagination getPage()
	{
		return page;
	}

	public void setPage(Pagination page)
	{
		this.page = page;
	}

	public Role getRole()
	{
		return role;
	}

	public void setRole(Role role)
	{
		this.role = role;
	}

	public List<Priv> getHavePrivs()
	{
		return havePrivs;
	}

	public void setHavePrivs(List<Priv> havePrivs)
	{
		this.havePrivs = havePrivs;
	}

	public IRoleService getRoleService()
	{
		return roleService;
	}

	public void setRoleService(IRoleService roleService)
	{
		this.roleService = roleService;
	}

	public IPrivService getPrivService()
	{
		return privService;
	}

	public void setPrivService(IPrivService privService)
	{
		this.privService = privService;
	}

	public IRolePrivilegeService getRolePrivilegeService()
	{
		return rolePrivilegeService;
	}

	public void setRolePrivilegeService(
			IRolePrivilegeService rolePrivilegeService)
	{
		this.rolePrivilegeService = rolePrivilegeService;
	}

	public List<PrivTree> getTopPrivTrees()
	{
		return topPrivTrees;
	}

	public void setTopPrivTrees(List<PrivTree> topPrivTrees)
	{
		this.topPrivTrees = topPrivTrees;
	}

	public String getAllPrivHtml()
	{
		return allPrivHtml;
	}

	public void setAllPrivHtml(String allPrivHtml)
	{
		this.allPrivHtml = allPrivHtml;
	}

	public String getPrivsIds()
	{
		return privsIds;
	}

	public void setPrivsIds(String privsIds)
	{
		this.privsIds = privsIds;
	}
}
