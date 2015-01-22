<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/common/taglibs.jsp"%>
<!doctype html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>场馆介绍-详情</title>
<script type="text/javascript"
	src="${ctx}/js/common/jquery/jquery-1.7.1.js"></script>
<script type="text/javascript"
	src="http://cdn.amazeui.org/amazeui/2.1.0/js/amazeui.min.js"></script>
<link type="text/css"
	href="http://cdn.amazeui.org/amazeui/2.1.0/css/amazeui.min.css"
	rel="stylesheet"></link>
</head>
<body>
	<header data-am-widget="header" class="am-header am-header-default">
	  <div class="am-header-left am-header-nav">
	    <a href="javascript:history.back();" class="" >
	      <img class="am-header-icon-custom" src="data:image/svg+xml;charset=utf-8,&lt;svg xmlns=&quot;http://www.w3.org/2000/svg&quot; viewBox=&quot;0 0 12 20&quot;&gt;&lt;path d=&quot;M10,0l2,2l-8,8l8,8l-2,2L0,10L10,0z&quot; fill=&quot;%23fff&quot;/&gt;&lt;/svg&gt;"
	      alt="返回" />
	    </a>
	  </div>
	  <h1 class="am-header-title">
	    详情
	  </h1>
	 </header>
	<div class="page">
		 <div id="demo-view" data-backend-compiled="">
			 <article class="am-paragraph am-paragraph-default am-no-layout">
			 <p>${content}</p>
			 </article>
		 </div>
	</div>

</body>
</html>