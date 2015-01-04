package com.deve.pig.controller.admin;

import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import my.utils.Constants;
import my.utils.DwzAjaxJsonUtil;
import my.utils.Pagination;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.deve.pig.controller.BaseController;
import com.deve.pig.model.Admin;
import com.deve.pig.model.Role;
import com.deve.pig.service.manager.IAdminService;
import com.deve.pig.service.manager.IRoleService;


@Controller
@RequestMapping("/account")
public class AdminController extends BaseController{

	@Resource
	private IAdminService adminService;

	@Resource
	private IRoleService roleService;

	@Resource(name = "userTypeStr")
	private LinkedHashMap<String, String> userType;

	/**
	 * @Description:显示首页
	 * @return
	 */
	@RequestMapping("/showIndex")
	public String showIndex(HttpServletRequest request,Model model,HttpServletResponse response)
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
		Pagination page = adminService.findPagination(params, pageNum, pageSize);
		// 所有角色
		List<Role> allRole = roleService.findAll();
		
		model.addAttribute("page", page);
		model.addAttribute("allRole", allRole);
		model.addAttribute("userType", userType);

		return "admin/system/account/account";
	}

	@RequestMapping("/showAdd")
	public String showAdd(Model model){
		// 所有角色
		List<Role> allRole = roleService.findAll();
		model.addAttribute("allRole", allRole);
		// 用户类型
		model.addAttribute("userType", userType);
		return "admin/system/account/add";
	}

	
	@RequestMapping("/ajax/add")
	@ResponseBody
	public Map<String,Object> add(Admin user,HttpServletRequest request,HttpServletResponse response) throws IOException{
		Map<String, Object> json = DwzAjaxJsonUtil.getDefaultAjaxJson();
		json.put(DwzAjaxJsonUtil.KEY_NAVTABID, getNavTabId(request));
		if (user == null)
		{
			json.put(DwzAjaxJsonUtil.KEY_STATUSCODE, 300);
			json.put(DwzAjaxJsonUtil.KEY_MESSAGE, "操作失败！");
		}
		else
		{
			int result = adminService.add(user);
			if (result == Constants.Base.REPEAT)
			{
				json.put(DwzAjaxJsonUtil.KEY_STATUSCODE, 300);
				json.put(DwzAjaxJsonUtil.KEY_MESSAGE, "用户已存在，请重新输入！");
			}
		}
		return json;
	}

	@RequestMapping("/delete")
	@ResponseBody
	public Map<String,Object> delete(Admin user,HttpServletRequest request,HttpServletResponse response) throws IOException
	{
		Map<String, Object> json = DwzAjaxJsonUtil.getDefaultAjaxJson();
		json.put(DwzAjaxJsonUtil.KEY_NAVTABID, getNavTabId(request));
		json.put(DwzAjaxJsonUtil.KEY_CALLBACKTYPE, "");
		if (user == null)
		{
			json.put(DwzAjaxJsonUtil.KEY_STATUSCODE, 300);
			json.put(DwzAjaxJsonUtil.KEY_MESSAGE, "操作失败！");
		}
		else
		{
			if(user.getId() != 1)
			{
				if (adminService.delete(user) < 1)
				{
					json.put(DwzAjaxJsonUtil.KEY_STATUSCODE, 300);
					json.put(DwzAjaxJsonUtil.KEY_MESSAGE, "删除失败！");
				}
			}
			else
			{
				json.put(DwzAjaxJsonUtil.KEY_STATUSCODE, 300);
				json.put(DwzAjaxJsonUtil.KEY_MESSAGE, "系统管理员不允许删除！");
			}
		}
		return json;
	}

	@RequestMapping("/showUpdate")
	public String showUpdate(Admin user,Model model,HttpServletRequest request,HttpServletResponse response)
	{
		// 修改的用户
		user = adminService.findById(user.getId());
		// 所有角色
		List<Role> allRole = roleService.findAll();
		// 存入修改的用户，执行修改时查重使用
		getSession(request).setAttribute("oldUser", user);
		
		model.addAttribute("user", user);
		model.addAttribute("allRole", allRole);
		// 用户类型
		model.addAttribute("userType", userType);

		return "admin/system/account/update";
	}

	
	@RequestMapping("/update")
	@ResponseBody
	public Map<String,Object> update(Admin user,HttpServletRequest request,HttpServletResponse response) throws IOException
	{
		Map<String, Object> json = DwzAjaxJsonUtil.getDefaultAjaxJson();
		json.put(DwzAjaxJsonUtil.KEY_NAVTABID, getNavTabId(request));
		if (user == null)
		{
			json.put(DwzAjaxJsonUtil.KEY_STATUSCODE, 300);
			json.put(DwzAjaxJsonUtil.KEY_MESSAGE, "操作失败！");
		}
		else
		{
			int result = adminService.update(user, (Admin) getSession(request)
					.getAttribute("oldUser"));
			if (result == Constants.Base.REPEAT)
			{
				json.put(DwzAjaxJsonUtil.KEY_STATUSCODE, 300);
				json.put(DwzAjaxJsonUtil.KEY_MESSAGE, "用户已存在，请重新输入！");
			}
			else if (result == Constants.Base.SUCCESS)
			{
				getSession(request).removeAttribute("oldUser");
			}
		}
		return json;
	}

	/*public String showRelated()
	{
		Admin admin = ((Admin) session.get("user"));
		// 根据商家ID获取相应门店
		RestaurantCriteria rc = new RestaurantCriteria();
		Criteria criteria1 = rc.createCriteria();
		criteria1.andBelongidEqualTo(admin.getMerchantId());
		List<Restaurant> storeList = storeService.findByCriteria(rc);
		// 对可管理门店处理，将可管理的门店标记，迭代展示时使用
		userMerchant = userMerchantService.selectUserMerchantByUserIdAndMerchantId(user.getId(), admin.getMerchantId());
		if(userMerchant != null)
		{
			List<String> ids = Arrays.asList(userMerchant.getManageRestaurantList().split(","));
			for(Restaurant r : storeList)
			{
				if(ids.contains(r.getId()))
				{
					r.setIsManaged(true);
				}
			}
		}
		request.setAttribute("storeList", storeList);
		return "showRelated";
	}

	public void related() throws IOException
	{
		Map<String, Object> json = DwzAjaxJsonUtil.getDefaultAjaxJson();
		json.put(DwzAjaxJsonUtil.KEY_NAVTABID, navTabId);
		if (userMerchant == null)
		{
			json.put(DwzAjaxJsonUtil.KEY_STATUSCODE, 300);
			json.put(DwzAjaxJsonUtil.KEY_MESSAGE, "操作失败！");
		}
		else
		{
			Admin admin = ((Admin) session.get("user"));
			UserMerchant um = userMerchantService.selectUserMerchantByUserIdAndMerchantId(userMerchant.getUserId(), admin.getMerchantId());
			if(um != null)	// 存在关联，更新
			{
				um.setManageRestaurantList(StringUtil.trim(userMerchant.getManageRestaurantList()));
				userMerchantService.update(um);
			}
			else	// 不存在，新增关联
			{
				userMerchant.setManageRestaurantList(StringUtil.trim(userMerchant.getManageRestaurantList()));
				userMerchant.setMerchantId(admin.getMerchantId());
				userMerchantService.add(userMerchant);
			}
		}
		super.writeMap(json);
	}*/

}