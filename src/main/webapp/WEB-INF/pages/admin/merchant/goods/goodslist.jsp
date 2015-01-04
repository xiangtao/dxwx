<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/common/taglibs.jsp"%>

<script type="text/javascript" src="${ctx}/js/backend/merchant/list.js"></script>

<form id="pagerForm" method="post" action="${ctx}/merchant/goods/showList?action=role_mgr&navTabId=${param.navTabId}">
	<input type="hidden" name="pageNum" value="${page.currentPage}" />
	<input type="hidden" name="numPerPage" value="${page.pageSize}" />
	<input type="hidden" name="orderField" value="${param.orderField}" />
	<input type="hidden" name="orderDirection" value="asc" />
	
	<!--【可选】其它查询条件，业务有关，有什么查询条件就加什么参数。
      			也可以在searchForm上设置属性rel=”pagerForm”，js框架会自动把searchForm搜索条件复制到pagerForm中 -->
	<input type="hidden" name="name" value="${param.name}" />
</form>

<div class="pageHeader">
	<form onsubmit="return navTabSearch(this);" action="${ctx}/merchant/goods/showList?action=zxjsss&navTabId=${param.navTabId}" method="post" rel="pagerForm">
	<div class="searchBar">
		<input name="merchantId" value="${param.merchantId}" type="hidden"/>
		<table class="searchContent">
			<tr>
				<td>
					商品名称：<input type="text" name="name" />
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
			<li><a class="add" href="${ctx}/merchant/goods/showAdd?merchantId=${param.merchantId}&action=tjzhzs&navTabId=${param.navTabId}" target="dialog" mask="true" width="900" height="500"><span>添加商品</span></a></li>
			<li><a class="edit" href="${ctx}/merchant/goods/showUpdate/{sid}?action=xgzhzs&navTabId=${param.navTabId}" target="dialog" mask="true" width="900" height="500"><span>修改商品</span></a></li>
		</ul>
	</div>
	<table class="list"  layoutH="95">
		<thead>
			<tr align="center">
				<th width="180">商品名称</th>
				<th width="180">商品内容</th>
				<th width="80">原价</th>
				<th width="80">现价</th>
				<th width="80">折扣</th>
				<th width="80">优惠图片</th>
				<th width="150">是否置顶</th>
				<th width="150">发布状态</th>
				<th width="150">创建时间</th>
				<th width="150">发布时间</th>
				<th width="100">操作</th>
			</tr>
		</thead>
		<tbody>
			<c:forEach items="${page.items }" var="item">
				<tr target="sid" rel="${item.id}" align="center" height="50">
					<td>${item.name}</td>
					<td>${item.content}</td>
					<td>${item.srcPrice}</td>
					<td>${item.nowPrice}</td>
					<td>${item.disount}</td>
					<td>
						<img title="${item.name}" src="${item.coverImage}" width="40" height="40" />
						<br/>
						<a href="${ctx}/merchant/goods/image?url=${item.coverImage}&name=${item.name}&action=tjzhzs&navTabId=${param.navTabId}" target="dialog" mask="true" width="800" height="450"><span>查看大图</span></a>
					</td>
					<td>
						<c:choose>
							<c:when test="${item.isTop}">
								是
							</c:when>
							<c:otherwise>
								否
							</c:otherwise>
						</c:choose>
					</td>
					
					<td>
						<c:choose>
							<c:when test="${item.isPublish}">
								已发布
							</c:when>
							<c:otherwise>
								未发布
							</c:otherwise>
						</c:choose>
					</td>
					<td><fmt:formatDate value="${item.createTime}" pattern="yyyy-MM-dd HH:mm:ss"/></td>
					<td><fmt:formatDate value="${item.publishTime}" pattern="yyyy-MM-dd HH:mm:ss"/></td>
					<td>
						<c:choose>
							<c:when test="${item.isPublish}">
								<a href="${ctx}/merchant/goods/unPublish?id=${item.id}&action=zxzhsc&navTabId=${param.navTabId}" target="ajaxTodo" title="确定下架 - ${item.name} - 吗?">下架商品</a>&nbsp;&nbsp;&nbsp;&nbsp;
							</c:when>
							<c:otherwise>
								<a href="${ctx}/merchant/goods/publish?id=${item.id}&action=zxzhsc&navTabId=${param.navTabId}" target="ajaxTodo" title="确定发布 - ${item.name} - 吗?">发布商品</a>&nbsp;&nbsp;&nbsp;&nbsp;
							</c:otherwise>
						</c:choose>
						<br/>
						<c:choose>
							<c:when test="${item.isTop}">
								<a href="${ctx}/merchant/goods/unTop?id=${item.id}&action=zxzhsc&navTabId=${param.navTabId}" target="ajaxTodo" title="确定取消置顶 - ${item.name} - 吗?">取消置顶</a>&nbsp;&nbsp;&nbsp;&nbsp;
							</c:when>
							<c:otherwise>
								<a href="${ctx}/merchant/goods/top?id=${item.id}&action=zxzhsc&navTabId=${param.navTabId}" target="ajaxTodo" title="确定置顶 - ${item.name} - 吗?">置顶商品</a>&nbsp;&nbsp;&nbsp;&nbsp;
							</c:otherwise>
						</c:choose>
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
