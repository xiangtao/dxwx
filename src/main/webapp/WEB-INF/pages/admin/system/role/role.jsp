<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/common/taglibs.jsp"%>
<form id="pagerForm" method="post" action="${ctx}/role/showIndex?action=role_mgr&navTabId=${param.navTabId}">
	<input type="hidden" name="pageNum" value="${page.currentPage}" />
	<input type="hidden" name="numPerPage" value="${page.pageSize}" />
	<input type="hidden" name="orderField" value="${param.orderField}" />
	<input type="hidden" name="orderDirection" value="asc" />
	
	<!--【可选】其它查询条件，业务有关，有什么查询条件就加什么参数。
      			也可以在searchForm上设置属性rel=”pagerForm”，js框架会自动把searchForm搜索条件复制到pagerForm中 -->
	<input type="hidden" name="roleName" value="${param.roleName}" />
</form>

<div class="pageHeader">
	<form onsubmit="return navTabSearch(this);" action="${ctx}/role/showIndex?action=zxjsss&navTabId=${param.navTabId}" method="post">
	<div class="searchBar">
		<table class="searchContent">
			<tr>
				<td>
					角色名称：<input type="text" name="roleName" />
				</td>
				<td>
					<div class="subBar">
						<ul>
							<li><div class="buttonActive"><div class="buttonContent"><button type="submit">搜索</button></div></div></li>
						</ul>
					</div>
				</td>
			</tr>
		</table>
	</div>
	</form>
</div>
<div class="pageContent">
	<div class="panelBar">
		<ul class="toolBar">
			<li><a class="add" href="${ctx}/role/showAdd?action=tjjszs&navTabId=${param.navTabId}" target="dialog" mask="true" width="520" height="250"><span>添加角色</span></a></li>
			<li><a class="delete" href="${ctx}/role/ajax/delete?id={sid}&action=zxjssc&navTabId=${param.navTabId}" target="ajaxTodo" title="确定要删除吗?"><span>删除</span></a></li>
			<li><a class="edit" href="${ctx}/role/showUpdate?id={sid}&action=xgjszs&navTabId=${param.navTabId}" target="dialog" mask="true" width="520" height="250"><span>修改角色</span></a></li>
		</ul>
	</div>
	<table class="table"  layoutH="117">
		<thead>
			<tr align="center">
				<th width="180">角色名称</th>
				<th width="300">角色描述</th>
				<th width="100">操作</th>
			</tr>
		</thead>
		<tbody>
			<c:forEach items="${page.items }" var="item">
				<tr target="sid" rel="${item.id}" align="center">
					<td>${item.roleName}</td>
					<td align="left">${item.description}</td>
					<td>
						<a style="color: blue;" title="${item.roleName}-设置权限" target="dialog" mask="true" width="780" height="480" 
							href="${ctx}/role/showSetPriv?id=${item.id}&action=qxfpzs">设置权限</a>
						<a title="编辑" target="dialog" mask="true" width="520" height="250" 
							href="${ctx}/role/showUpdate?id=${item.id}&action=xgjszs&navTabId=${param.navTabId}" class="btnEdit">编辑</a>
					</td>
				</tr>
			</c:forEach>
		</tbody>
	</table>
	<div class="panelBar" layoutH="0">
		<div class="pages">
			<span style="margin-right: 3px;">显示</span>
			<select class="combox" name="numPerPage" onchange="navTabPageBreak({numPerPage:this.value})">
				<option value="15" <c:if test="${page.pageSize == 15}">selected</c:if>>15</option>
				<option value="20" <c:if test="${page.pageSize == 20}">selected</c:if>>20</option>
				<option value="25" <c:if test="${page.pageSize == 25}">selected</c:if>>25</option>
				<option value="30" <c:if test="${page.pageSize == 30}">selected</c:if>>30</option>
			</select>
			<span style="margin-left: 5px;">共 - ${page.totalCount} - 条</span>
		</div>
		
		<div class="pagination" targetType="navTab" totalCount="${page.totalCount }" numPerPage="${page.pageSize }" pageNumShown="10" currentPage="${page.currentPage }"></div>

	</div>
</div>
