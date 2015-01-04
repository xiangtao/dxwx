$(document).ready(function() {

	/** 修复当下拉列表框为显示状态时，关闭窗口，下拉框不消退的Bug **/
	$(".close").on("click", function() {
		$(".comboxop").hide("fast");
	});

	/** 商家图片上传确认 **/
	$("#coverFile").live("change", function() {
		if(!doValidateFileSuf($(this).val(),"jpg,jpeg,png,gif")){
			alert("请上传jpg,jpeg,png,gif格式文件");
			return;
		}
		alertMsg.confirm("确认上传商家图片 - " + $(this).val() + " - 吗？", {
			okCall : function() {
				$.ajaxFileUpload({
					url : $("#coverUploadUrl").val(),
					secureuri : false,
					fileElementId : "coverFile",
					dataType : "json",
					type:'post',
					success : function(json, status) {
						if(json.statusCode == 200) {
							$("#coverImage").val(json.files[0].relativePath);
							$("#coverUploadStatus").html("&nbsp;&nbsp;&nbsp;&nbsp;已上传");
							$("#coverUploadStatus").show("slow");
							$("#img_cover_id").attr('src', resourceCtx + "/" + json.files[0].relativePath);
							$("#img_cover_id").css("display", "block");
							alertSuc("商家图片上传成功，如需更改请直接重新上传！");
						} else {
							dialogAjaxDone(json);
						}
					},
					error: function (data, status, e){//服务器响应失败处理函数
                        alert(e);
                    }
				});
			}
		});
	});
});

/** 数据校验 **/
function check(obj) {
	var coverImage = $("#coverImage").val();
	if(coverImage == undefined || coverImage == "") {
		$("#coverFile").addClass("error");
		$("#coverUploadStatus").show("slow");
		return false;
	}
	return validateCallback(obj, dialogAjaxDone);
}

function doValidateFileSuf(filename,filetype){
	var sufix = filename.substring(filename.lastIndexOf(".")+1);
	sufix = sufix==null?"":sufix.toLowerCase();
	if(sufix == "") return false;
	
	var exists = false;
	var filetypes = filetype.split(",");
	for(var i=0;i<filetypes.length;i++){
		var ft = filetypes[i];
		if(sufix == ft){
			exists = true;
			break;
		}
	}
	return exists;
}
