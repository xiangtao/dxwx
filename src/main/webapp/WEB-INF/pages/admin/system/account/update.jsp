<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/common/taglibs.jsp"%>

<div class="pageContent">
	<form method="post" action="${ctx}/account/update?action=zxzhxg&navTabId=${param.navTabId}" class="pageForm required-validate" onsubmit="return validateCallback(this, dialogAjaxDone);">
		<div class="pageFormContent" layoutH="56">
			<input name="id" type="hidden" value="${user.id}" />
			<p>
				<label>用户名称：</label>
				<input name="userName" class="required" type="text" size="35" value="${user.userName}" style="ime-mode: disabled;"/>
			</p>
			<p>
				<label>密码：</label>
				<input name="pwd" class="required" type="password" size="35" value="${user.pwd}" style="ime-mode: disabled;"/>
			</p>
			<p>
				<label>邮箱：</label>
				<input type="text" class="email" name="email" size="35" value="${user.email}"/>
			</p>
			<p>
				<label>用户类型：</label>
				<select name="type" class="required combox">
					<c:forEach items="${userType}" var="t">
						<option value="${t.key}" <c:if test="${user.type == t.key}">selected</c:if>>
							${t.value}
						</option>
					</c:forEach>
				</select>
			</p>
			<p>
				<label>角色类型：</label>
				<select name="roleId" class="required combox">
					<c:forEach items="${allRole}" var="r">
						<option value="${r.id}" <c:if test="${user.roleId == r.id}">selected</c:if>>
							${r.roleName}
						</option>
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
