<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/common/taglibs.jsp"%>

<script type="text/javascript" src="${ctx}/js/backend/images/add.js"></script>
<script type="text/javascript">

</script>

<div class="pageContent">
	<form id="addImagesForm" method="post" action="${ctx}/merchant/images/add?action=zxzhtj&navTabId=${param.navTabId}" class="pageForm required-validate" onsubmit="return check(this);">
		<div class="pageFormContent" layoutH="56">
            <input type="hidden" name="merchantId" value="${param.merchantId}"/>
			<fieldset>
				<legend>商家图片上传</legend>
				<dl class="nowrap">
					<dt>选择图片：</dt>
					<dd>
					  	<input id="coverImage" type="hidden" name="image"/>
						<input id="coverUploadUrl" type="hidden" value="${ctx}/upload/doUpload?action=zxzhxg"/>
						<input id="coverFile" name="file__merchant/cover" type="file" size="35" />
						<span id="coverUploadStatus" style="color: red; display: none;">&nbsp;&nbsp;&nbsp;&nbsp;未上传</span>
					</dd>
				</dl>
                <dl class="nowrap">
                    <dt>是否封面：</dt>
                    <dd><input type="checkbox" name="isCover" value="1"/></dd>
                </dl>
				
				<dl class="nowrap">
					<dd>
						<img id="img_cover_id"  width="150" height="150"  src="" style="display:none;text-align: center;" />
					</dd>
				</dl>
			</fieldset>
		</div>
		<div class="formBar">
			<ul>
				<li><div class="button"><div class="buttonContent"><button type="submit">保存</button></div></div></li>
				<li><div class="button"><div class="buttonContent"><button type="button" class="close">取消</button></div></div></li>
			</ul>
		</div>
	</form>
</div>
