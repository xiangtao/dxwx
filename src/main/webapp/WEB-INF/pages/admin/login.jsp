<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/common/taglibs.jsp"%>

<!DOCTYPE html>
<html version="-//W3C//DTD HTML 4.01 Transitional//EN">
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
	<title>登录</title>
	<link rel="shortcut icon" href="${ctx}/images/common/logo.ico" type="image/x-icon"/>
	<%@ include file="/WEB-INF/common/jslibs.jsp"%>
	<link type="text/css" href="${ctx}/styles/backend/login/login.css" rel="stylesheet"/>
</head>
<body>
	<div id="login">
		<div id="login_content">
			<div class="loginForm">
				<form action="">
					<p>
						<label>用户名：</label>
						<input id="username" type="text" name="username" maxlength="16" style="ime-mode: disabled;"
							class="login_input" onkeydown="clickLoginButton(event, 'loginBtn')"
								onfocus="getFocus(this.id)" onblur="loseFocus(this.id)" value="admin"/>
					</p>
					<p>
						<label>私密码：</label>
						<input id="password" type="password" name="password" maxlength="16" style="ime-mode: disabled;"
							class="login_input" onkeydown="clickLoginButton(event, 'loginBtn')" 
								onfocus="getFocus(this.id)" onblur="loseFocus(this.id)" onpaste="return false;"/>
					</p>
					<p>
						<label>验证码：</label>
						<input id="verifycode" type="text" name="verifycode" maxlength="4" style="ime-mode: disabled;"
							 class="code" onkeydown="clickLoginButton(event, 'loginBtn')"
								onfocus="getFocus(this.id)" onblur="loseFocus(this.id)" value="1234"/>
						<span>
							<img id="verifyImg" src="${ctx}/verify/getVerifyCode" title="换一张" width="75" height="24" onclick="changeImage(this)"/>									
						</span>
					</p>
					<div class="login_bar">
						<input id="loginBtn" class="sub" type="button" value="" onclick="check()" 
							onmouseover="mouseOver(this.id, '${ctx}/images/backend/login/login_btn_bg2.jpg')" 
								onmouseout="mouseOut(this.id, '${ctx}/images/backend/login/login_btn_bg1.jpg')"/>
						<input id="resetBtn" class="reset" type="reset" value="" 
							onmouseover="mouseOver(this.id, '${ctx}/images/backend/login/reset_btn_bg2.jpg')" 
								onmouseout="mouseOut(this.id, '${ctx}/images/backend/login/reset_btn_bg1.jpg')"/>
					</div>
				</form>
			</div>
			<div class="login_banner"><img src="${ctx}/images/backend/login/login_banner.jpg" /></div>
	  	</div>
		<div id="login_footer">
			Copyright &copy; 2014 DoReader Inc. All Rights Reserved.
		</div>
	</div>
</body>
</html>