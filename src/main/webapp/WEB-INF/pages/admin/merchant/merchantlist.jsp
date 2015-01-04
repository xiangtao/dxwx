<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/common/taglibs.jsp"%>

<script type="text/javascript" src="${ctx}/js/backend/merchant/list.js"></script>

<form id="pagerForm" method="post" action="${ctx}/merchant/showList?action=role_mgr&navTabId=${param.navTabId}">
	<input type="hidden" name="pageNum" value="${page.currentPage}" />
	<input type="hidden" name="numPerPage" value="${page.pageSize}" />
	<input type="hidden" name="orderField" value="${param.orderField}" />
	<input type="hidden" name="orderDirection" value="asc" />
	
	<!--【可选】其它查询条件，业务有关，有什么查询条件就加什么参数。
      			也可以在searchForm上设置属性rel=”pagerForm”，js框架会自动把searchForm搜索条件复制到pagerForm中 -->
</form>

<div class="pageHeader">
	<form onsubmit="return navTabSearch(this);" action="${ctx}/merchant/showList?action=zxjsss&navTabId=${param.navTabId}" method="post" rel="pagerForm">
	<div class="searchBar">
		<table class="searchContent">
			<tr>
				<td>
					商家：<input type="text" name="name" />
				</td>
				<td>
					<select class="combox" name="status">
						<option value="">是否发布</option>
						<option value="1">是</option>
						<option value="0">否</option>
					</select>
				</td>
				<td>
					发布时间：
					<input type="text" name="publishTime1" class="date textInput readonly valid" 
						format="yyyy-MM-dd HH:mm:ss" readonly="true" />
					-
					<input type="text" name="publishTime2" class="date textInput readonly valid" 
					format="yyyy-MM-dd HH:mm:ss" readonly="true" />
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
			<li><a class="add" href="${ctx}/merchant/showAdd?action=tjzhzs&navTabId=${param.navTabId}" target="dialog" mask="true" width="900" height="500"><span>添加商家</span></a></li>
			<li><a class="edit" href="${ctx}/merchant/showUpdate/{sid}?action=xgzhzs&navTabId=${param.navTabId}" target="dialog" mask="true" width="900" height="500"><span>修改商家</span></a></li>
		</ul>
	</div>
	<table class="list"  layoutH="95">
		<thead>
			<tr align="center">
				<th width="180">商家id</th>
				<th width="180">商家名称</th>
				<th width="80">星级</th>
				<th width="80">是否有优惠</th>
				<th width="80">是否置顶</th>
				<th width="80">是否发布</th>
				<th width="150">发布时间</th>
				<th width="150">创建时间</th>
				<th width="100">操作</th>
			</tr>
		</thead>
		<tbody>
			<c:forEach items="${page.items }" var="item">
				<tr target="sid" rel="${item.id}" align="center" height="80">
					<td>${item.id}</td>
					<td>${item.name}</td>
					<td>${item.starLevel}</td>
					
					<td>
						<c:choose>
							<c:when test="${item.isDiscount == 1}">
								是
							</c:when>
							<c:otherwise>
								否
							</c:otherwise>
						</c:choose>
					</td>
					
					<td>
						<c:choose>
							<c:when test="${item.isTop == 1}">
								是
							</c:when>
							<c:otherwise>
								否
							</c:otherwise>
						</c:choose>
					</td>
					
					<td>
						<c:choose>
							<c:when test="${item.isPublish == 1}">
								是
							</c:when>
							<c:otherwise>
								否
							</c:otherwise>
						</c:choose>
					</td>
					
					
					<td>
						<fmt:formatDate value="${item.publishTime}" pattern="yyyy-MM-dd HH:mm:ss"/>
					</td>
					
					<td><fmt:formatDate value="${item.createTime}" pattern="yyyy-MM-dd HH:mm:ss"/></td>
					
					<td>
						<a title="商家 - ${item.name}"  target="navTab" rel="merchant-branchstore-view-${item.id}"
							href="${ctx}/merchant/store/showList?merchantId=${item.id}&action=xgjszs&navTabId=merchant-branchstore-view-${item.id}">分店信息</a>
						<br/>
						<a title="商家 - ${item.name}"  target="navTab" rel="merchant-goods-view-${item.id}"
							href="${ctx}/merchant/goods/showList?merchantId=${item.id}&action=xgjszs&navTabId=merchant-goods-view-${item.id}">商品信息</a>
						<br/>
						<a title="商家 - ${item.name}"  target="navTab" rel="merchant-images-view-${item.id}"
							href="${ctx}/merchant/images/showList?merchantId=${item.id}&action=xgjszs&navTabId=merchant-images-view-${item.id}">图片信息</a>
							
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
