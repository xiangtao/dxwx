<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/common/taglibs.jsp"%>

<script type="text/javascript" src="${ctx}/js/backend/merchant/add.js"></script>
<script type="text/javascript">
</script>

<div class="pageContent">
    
    <div id="allmap"></div>
    
    
	<form id="addMerchantForm" method="post" action="${ctx}/merchant/add?action=zxzhtj&navTabId=${param.navTabId}" class="pageForm required-validate" onsubmit="return check(this);">
		<input id="publishClick" name="publishClick" type="hidden" value="0"/>
		<input name="isPublish" type="hidden" value="0"/>
		
		<div class="pageFormContent" layoutH="56">
			<fieldset>
				<legend>基本信息</legend>
				<p>
					<label>商家名称：</label>
					<input name="name" class="required" type="text" size="35" maxlength="100"/>
				</p>
				
				<dl class="nowrap">
					<dt>商家介绍：</dt>
					<dd><textarea style="width:100%;" rows="5" name="description" ></textarea></dd>
				</dl>
				
				<dl class="nowrap">
					<dt>商家地址：</dt>
					<dd>
						<input name="address" class="required" type="text" size="90" />
					</dd>
				</dl>
				
				<p>
					<label>星级：</label>
					<select name="starLevel" class="required combox">
						<option value="1">一星级</option>
						<option value="2">二星级</option>
						<option value="3">三星级</option>
						<option value="4">四星级</option>
						<option value="5">五星级</option>
					</select>
				</p>
				
				<p>
					<label>是否需要预约：</label>
					<select name="isReservation" class="required combox">
						<option value="0">不需要预约</option>
						<option value="1">需要预约</option>
					</select>
				</p>
				
				<dl class="nowrap">
					<dt>是否有优惠：</dt>
					<dd>
						<select name="isDiscount" class="required combox">
							<option value="1">有</option>
							<option value="0">无</option>
						</select>
					</dd>
				</dl>
				
				<dl class="nowrap">
					<dt>优惠信息：</dt>
					<dd><textarea name="discountDescription" style="width:100%;" rows="5"  ></textarea></dd>
				</dl>
				
				<p>
					<label>人均消费：</label>
					<input name="consumPerPerson" type="text" size="20" value=""/>
				</p>
				
				<dl class="nowrap">
					<dt>商家标签：</dt>
					<dd>
						<input name="label" type="text" size="60" value=""/>
					</dd>
				</dl>
				
				<p>
					<label>是否置顶：</label>
					<select name="isTop" class="required combox">
						<option value="0">否</option>
						<option value="1">是</option>
					</select>
				</p>
			</fieldset>
			
			<fieldset>
				<legend>属性信息</legend>
				<dl class="nowrap">
					<dt>封面图片：</dt>
					<dd>
					  	<input id="coverImage" type="hidden" name="coverImage"/>
						<input id="coverUploadUrl" type="hidden" value="${ctx}/upload/doUpload?action=zxzhxg"/>
						<input id="coverFile" name="file__merchant/cover" type="file" size="35" />
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
					<dt>所在省/直辖市：</dt>
					<dd>
						<select name="incity1" class="required combox">
							 <option value="1">北京</option>
						</select>
					</dd>
				</dl>
				
				<dl class="nowrap">
					<dt>设置经纬度：</dt>
					<dd>
						<input type="button" value="设置经纬度" onclick="settingMap();"/>
					</dd>
				</dl>
				
				
				<p>
					<label>所在经度：</label>
					<input id="longitude" name="longitude" type="text" size="35"  class="required" readonly="readonly"/>
				</p>
				<p>
					<label>所在纬度：</label>
					<input id="latitude" name="latitude" type="text" size="35"  class="required" readonly="readonly" />
				</p>
				
				<dl class="nowrap">
					<dt>分类：</dt>
					<dd>
						<select name="categoryId" class="required combox">
							<c:forEach var="c" items="${topLevelCategoryList}">
								<option value="${c.id}">${c.name}</option>
							</c:forEach>
						</select>
					</dd>
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
