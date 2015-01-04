$(document).ready(function(){
	initMediaPlayer();
});
	
	

function initMediaPlayer(){
	$('audio').mediaelementplayer({
	    audioWidth: 130,
	    audioHeight: 30,
	    features: ['playpause','current','duration','tracks']
	});
}

function _startPlay(obj,id,url){
	url = url + "?r=" + Math.random();
	var rndStr = Math.random().toString();
	rndStr = rndStr.substring(2);
	var audioElem = "<audio id=\"audio_player_"+id +"_" + rndStr +"\" src=\""+url+"\" type=\"audio/mp3\" controls=\"controls\">浏览器不支持</audio>";
	$(obj).after(audioElem);
	$(obj).text("加载中...");
	var mdediaPlayer = new MediaElementPlayer('#audio_player_'+id+'_'+rndStr, {
		audioWidth:130,
		audioHeight:30,
		features: ['playpause','current','duration','tracks']
	});
	mdediaPlayer.load();
	//加载完后隐藏
	$(obj).hide();
	mdediaPlayer.play();
}


/** 文章朗读转码 * */
function articleReadTranscoding(id) {
	$.post(ctx + "/transcoding/articleRead", {
		"id" : id
	}, function(json) {
		if(json.result == "success") {
			var audioElement = "<audio src='" + resourceCtx + "/" + json.msg + "' type='audio/mp3' controls='controls'/>";
			$("#transcodingBtn_" + id).replaceWith(audioElement);
			initMediaPlayer();
		} else {
			alertErr(json.msg);
		}
	}, "json");
}

/** 自由朗读转码 * */
function freeReadTranscoding(id) {
	$.post(ctx + "/transcoding/freeRead", {
		"id" : id
	}, function(json) {
		if(json.result == "success") {
			var audioElement = "<audio src='" + resourceCtx + "/" + json.msg + "' type='audio/mp3' controls='controls'/>";
			$("#transcodingBtn_" + id).replaceWith(audioElement);
			initMediaPlayer();
		} else {
			alertErr(json.msg);
		}
	}, "json");
}

/** 标准朗读音转码 * */
function standardReadTranscoding(id) {
	$.post(ctx + "/transcoding/standardRead", {
		"id" : id
	}, function(json) {
		if(json.result == "success") {
			$("#transcodingBtn_" + id).replaceWith("已转码");
		} else {
			alertErr(json.msg);
		}
	}, "json");
}