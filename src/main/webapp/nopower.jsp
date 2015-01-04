<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/common/taglibs.jsp"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <title>权限提示</title>
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">
	<link rel="shortcut icon" href="${ctx}/images/common/logo.ico" type="image/x-icon"/>
	<%@ include file="/WEB-INF/common/jslibs.jsp"%>
	<link type="text/css" href="${ctx}/styles/backend/login/login.css" rel="stylesheet"/>
  </head>
  
  <body>
	<script>
		art.dialog( {
			title : "温馨提示",
			content : "抱歉，您没有操作权限！<br/><br/>若需要该权限，请联系管理员！",
			left : "50%",
			icon : "face-sad",
		    drag : false,
		    fixed : true,
		    resize : false,
			close : function() {
				return false;
			},
			okVal : "管理首页",
			ok : function() {
				window.location.href = "${ctx}/admin/home.html";
				return false;
			}
		}).lock();
	</script>
  </body>
</html>