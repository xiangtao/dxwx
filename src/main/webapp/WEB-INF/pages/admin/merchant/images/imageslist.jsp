<%@ page language="java" import="java.util.*" pageEncoding="UTF-8" %>
<%@ include file="/WEB-INF/common/taglibs.jsp" %>

<script type="text/javascript" src="${ctx}/js/backend/merchant/list.js"></script>

<form id="pagerForm" method="post" action="${ctx}/merchant/images/showList?action=role_mgr&navTabId=${param.navTabId}">
    <input type="hidden" name="pageNum" value="${page.currentPage}"/>
    <input type="hidden" name="numPerPage" value="${page.pageSize}"/>
    <input type="hidden" name="orderField" value="${param.orderField}"/>
    <input type="hidden" name="orderDirection" value="asc"/>

    <!--【可选】其它查询条件，业务有关，有什么查询条件就加什么参数。
                  也可以在searchForm上设置属性rel=”pagerForm”，js框架会自动把searchForm搜索条件复制到pagerForm中 -->
</form>

<div class="pageHeader">
    <form onsubmit="return navTabSearch(this);" action="${ctx}/merchant/images/showList?action=zxjsss&navTabId=${param.navTabId}" method="post" rel="pagerForm">
        <div class="searchBar">
            <input name="merchantId" value="${param.merchantId}" type="hidden"/>
            <table class="searchContent">
                <tr>
                    <td>
                    </td>
                    <td>
                    </td>
                </tr>
            </table>
        </div>
    </form>
</div>
<div class="pageContent">
    <div class="panelBar">
        <ul class="toolBar">
            <li><a class="add" href="${ctx}/merchant/images/showAdd?merchantId=${param.merchantId}&action=tjzhzs&navTabId=${param.navTabId}" target="dialog" mask="true" width="750" height="360"><span>添加图片</span></a></li>
            <li><a class="edit" href="${ctx}/merchant/images/showUpdate/{sid}?action=xgzhzs&navTabId=${param.navTabId}" target="dialog" mask="true" width="750" height="360"><span>修改图片</span></a></li>
            <li><a class="delete" href="${ctx}/merchant/images/delete/{sid}?action=zxzhsc&navTabId=${param.navTabId}" target="ajaxTodo" title="确定删除该图片吗?"><span>删除图片</span></a></li>
        </ul>
    </div>
    <table class="list" layoutH="95">
        <thead>
        <tr align="center">
            <th width="300">图片</th>
            <th width="100">是否封面</th>
            <th width="100">操作</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach items="${page.items}" var="item">
            <tr target="sid" rel="${item.id}" align="center" height="70">
                <td>
                    <img src="${resourceCtx}/${item.image}" width="60" height="60"/>
                </td>
                <td>
                    <c:choose>
                        <c:when test="${item.isCover == 1}">
                            <font color="#E13CD8">是</font>
                        </c:when>
                        <c:otherwise>
                            <font color="#1979E0">否</font>
                        </c:otherwise>
                    </c:choose>
                </td>
                <td>
                    <a href="${ctx}/merchant/images/image?url=${item.image}&action=tjzhzs&navTabId=${param.navTabId}"
                       target="dialog" mask="true" width="800" height="450"><span>查看大图</span></a>
                    <br/>
                    <c:choose>
                        <c:when test="${item.isCover == 1}">
                            <a href="${ctx}/merchant/images/unCover?id=${item.id}&merchantId=${item.merchantId}&action=zxzhsc&navTabId=${param.navTabId}"
                               target="ajaxTodo" title="确定取消该封面吗?">取消封面</a>
                        </c:when>
                        <c:otherwise>
                            <a href="${ctx}/merchant/images/cover?id=${item.id}&merchantId=${item.merchantId}&action=zxzhsc&navTabId=${param.navTabId}"
                               target="ajaxTodo" title="确定设置该图为封面吗?">设置封面</a>
                        </c:otherwise>
                    </c:choose>
                </td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
    <div class="panelBar" layoutH="0">
        <div class="pages">
            <span style="margin-right: 3px;">显示</span>
            <select class="combox" name="numPerPage" onchange="navTabPageBreak({numPerPage:this.value})">
                <option value="15" <c:if test="${page.pageSize == 15}">selected</c:if>>15</option>
                <option value="20" <c:if test="${page.pageSize == 20}">selected</c:if>>20</option>
                <option value="25" <c:if test="${page.pageSize == 25}">selected</c:if>>25</option>
                <option value="30" <c:if test="${page.pageSize == 30}">selected</c:if>>30</option>
            </select>
            <span style="margin-left: 5px;">共 - ${page.totalCount} - 条</span>
        </div>

        <div class="pagination" targetType="navTab" totalCount="${page.totalCount }" numPerPage="${page.pageSize }"
             pageNumShown="10" currentPage="${page.currentPage }"></div>

    </div>
</div>
