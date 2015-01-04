<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/common/taglibs.jsp"%>

<script type="text/javascript">
	$(document).ready(function() {
		$("#parseSpan").on("click", function() {
			var address = $(":input[name='address']").val();
			if(address) {
				var url = "merchant/store/parseAddr";
				var params = {
					"address" : address
				};
				$.post(url, params, function(data) {
					if(data.success) {
						$(":input[name='longitude']").val(data.lng);
						$(":input[name='latitude']").val(data.lat);
					} else {
						$(":input[name='longitude']").val("");
						$(":input[name='latitude']").val("");
						alertErr("抱歉，解析失败");
					}
				});
			} else {
				alertWarn("请输入地址");
			}
		});
	});
</script>

<div class="pageContent">
    <form id="addStoreForm" method="post" action="${ctx}/merchant/store/add?action=zxzhtj&navTabId=${param.navTabId}" class="pageForm required-validate" onsubmit="return validateCallback(this, dialogAjaxDone);">
        <div class="pageFormContent" layoutH="56">
            <fieldset>
                <legend>分店信息</legend>
                <p>
                    <label>分店名称：</label>
                    <input name="merchantId" value="${param.merchantId}" type="hidden"/>
                    <input name="name" class="required" type="text" size="35" maxlength="100" />
                </p>
                <p>
                    <label>固定电话：</label>
                    <input name="phone" class="required phone" type="text" size="35" maxlength="13" />
                </p>
                <p>
                    <label>手机号码：</label>
                    <input name="mobile" class="required phone" type="text" size="35" maxlength="11" />
                </p>
                <p>
                    <label>分店地址：</label>
                    <input name="address" class="required" type="text" size="35" maxlength="100" />
                    <span id="parseSpan" style="color: blue; cursor: pointer; margin-left: 8px;">&nbsp;解析</span>
                </p>
                <p>
                    <label>地理经度：</label>
                    <input name="longitude" class="required number" type="text" size="35" maxlength="12" />
                </p>
                <p>
                    <label>地理纬度：</label>
                    <input name="latitude" class="required number" type="text" size="35" maxlength="12" />
                </p>
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
