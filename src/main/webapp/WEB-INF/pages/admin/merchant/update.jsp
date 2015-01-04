<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/common/taglibs.jsp"%>

<script type="text/javascript" src="${ctx}/js/backend/merchant/add.js?r=1"></script>
<script type="text/javascript">

</script>

<div class="pageContent">
	<form id="addMerchantForm" method="post" action="${ctx}/merchant/update?action=zxzhtj&navTabId=${param.navTabId}" class="pageForm required-validate" onsubmit="return check(this);">
		<input name="id" type="hidden" value="${merchant.id }"/>
		<input id="publishClick" name="publishClick" type="hidden" value="0"/>
		<input name="isPublish" type="hidden" value="${merchant.isPublish}"/>
		<div class="pageFormContent" layoutH="56">
			<fieldset>
				<legend>基本信息</legend>
				<p>
					<label>商家名称：</label>
					<input name="name" class="required" type="text" size="35" maxlength="100" value="${merchant.name}"/>
				</p>
				
				<dl class="nowrap">
					<dt>商家介绍：</dt>
					<dd><textarea style="width:100%;" rows="5" name="description" >${merchant.description}</textarea></dd>
				</dl>
				
				<dl class="nowrap">
					<dt>商家地址：</dt>
					<dd>
						<input name="address" class="required" type="text" size="90" value="${merchant.address}"/>
					</dd>
				</dl>
				
				<p>
					<label>星级：</label>
					<select name="starLevel" class="required combox">
						<option value="1" <c:if test="${1 == merchant.starLevel}">selected="selected"</c:if> >一星级</option>
						<option value="2" <c:if test="${2 == merchant.starLevel}">selected="selected"</c:if> >二星级</option>
						<option value="3" <c:if test="${3 == merchant.starLevel}">selected="selected"</c:if> >三星级</option>
						<option value="4" <c:if test="${4 == merchant.starLevel}">selected="selected"</c:if> >四星级</option>
						<option value="5" <c:if test="${5 == merchant.starLevel}">selected="selected"</c:if> >五星级</option>
					</select>
				</p>
				
				<p>
					<label>是否需要预约：</label>
					<select name="isReservation" class="required combox">
						<option value="0" <c:if test="${0 == merchant.isReservation}">selected="selected"</c:if> >不需要预约</option>
						<option value="1" <c:if test="${1 == merchant.isReservation}">selected="selected"</c:if> >需要预约</option>
					</select>
				</p>
				
				<dl class="nowrap">
					<dt>是否有优惠：</dt>
					<dd>
						<select name="isDiscount" class="required combox">
							<option value="1" <c:if test="${1 == merchant.isDiscount}">selected="selected"</c:if> >有</option>
							<option value="0" <c:if test="${0 == merchant.isDiscount}">selected="selected"</c:if> >无</option>
						</select>
					</dd>
				</dl>
				
				<dl class="nowrap">
					<dt>优惠信息：</dt>
					<dd><textarea name="discountDescription" style="width:100%;" rows="5" >${merchant.discountDescription}</textarea></dd>
				</dl>
				
				<p>
					<label>人均消费：</label>
					<input name="consumPerPerson" type="text" size="20" value="${merchant.consumPerPerson}"/>
				</p>
				
				<dl class="nowrap">
					<dt>商家标签：</dt>
					<dd>
						<input name="label" type="text" size="60" value="${merchant.label}"/>
					</dd>
				</dl>
				
				<p>
					<label>是否置顶：</label>
					<select name="isTop" class="required combox">
						<option value="0" <c:if test="${0 == merchant.isTop}">selected="selected"</c:if> >否</option>
						<option value="1" <c:if test="${1 == merchant.isTop}">selected="selected"</c:if> >是</option>
					</select>
				</p>
			</fieldset>
			
			<fieldset>
				<legend>属性信息</legend>
				<dl class="nowrap">
					<dt>封面图片：</dt>
					<dd>
					  	<input id="coverImage" type="hidden" name="coverImage" value="${merchant.coverImage}"/>
						<input id="coverUploadUrl" type="hidden" value="${ctx}/upload/doUpload?action=zxzhxg"/>
						<input id="coverFile" name="file__merchant/cover" type="file" size="35" />
						<span id="coverUploadStatus" style="color: red; display: ;">
							<c:choose>
								<c:when test="${merchant.coverImage != null}">
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
						<img id="img_cover_id"  width="80" height="80"  src="${resourceCtx}/${merchant.coverImage}" 
								style="display:block;text-align: center;" />
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
					<input id="longitude" name="longitude" type="text" readonly="readonly" size="35" value="${merchant.longitude}" />
				</p>
				<p>
					<label>所在纬度：</label>
					<input id="latitude" name="latitude" type="text" readonly="readonly" size="35"  value="${merchant.latitude}"/>
				</p>
				
				<dl class="nowrap">
					<dt>分类：</dt>
					<dd>
						<select name="categoryId" class="required combox">
							<c:forEach var="c" items="${topLevelCategoryList}">
									<option value="${c.id}" <c:if test="${c.id == merchant.categoryId}">selected="selected"</c:if>>${c.name}</option>
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
