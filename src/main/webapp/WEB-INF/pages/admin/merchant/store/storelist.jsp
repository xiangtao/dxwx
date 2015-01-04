<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/common/taglibs.jsp"%>

<script type="text/javascript" src="${ctx}/js/backend/merchant/list.js"></script>

<form id="pagerForm" method="post" action="${ctx}/merchant/store/showList?action=role_mgr&navTabId=${param.navTabId}">
	<input type="hidden" name="pageNum" value="${page.currentPage}" />
	<input type="hidden" name="numPerPage" value="${page.pageSize}" />
	<input type="hidden" name="orderField" value="${param.orderField}" />
	<input type="hidden" name="orderDirection" value="asc" />
	
	<!--【可选】其它查询条件，业务有关，有什么查询条件就加什么参数。
      			也可以在searchForm上设置属性rel=”pagerForm”，js框架会自动把searchForm搜索条件复制到pagerForm中 -->
	<input type="hidden" name="name" value="${param.name}" />
</form>

<div class="pageHeader">
	<form onsubmit="return navTabSearch(this);" action="${ctx}/merchant/store/showList?action=zxjsss&navTabId=${param.navTabId}" method="post" rel="pagerForm">
	<div class="searchBar">
		<input name="merchantId" value="${params.merchantId}" type="hidden"/>
		<table class="searchContent">
			<tr>
				<td>
					分店名称：<input type="text" name="name" />
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
			<li><a class="add" href="${ctx}/merchant/store/showAdd?merchantId=${param.merchantId}&action=tjzhzs&navTabId=${param.navTabId}" target="dialog" mask="true" width="600" height="350"><span>添加分店</span></a></li>
			<li><a class="edit" href="${ctx}/merchant/store/showUpdate/{sid}?action=xgzhzs&navTabId=${param.navTabId}" target="dialog" mask="true" width="600" height="350"><span>修改分店</span></a></li>
		</ul>
	</div>
	<table class="list"  layoutH="95">
		<thead>
			<tr align="center">
				<th width="180">分店名称</th>
				<th width="120">固定电话</th>
				<th width="120">手机</th>
				<th width="280">地址</th>
				<th width="80">经度</th>
				<th width="80">纬度</th>
				<th width="80">操作</th>
			</tr>
		</thead>
		<tbody>
			<c:forEach items="${page.items }" var="item">
				<tr target="sid" rel="${item.id}" align="center" height="40">
					<td>${item.name}</td>
					<td>${item.phone}</td>
					<td>${item.mobile}</td>
					<td>${item.address}</td>
					<td>${item.longitude}</td>
					<td>${item.latitude}</td>
					<td>
                        <a href="${ctx}/merchant/store/delete/${item.id}?action=zxzhsc&navTabId=${param.navTabId}" target="ajaxTodo" title="确定删除 - ${item.name} - 吗?">删除分店</a>
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
