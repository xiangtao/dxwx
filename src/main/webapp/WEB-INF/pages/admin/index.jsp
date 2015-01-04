<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@page import="com.deve.pig.model.Admin"%>
<%@ include file="/WEB-INF/common/taglibs.jsp"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
		<meta http-equiv="X-UA-Compatible" content="IE=7" />
		<title>后台管理系统</title>
		<link rel="shortcut icon" href="${ctx}/images/common/logo.ico" type="image/x-icon"/>
		<%@ include file="/WEB-INF/common/jslibs.jsp"%>
		<script type="text/javascript">
			$(function(){
				DWZ.init("${ctx}/dwz.frag.xml", {
				//	loginUrl:"${ctx}/login/toLogin", loginTitle:"登录",	// 弹出登录对话框
					loginUrl:"${ctx}/login/toLogin",	// 跳到登录页面
					statusCode:{ok:200, error:300, timeout:301, nopower:302}, //【可选】
					pageInfo:{pageNum:"pageNum", numPerPage:"numPerPage", orderField:"orderField", orderDirection:"orderDirection"}, //【可选】
					debug:false,	// 调试模式 【true|false】
					callback:function(){
						initEnv();
						$("#themeList").theme({themeBase:"${ctx}/styles/backend/themes"}); // themeBase 相对于index页面的主题base路径
					}
				});
			});
		</script>
	</head>

	<body scroll="no">
		<div id="layout">
			<div id="header">
				<div class="headerNav">
					<a class="logo" href="#">Logo</a>
					<ul class="nav">
						<li>欢迎您！${user.userName}</li>
						<li><a href="#" onclick="confirm2Link('您确定退出吗？', '${ctx}/login/loginOut')">退出</a></li>
					</ul>
					<ul class="themeList" id="themeList">
						<li theme="default" title="蓝色"><div class="selected">蓝色</div></li>
						<li theme="green" title="绿色"><div>绿色</div></li>
						<li theme="purple" title="紫色"><div>紫色</div></li>
						<li theme="silver" title="银色"><div>银色</div></li>
						<li theme="azure" title="天蓝"><div>天蓝</div></li>
					</ul>
				</div>
	
				<!-- navMenu -->
				
			</div>
	
			<div id="leftside">
				<div id="sidebar_s">
					<div class="collapse">
						<div class="toggleCollapse"><div></div></div>
					</div>
				</div>
				<div id="sidebar">
					<div class="toggleCollapse"><h2>主菜单</h2><div>收缩</div></div>
	
					<div class="accordion" fillSpace="sidebar">
						${allMenuHtml}
					</div>
				</div>
			</div>
			<div id="container">
				<div id="navTab" class="tabsPage">
					<div class="tabsPageHeader">
						<div class="tabsPageHeaderContent"><!-- 显示左右控制时添加 class="tabsPageHeaderMargin" -->
							<ul class="navTab-tab">
								<li tabid="main" class="main"><a href="javascript:;"><span><span class="home_icon">&nbsp;首&nbsp;&nbsp;页</span></span></a></li>
							</ul>
						</div>
						<div class="tabsLeft">left</div><!-- 禁用只需要添加一个样式 class="tabsLeft tabsLeftDisabled" -->
						<div class="tabsRight">right</div><!-- 禁用只需要添加一个样式 class="tabsRight tabsRightDisabled" -->
						<div class="tabsMore">more</div>
					</div>
					<ul class="tabsMoreList">
						<li><a href="javascript:;">&nbsp;首&nbsp;&nbsp;页</a></li>
					</ul>
					<div class="navTab-panel tabsPageContent layoutBox">
						<div class="page unitBox" style="height: 100%; margin: 1px;">
							<%
								if(((Admin) session.getAttribute("user")).getAllPrivs().containsKey("home"))
								{
							%>
									<jsp:include page="/WEB-INF/pages/admin/desktop.jsp"></jsp:include>
				      		<%
				      			}
				      		%>
						</div>
					</div>
				</div>
			</div>
		</div>
	
		<div id="footer">Copyright &copy; 2014 <a href="#">app</a></div>
	
	</body>
</html>