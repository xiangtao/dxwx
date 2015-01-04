<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/common/taglibs.jsp"%>
<form id="pagerForm" method="post" action="${ctx}/priv/showSetSubPriv?action=zqxlbzs&dialogId=${param.dialogId}">
	<!--【可选】其它查询条件，业务有关，有什么查询条件就加什么参数。
      			也可以在searchForm上设置属性rel=”pagerForm”，js框架会自动把searchForm搜索条件复制到pagerForm中 -->
	<input type="hidden" name="userName" value="${param.userName}" />
	<input type="hidden" name="email" value="${param.email}" />
</form>

<div class="pageContent">
	<div class="panelBar">
		<ul class="toolBar">
			<li><a class="add" href="${ctx}/priv/showAdd?pid=${param.pid}&action=tjqxzs&dialogId=${param.dialogId}" target="dialog" mask="true" rel="${param.dialogId}_add" width="520" height="250"><span>添加权限</span></a></li>
			<li><a class="edit" href="${ctx}/priv/showUpdate?id={sid}&action=xgqxzs&dialogId=${param.dialogId}" target="dialog" mask="true" rel="${param.dialogId}_update"><span>修改权限</span></a></li>
		</ul>
	</div>
	<table class="table" layoutH="50">
		<thead>
			<tr align="center">
				<th width="120">权限名称</th>
				<th width="120">菜单名称</th>
				<th width="280">动作标识</th>
				<th width="200">描述</th>
				<th width="80">是否显示</th>
				<th width="180">操作</th>
			</tr>
		</thead>
		<tbody>
			<c:forEach items="${list}" var="item">
				<tr target="sid" rel="${item.id }" align="center">
					<td>${item.privName}</td>
					<td>${item.menuName}</td>
					<td>${item.action}</td>
					<td align="left">${item.description}</td>
					<td>
						<c:choose>
							<c:when test="${item.isShow == 1 }">
								是
							</c:when>
							<c:otherwise>
								否
							</c:otherwise>
						</c:choose>
					<td>
						<a title="${item.privName}-设置子权限" target="dialog" rel="dialog_${item.id}" mask="false" minable="true" 
							href="${ctx}/priv/showSetSubPriv?pid=${item.id}&action=zqxlbzs&dialogId=dialog_${item.id}"
							width="800" height="480">设置子权限</a>
						&nbsp;&nbsp;&nbsp;&nbsp;
						<a href="javascript: delAjaxInDialog('${ctx}/priv/ajax/delete?id=${item.id}&action=zxqxsc&dialogId=${param.dialogId}', '${param.dialogId}', '确定要删除吗?')">
							删 除
						</a>
					</td>
				</tr>
			</c:forEach>
		</tbody>
	</table>
</div>
