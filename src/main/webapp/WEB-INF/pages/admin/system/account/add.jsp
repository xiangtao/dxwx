<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/common/taglibs.jsp"%>

<div class="pageContent">
	<form method="post" action="${ctx}/account/ajax/add?action=zxzhtj&navTabId=${param.navTabId}" class="pageForm required-validate" onsubmit="return validateCallback(this, dialogAjaxDone);">
		<div class="pageFormContent" layoutH="56">
			<p>
				<label>用户名称：</label>
				<input name="userName" class="required" type="text" size="35" style="ime-mode: disabled;"/>
			</p>
			<p>
				<label>密码：</label>
				<input name="pwd" class="required" type="password" size="35" style="ime-mode: disabled;"/>
			</p>
			<p>
				<label>邮箱：</label>
				<input type="text" class="email" name="email" size="35"/>
			</p>
			<p>
				<label>用户类型：</label>
				<select name="type" class="required combox">
					<c:forEach items="${userType}" var="t">
						<option value="${t.key}">${t.value}</option>
					</c:forEach>
				</select>
			</p>
			<p>
				<label>角色类型：</label>
				<select name="roleId" class="required combox">
					<c:forEach items="${allRole}" var="r">
						<option value="${r.id}">${r.roleName}</option>
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
