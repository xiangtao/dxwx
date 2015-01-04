<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/common/taglibs.jsp"%>

<div class="pageContent">
	<form method="post" action="${ctx}/priv/ajax/update?navTabId=${param.navTabId}&dialogId=${param.dialogId}" class="pageForm required-validate" 
		onsubmit="return validateCallback(this, <c:choose><c:when test="${param.dialogId != null}">refreshDialogAjaxDone</c:when><c:otherwise>dialogAjaxDone</c:otherwise></c:choose>);">
		<div class="pageFormContent" layoutH="56">
			<input name="id" type="hidden" value="${priv.id}" />
			<input name="pid" type="hidden" value="${priv.pid}" />
			<p>
				<label>权限名称：</label>
				<input name="privName" class="required" type="text" size="35" value="${priv.privName}" alt="请输入权限名称"/>
			</p>
			<p>
				<label>菜单名称：</label>
				<input name="menuName" type="text" size="35" value="${priv.menuName}" alt="请输入菜单名称"/>
			</p>
			<p>
				<label>动作标识：</label>
				<input name="action" type="text" size="35" value="${priv.action}" alt="请输入动作标识"/>
			</p>
			<p>
				<label>描述：</label>
				<input type="text" size="35" name="description" size="35" value="${priv.description}">
			</p>
			<p>
				<label>是否显示：</label>
				<input type="checkbox" name="isShow" value="1" <c:if test="${priv.isShow == 1}">checked='checked'</c:if> />
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
