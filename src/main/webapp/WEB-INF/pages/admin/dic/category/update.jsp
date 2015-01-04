<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/common/taglibs.jsp"%>

<script type="text/javascript" src="${ctx}/js/backend/dic/category/add.js"></script>

<div class="pageContent">
	<form method="post" action="${ctx}/dic/category/update?navTabId=${param.navTabId}&dialogId=${param.dialogId}" class="pageForm required-validate"
			 onsubmit="return validateCallback(this, <c:choose><c:when test="${param.dialogId != null}">refreshDialogAjaxDone</c:when><c:otherwise>dialogAjaxDone</c:otherwise></c:choose>);">
		<div class="pageFormContent" layoutH="56">
			<input type="hidden" name="id" value="${category.id}">
			<input type="hidden" name="pid" value="${category.pid}">
			<p>
				<label>分类名称：</label>
				<input name="name" class="required" type="text" size="35" value="${category.name}"/>
			</p>
			<p>
				<label>启用状态：</label>
				<select name="status" class="required combox">
					<c:forEach var="s" items="${categoryStatus}">
						<option value="${s.key}" <c:if test="${s.key == category.status}">selected="selected"</c:if>>${s.value}</option>
					</c:forEach>
				</select>
			</p>
		</div>
		<div class="formBar">
			<ul>
				<li><div class="buttonActive"><div class="buttonContent"><button type="submit">保存</button></div></div></li>
				<li><div class="button"><div class="buttonContent"><button type="button" class="close">取消</button></div></div></li>
			</ul>
		</div>
	</form>
</div>