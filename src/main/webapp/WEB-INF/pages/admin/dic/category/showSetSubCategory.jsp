<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/common/taglibs.jsp"%>

<script type="text/javascript">
/** 启用分类 **/
function activation(id, name, dialogId) {
	alertMsg.confirm("确认启用分类 - " + name + " - 吗？", {
		okCall : function() {
			$.post(ctx + "/dic/category/activation", {
					"id" : id,
					"dialogId" : dialogId
				}, 
				function(json, status) {
					refreshDialogAjaxDone(json);
				}, 
			"json");
		}
	});
}

/** 屏蔽分类 **/
function shield(id, name, dialogId) {
	alertMsg.confirm("确认屏蔽分类 - " + name + " - 吗？", {
		okCall : function() {
			$.post(ctx + "/dic/category/shield", {
					"id" : id,
					"dialogId" : dialogId
				}, 
				function(json, status) {
					refreshDialogAjaxDone(json);
				}, 
			"json");
		}
	});
}
</script>

<div class="pageContent">
	<div class="panelBar">
		<ul class="toolBar">
			<li><a class="add" href="${ctx}/dic/category/showAdd?pid=${param.pid}&action=tjqxzs&dialogId=${param.dialogId}" target="dialog" mask="true" rel="${param.dialogId}_add" width="520" height="250"><span>添加分类</span></a></li>
			<li><a class="edit" href="${ctx}/dic/category/showUpdate/{sid}?action=xgqxzs&dialogId=${param.dialogId}" target="dialog" mask="true" rel="${param.dialogId}_update" width="520" height="250"><span>修改分类</span></a></li>
		</ul>
	</div>
	<table class="table" layoutH="50">
		<thead>
			<tr align="center">
				<th width="150">分类名称</th>
				<th width="80">启用状态</th>
				<th width="100">启用 / 屏蔽</th>
				<%--
				<th width="80">操作</th>
				--%>
			</tr>
		</thead>
		<tbody>
			<c:forEach items="${list}" var="item">
				<tr target="sid" rel="${item.id}" align="center">
					<td>${item.name}</td>
					<td>${item.statusStr}&nbsp;</td>
					<td>
						<c:choose>
							<c:when test="${item.status == 0}">
								<a style="color: blue;" title="启用【${item.name}】" href="javascript:activation(${item.id}, '${item.name}', '${param.dialogId}');">启用</a>
							</c:when>
							<c:otherwise>
								<a style="color: blue;" title="屏蔽【${item.name}】" href="javascript:shield(${item.id}, '${item.name}', '${param.dialogId}');">屏蔽</a>
							</c:otherwise>
						</c:choose>
					</td>
					<%--
					<td>
						<a style="color: blue;" href="javascript: delAjaxInDialog('${ctx}/dic/category/delete/${item.id}?action=zxqxsc&dialogId=${param.dialogId}', '${param.dialogId}', '确定要删除吗?')">
							删 除
						</a>
					</td>
					--%>
				</tr>
			</c:forEach>
		</tbody>
	</table>
</div>