<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/common/taglibs.jsp"%>

<script type="text/javascript" src="${ctx}/js/backend/dic/category/list.js"></script>

<form id="pagerForm" method="post" action="${ctx}/dic/category/showList?action=zxqxss&navTabId=${param.navTabId}">
	<input type="hidden" name="pageNum" value="${page.currentPage}" />
	<input type="hidden" name="numPerPage" value="${page.pageSize}" />
	<input type="hidden" name="orderField" value="${param.orderField}" />
	<input type="hidden" name="orderDirection" value="asc" />
	
	<!--【可选】其它查询条件，业务有关，有什么查询条件就加什么参数。
      			也可以在searchForm上设置属性rel=”pagerForm”，js框架会自动把searchForm搜索条件复制到pagerForm中 -->
	<input type="hidden" name="name" value="${param.name}" />
</form>


<div class="pageHeader">
	<form onsubmit="return navTabSearch(this);" action="${ctx}/dic/category/showList?action=zxqxss&navTabId=${param.navTabId}" method="post">
	<div class="searchBar">
		<table class="searchContent">
			<tr>
				<td>
					分类名称：<input type="text" name="name" />
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
			<li><a class="add" href="${ctx}/dic/category/showAdd?action=tjqxzs&navTabId=${param.navTabId}" target="dialog" mask="true" width="520" height="250"><span>添加分类</span></a></li>
			<%--
			<li><a class="delete" href="${ctx}/dic/category/delete/{sid}?action=zxqxsc&navTabId=${param.navTabId}" target="ajaxTodo" title="确定要删除吗？"><span>删除</span></a></li>
			--%>
			<li><a class="edit" href="${ctx}/dic/category/showUpdate/{sid}?action=xgqxzs&navTabId=${param.navTabId}" target="dialog" mask="true" width="520" height="250"><span>修改分类</span></a></li>
		</ul>
	</div>
	<table class="table" layoutH="117">
		<thead>
			<tr align="center">
				<th width="150">分类名称</th>
				<th width="80">启用状态</th>
				<th width="100">启用 / 屏蔽</th>
				<th width="100">设置</th>
			</tr>
		</thead>
		<tbody>
			<c:forEach items="${page.items }" var="item">
				<tr target="sid" rel="${item.id}" align="center">
					<td>${item.name}</td>
					<td>${item.statusStr}&nbsp;</td>
					<td>
						<c:choose>
							<c:when test="${item.status == 0}">
								<a style="color: blue;" title="启用【${item.name}】" href="javascript:activation(${item.id}, '${item.name}', '${param.navTabId}');">启用</a>
							</c:when>
							<c:otherwise>
								<a style="color: blue;" title="屏蔽【${item.name}】" href="javascript:shield(${item.id}, '${item.name}', '${param.navTabId}');">屏蔽</a>
							</c:otherwise>
						</c:choose>
					</td>
					<td>
						<a style="color: blue;" title="${item.name}-设置子分类" target="dialog" rel="dialog_${item.id}" mask="false" minable="true" 
							href="${ctx}/dic/category/showSetSubCategory?pid=${item.id}&action=zqxlbzs&dialogId=dialog_${item.id}"
							width="700" height="450">设置子分类</a>
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
