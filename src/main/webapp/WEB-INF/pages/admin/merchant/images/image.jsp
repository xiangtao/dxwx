<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/common/taglibs.jsp"%>
<link type="text/css" href="${ctx}/style/backend/common/style.css" rel="stylesheet"/>
<style type="text/css">
<!--
.STYLE1 {
	font-family: "华文楷体";
	font-size: 30px;
}
-->
</style>

<div class="pageContent" layoutH="32">
	<img src="${resourceCtx}/${param.url}" />
</div>

<div class="formBar">
	<ul>
		<li><div class="button"><div class="buttonContent"><button type="button" class="close">关闭</button></div></div></li>
	</ul>
</div>