<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/common/taglibs.jsp"%>

<div class="accordion" layoutH="35">
	<ul class="tree treeFolder treeCheck expand">
		${allPrivHtml}
	</ul>
</div>
<div class="formBar">
	<ul>
		<li><div class="buttonActive"><div class="buttonContent"><button type="button" onclick="savePrivs()">保存</button></div></div></li>
		<li><div class="button"><div class="buttonContent"><button type="button" class="close">取消</button></div></div></li>
	</ul>
</div>

<script type="text/javascript">
	function savePrivs() {
		var chkPrivs = $("div[class='ckbox checked'],div[class='ckbox indeterminate']");
		if(chkPrivs.length) {
			var url = "${ctx}/role/ajax/setPriv?action=zxqxfp";
			var msg = "您确认分配这 - " + chkPrivs.length + " - 项权限吗？";
			var privsIds = "";	// 需设置的权限的ID集合
			for(var i = 0; i < chkPrivs.length; i++) {
				privsIds += $(chkPrivs[i]).children().eq(0).val() + ",";
			}
			privsIds = privsIds.substring(0, privsIds.length - 1);
			var data = {
				"id" : "${role.id}",
				"privsIds" : privsIds,
			};
			// Ajax保存确认
			confirm2Ajax(url, msg, data, 
				function(json){
					DWZ.ajaxDone(json);
					if(json.statusCode == DWZ.statusCode.ok) {
						$.pdialog.closeCurrent(); 
					}
				}, "json"
			);
		} else {
			alertErr("请选择需要分配的权限！");
		}
	}
</script>
