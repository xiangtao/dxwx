/* 消息提示框 */
function showMsg(msg, time) {
	art.dialog( {
		title : "温馨提示",
		content : msg,
		left : '50%',
		top : '50%',
		icon : 'face-smile',
		time : time,
	    drag : false,
	    fixed : true,
		close : function() {
			art.dialog.close();
		}
	}).lock();
}

/* 在对话框中的Ajax删除(删除后要手动刷新Dialog) */
function delAjaxInDialog(url, dialogId, msg) {
	confirm2Ajax(url, msg, null, function(json) {
		DWZ.ajaxDone(json);
		// 刷新Dialog
		$.pdialog.reloadDialog(json.dialogId);
	}, "json");
}

/* 打印数据操作 */
function printData() {
	window.print();
}