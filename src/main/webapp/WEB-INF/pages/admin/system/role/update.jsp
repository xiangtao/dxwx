<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/common/taglibs.jsp"%>

<div class="pageContent">
	<form method="post" action="${ctx}/role/ajax/update?action=zxjsxg&navTabId=${param.navTabId}" class="pageForm required-validate" onsubmit="return validateCallback(this, dialogAjaxDone);">
		<div class="pageFormContent" layoutH="56">
			<input name="id" type="hidden" value="${role.id}" />
			<p>
				<label>角色名称：</label>
				<input name="roleName" class="required" type="text" size="35" value="${role.roleName}" alt="请输入角色名称"/>
			</p>
			<p>
				<label>描述：</label>
				<input type="text" value="${role.description}" name="description" size="35" value="${role.description}" class="textInput">
			</p>
		</div>
		<div class="formBar">
			<ul>
				<!--<li><a class="buttonActive" href="javascript:;"><span>保存</span></a></li>-->
				<li><div class="buttonActive"><div class="buttonContent"><button type="submit">保存</button></div></div></li>
				<li>
					<div class="button"><div class="buttonContent"><button type="button" class="close">取消</button></div></div>
				</li>
			</ul>
		</div>
	</form>
</div>