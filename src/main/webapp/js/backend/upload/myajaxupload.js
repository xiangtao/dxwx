/* 异步上传文件 */
function ajaxFileUpload() {
	$.ajaxFileUpload( {
		url : $("#uploadUrl").val(),
		secureuri : false,
		fileElementId : "picFile",
		dataType : "json",
		success : function(json, status) {
			if(json.statusCode == 200) {
				$("#imgUrl").val(json.message);
				alertSuc("上传成功！");
			} else {
				dialogAjaxDone(json);
			}
		},
		error : function(data, status, e) {
			alert(e);
		}
	});
	return false;
}