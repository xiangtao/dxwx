<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/common/taglibs.jsp"%>

<script type="text/javascript" src="${ctx}/js/backend/goods/add.js"></script>
<script type="text/javascript">

</script>

<div class="pageContent">
	<form id="addGoodsForm" method="post" action="${ctx}/merchant/goods/update?action=zxzhtj&navTabId=${param.navTabId}" class="pageForm required-validate" onsubmit="return check(this);">
		<div class="pageFormContent" layoutH="56">
			<fieldset>
				<legend>商品信息</legend>
				<p>
					<label>商品名称：</label>
					<input name="id" type="hidden" value="${goods.id}" />
					<input name="isPublish" type="hidden"/>
					<input name="name" class="required" type="text" size="35" maxlength="100" value="${goods.name}"/>
				</p>
				<p>
					<label>商品原价：</label>
					<input name="srcPrice" class="required number" type="text" size="35" maxlength="100" value="${goods.srcPrice}"/>
				</p>
				<p>
					<label>商品现价：</label>
					<input name="nowPrice" class="required number" type="text" size="35" maxlength="100" value="${goods.nowPrice}"/>
				</p>
				<p>
					<label>商品折扣：</label>
					<input name="disount" class="required number" type="text" size="35" maxlength="100" value="${goods.disount}"/>
				</p>
				<dl class="nowrap">
					<dt>优惠图片：</dt>
					<dd>
					  	<input id="coverImage" type="hidden" name="coverImage" value="${goods.coverImage}"/>
						<input id="coverUploadUrl" type="hidden" value="${ctx}/upload/doUpload?action=zxzhxg"/>
						<input id="coverFile" name="file__goods" type="file" size="35" />
						<span id="coverUploadStatus" style="color: red; display: ;">
							<c:choose>
								<c:when test="${goods.coverImage != null && goods.coverImage != ''}">
									&nbsp;&nbsp;&nbsp;&nbsp;已上传
								</c:when>
								<c:otherwise>
									&nbsp;&nbsp;&nbsp;&nbsp;未上传
								</c:otherwise>
							</c:choose>
						</span>
					</dd>
				</dl>
				
				<dl class="nowrap">
					<dt>&nbsp;&nbsp;&nbsp;&nbsp;</dt>
                    <dd>
                        <c:choose>
                            <c:when test="${goods.coverImage != null && goods.coverImage != ''}">
                                <img id="img_cover_id"  width="80" height="80"  src="${resourceCtx}/${goods.coverImage}" style="text-align: center;" />
                            </c:when>
                            <c:otherwise>
                                <img id="img_cover_id"  width="80" height="80"  src="${ctx}/images/backend/no_photo.jpg" style="text-align: center;" />
                            </c:otherwise>
                        </c:choose>
					</dd>
				</dl>
				
				<dl class="nowrap">
					<dt>是否置顶：</dt>
					<dd><input type="checkbox" name="isTop" <c:if test="${goods.isTop}">checked="checked"</c:if>/></dd>
				</dl>
				<dl class="nowrap">
					<dt>商品描述：</dt>
					<dd><textarea style="width:100%;" rows="5" name="content" >${goods.content}</textarea></dd>
				</dl>
			</fieldset>
		</div>
		<div class="formBar">
			<ul>
				<li><div class="button"><div class="buttonContent"><button id="publishBtn" type="button">发布</button></div></div></li>
				<li><div class="button"><div class="buttonContent"><button id="saveBtn" type="button">保存</button></div></div></li>
				<li><div class="button"><div class="buttonContent"><button type="button" class="close">取消</button></div></div></li>
			</ul>
		</div>
	</form>
</div>
