<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/common/taglibs.jsp"%>

<script type="text/javascript" src="${ctx}/js/backend/goods/add.js"></script>
<script type="text/javascript">

</script>

<div class="pageContent">
	<form id="addGoodsForm" method="post" action="${ctx}/merchant/goods/add?action=zxzhtj&navTabId=${param.navTabId}" class="pageForm required-validate" onsubmit="return check(this);">
		<div class="pageFormContent" layoutH="56">
			<fieldset>
				<legend>商品信息</legend>
				<p>
					<label>商品名称：</label>
					<input name="merchantId" value="${param.merchantId}" type="hidden"/>
					<input name="isPublish" type="hidden"/>
					<input name="name" class="required" type="text" size="35" maxlength="100"/>
				</p>
				<p>
					<label>商品原价：</label>
					<input name="srcPrice" class="required number" type="text" size="35" maxlength="100"/>
				</p>
				<p>
					<label>商品现价：</label>
					<input name="nowPrice" class="required number" type="text" size="35" maxlength="100"/>
				</p>
				<p>
					<label>商品折扣：</label>
					<input name="disount" class="required number" type="text" size="35" maxlength="100"/>
				</p>
				<dl class="nowrap">
					<dt>优惠图片：</dt>
					<dd>
					  	<input id="coverImage" type="hidden" name="coverImage"/>
						<input id="coverUploadUrl" type="hidden" value="${ctx}/upload/doUpload?action=zxzhxg"/>
						<input id="coverFile" name="file__goods" type="file" size="35" />
						<span id="coverUploadStatus" style="color: red; display: none;">&nbsp;&nbsp;&nbsp;&nbsp;未上传</span>
					</dd>
				</dl>
				
				<dl class="nowrap">
					<dt>&nbsp;&nbsp;&nbsp;&nbsp;</dt>
					<dd>
						<img id="img_cover_id"  width="80" height="80"  src="" style="display:none;text-align: center;" />
					</dd>
				</dl>
				
				<dl class="nowrap">
					<dt>是否置顶：</dt>
					<dd><input type="checkbox" name="isTop" /></dd>
				</dl>
				<dl class="nowrap">
					<dt>商品描述：</dt>
					<dd><textarea style="width:100%;" rows="5" name="content" ></textarea></dd>
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
