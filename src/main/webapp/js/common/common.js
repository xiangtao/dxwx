



//将时间戳转换成日期
// yyyy-MM-dd hh:mm:ss.S ==> 2006-07-02 08:09:04.423   
// yyyy-M-d h:m:s.S      ==> 2006-7-2 8:9:4.18
Number.prototype.parse = function(fmt){
	if(undefined ==this || "" == this){
		return "";
	}
	if(!(new RegExp(/^[1-9]\d*$/).test(this))){
		return "";
	}
	var date = new Date();
	date.setTime(this);
	
	return date.format(fmt);
}
//格式化日期
Date.prototype.format = function(fmt){  
  var o = {   
    "M+" : this.getMonth()+1,                 //月份   
    "d+" : this.getDate(),                    //日   
    "h+" : this.getHours(),                   //小时   
    "m+" : this.getMinutes(),                 //分   
    "s+" : this.getSeconds(),                 //秒   
    "q+" : Math.floor((this.getMonth()+3)/3), //季度   
    "S"  : this.getMilliseconds()             //毫秒   
  };   
  if(/(y+)/.test(fmt))   
    fmt=fmt.replace(RegExp.$1, (this.getFullYear()+"").substr(4 - RegExp.$1.length));   
  for(var k in o)   
    if(new RegExp("("+ k +")").test(fmt))   
  fmt = fmt.replace(RegExp.$1, (RegExp.$1.length==1) ? (o[k]) : (("00"+ o[k]).substr((""+ o[k]).length)));   
  return fmt;   
}

function showLoading(){
	$.mobile.loading( "show", {
		text: "",
		textVisible: false,
		theme: $.mobile.loader.prototype.options.theme,
		textonly: false,
		html: ""
	});
}

function closeLoading(){
	$.mobile.loading( "hide" );
}