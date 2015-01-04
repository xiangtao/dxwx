<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/common/taglibs.jsp"%>

<script type="text/javascript" src="${ctx}/js/backend/system/account/related.js"></script>

<div class="pageContent">
	<form id="addUserMerchantForm" method="post" action="${ctx}/admin/system/useradmin/account/related.html?action=zxzhxg&navTabId=${param.navTabId}" class="pageForm required-validate" >
		<input name="userMerchant.userId" type="hidden" value="${user.id}"/>
		<div class="pageFormContent" layoutH="56" align="right">
	      	<table width="100%" border="0" cellspacing="0" cellpadding="0">
	      		<s:iterator value="#request.storeList" id="s" status="st">
	      			<s:if test="#st.index % 3 == 0"><tr></s:if>
	          			<td><input name="userMerchant.manageRestaurantList" type="checkbox" value="${s.id}" <s:if test="#s.isManaged">checked="checked"</s:if>/>${s.name}</td>
		          	<s:if test="#st.index % 3 == 2"></tr></s:if>
		        </s:iterator>
	      	</table>
		</div>
		<div class="formBar">
			<ul>
				<li><div class="buttonActive"><div class="buttonContent"><button type="submit">保存</button></div></div></li>
				<li><div class="button"><div class="buttonContent"><button type="button" class="close">取消</button></div></div></li>
			</ul>
		</div>
	</form>
</div>