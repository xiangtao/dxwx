<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" import="java.lang.Math"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix='fmt'%>
<c:set var="ctx" value="${pageContext.request.contextPath}" />
<%--
<c:set var="resourceCtx" value="${pageContext.request.contextPath}" />
--%>
<%
	request.setAttribute("randNum", Math.random());
%>
<c:set var="resourceCtx" value="http://182.92.183.95/resource" />
