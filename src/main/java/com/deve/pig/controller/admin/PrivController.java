package com.deve.pig.controller.admin;

import java.io.IOException;
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
import com.deve.pig.model.Priv;
import com.deve.pig.model.PrivExample;
import com.deve.pig.model.PrivExample.Criteria;
import com.deve.pig.service.manager.IPrivService;

@Controller
@RequestMapping("/priv")
public class PrivController extends BaseController{

	@Resource
	private IPrivService privService;

	private Pagination page;

	private Priv priv;

	private List<Priv> list;

	/**
	 * @Description:显示首页
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
		params.put("pidisnull", null);
		page = privService.findPagination(params, pageNum, pageSize);

		model.addAttribute("page", page);
		
		return "admin/system/priv/priv";
	}

	@RequestMapping("/showAdd")
	public String showAdd(){
		
		return "admin/system/priv/add";
	}

	@RequestMapping("/ajax/add")
	@ResponseBody
	public Map<String,Object> add(Priv priv, HttpServletRequest request, HttpServletResponse response) throws IOException
	{
		Map<String, Object> json = DwzAjaxJsonUtil.getDefaultAjaxJson();
		json.put(DwzAjaxJsonUtil.KEY_NAVTABID, getNavTabId(request));
		json.put(DwzAjaxJsonUtil.KEY_DIALOGID, getDialogId(request));
		if (priv == null)
		{
			json.put(DwzAjaxJsonUtil.KEY_STATUSCODE, 300);
			json.put(DwzAjaxJsonUtil.KEY_MESSAGE, "操作失败！");
		}
		else
		{
			int result = privService.add(priv);
			if(result == Constants.Base.REPEAT)
			{
				json.put(DwzAjaxJsonUtil.KEY_STATUSCODE, 300);
				json.put(DwzAjaxJsonUtil.KEY_MESSAGE, "动作指令已存在，请重新输入！");
			}
			json.put("pid", priv.getPid());
		}
		return json;
	}

	@RequestMapping("/ajax/delete")
	@ResponseBody
	public Map<String,Object> delete(Priv priv,HttpServletRequest request,HttpServletResponse response) throws IOException
	{
		Map<String, Object> json = DwzAjaxJsonUtil.getDefaultAjaxJson();
		json.put(DwzAjaxJsonUtil.KEY_NAVTABID, getNavTabId(request));
		json.put(DwzAjaxJsonUtil.KEY_DIALOGID, getDialogId(request));
		json.put(DwzAjaxJsonUtil.KEY_CALLBACKTYPE, "");
		if (priv == null){
			json.put(DwzAjaxJsonUtil.KEY_STATUSCODE, 300);
			json.put(DwzAjaxJsonUtil.KEY_MESSAGE, "操作失败！");
		}
		else{
			if(privService.delete(priv) < 1){
				json.put(DwzAjaxJsonUtil.KEY_STATUSCODE, 300);
				json.put(DwzAjaxJsonUtil.KEY_MESSAGE, "删除失败！");
			}
		}
		return json;
	}

	@RequestMapping("/showUpdate")
	public String showUpdate(Priv priv,Model model,HttpServletRequest request)
	{
		priv = privService.findById(priv.getId());
		
		// 存入修改的权限，执行修改时查重使用
		getSession(request).setAttribute("oldPriv", priv);
		model.addAttribute("priv", priv);
		
		return "admin/system/priv/update";
	}

	@RequestMapping("/ajax/update")
	@ResponseBody
	public Map<String,Object> update(Priv priv,Model model,HttpServletRequest request) throws IOException
	{
		Map<String, Object> json = DwzAjaxJsonUtil.getDefaultAjaxJson();
		json.put(DwzAjaxJsonUtil.KEY_NAVTABID, getNavTabId(request));
		json.put(DwzAjaxJsonUtil.KEY_DIALOGID, getDialogId(request));
		if (priv == null)
		{
			json.put(DwzAjaxJsonUtil.KEY_STATUSCODE, 300);
			json.put(DwzAjaxJsonUtil.KEY_MESSAGE, "操作失败！");
		}
		else
		{
			int result = privService.update(priv, (Priv) getSession(request).getAttribute("oldPriv"));
			if(result == Constants.Base.REPEAT)
			{
				json.put(DwzAjaxJsonUtil.KEY_STATUSCODE, 300);
				json.put(DwzAjaxJsonUtil.KEY_MESSAGE, "动作指令已存在，请重新输入！");
			}
			else if(result == Constants.Base.SUCCESS)
			{
				getSession(request).removeAttribute("oldPriv");
			}
		}
		return json;
	}

	@RequestMapping("/showSetSubPriv")
	public String showSetSubPriv(Model model,HttpServletRequest request)
	{
		Map<String, Object> params = super.getRequestParameters(request);
		Object pid = params.get("pid");
		PrivExample privCriteria = new PrivExample();
		Criteria criteria = privCriteria.createCriteria();
		criteria.andPIdIsNotNull();
		criteria.andPIdEqualTo(Long.parseLong((String) pid));
		list = privService.findByCriteria(privCriteria);
		model.addAttribute("list", list);
		
		return "admin/system/priv/showSetSubPriv";
		
	}
	
	
	public String showIndexVm(){
		return "showIndexVm";
	}

	public Pagination getPage()
	{
		return page;
	}

	public void setPage(Pagination page)
	{
		this.page = page;
	}

	public Priv getPriv()
	{
		return priv;
	}

	public void setPriv(Priv priv)
	{
		this.priv = priv;
	}

	public List<Priv> getList()
	{
		return list;
	}

	public void setList(List<Priv> list)
	{
		this.list = list;
	}
}
