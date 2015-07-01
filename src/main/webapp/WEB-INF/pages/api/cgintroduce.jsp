<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!doctype html>
<html class="no-js">
<head>
  <meta charset="utf-8">
  <meta http-equiv="X-UA-Compatible" content="IE=edge">
  <meta name="description" content="">
  <meta name="keywords" content="">
  <meta name="viewport"
        content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no">
  <title>场馆介绍</title>

  <!-- Set render engine for 360 browser -->
  <meta name="renderer" content="webkit">

  <!-- No Baidu Siteapp-->
  <meta http-equiv="Cache-Control" content="no-siteapp"/>

  <link rel="icon" type="image/png" href="assets/i/favicon.png">

  <!-- Add to homescreen for Chrome on Android -->
  <meta name="mobile-web-app-capable" content="yes">
  <link rel="icon" sizes="192x192" href="assets/i/app-icon72x72@2x.png">

  <!-- Add to homescreen for Safari on iOS -->
  <meta name="apple-mobile-web-app-capable" content="yes">
  <meta name="apple-mobile-web-app-status-bar-style" content="black">
  <meta name="apple-mobile-web-app-title" content="Amaze UI"/>
  <link rel="apple-touch-icon-precomposed" href="assets/i/app-icon72x72@2x.png">

  <!-- Tile icon for Win8 (144x144 + tile color) -->
  <meta name="msapplication-TileImage" content="assets/i/app-icon72x72@2x.png">
  <meta name="msapplication-TileColor" content="#0e90d2">

  <!-- SEO: If your mobile URL is different from the desktop URL, add a canonical link to the desktop page https://developers.google.com/webmasters/smartphone-sites/feature-phones -->
  <!--
  <link rel="canonical" href="http://www.example.com/">
  -->

  <link rel="stylesheet" href="http://cdn.amazeui.org/amazeui/2.1.0/css/amazeui.min.css">
</head>
<body>
<!--[if lte IE 9]>
<p class="browsehappy">你正在使用<strong>过时</strong>的浏览器，Amaze UI 暂不支持。 请 <a
  href="http://browsehappy.com/" target="_blank">升级浏览器</a>
  以获得更好的体验！</p>
<![endif]-->

<!-- 页面内容 开发时删除 -->
<div class="am-g am-g-fixed">
		<ul 
			class="am-gallery am-avg-sm-2
  am-avg-md-3 am-avg-lg-4 am-gallery-default">
			<li>
				<div class="am-gallery-item">
					<a
						href="${ctx}/api/dxwx/showIntroDetail?code=10"
						class=""> <img
						src="${ctx}/images/backend/image_10.png" />
						<!-- <h3 class="am-gallery-title">远方 有一个地方 那里种有我们的梦想</h3>
						<div class="am-gallery-desc">2375-09-26</div> -->
					</a>
				</div>
			</li>
			<li>
				<div class="am-gallery-item">
					<a
						href="${ctx}/api/dxwx/showIntroDetail?code=11"
						class=""> <img
						src="${ctx}/images/backend/image_11.png" />
					</a>
				</div>
			</li>
			<li>
				<div class="am-gallery-item">
					<a
						href="${ctx}/api/dxwx/showIntroDetail?code=12"
						class=""> <img
						src="${ctx}/images/backend/image_12.png" />
					</a>
				</div>
			</li>
			<li>
				<div class="am-gallery-item">
					<a
						href="${ctx}/api/dxwx/showIntroDetail?code=13"
						class=""> <img
						src="${ctx}/images/backend/image_13.png" />
					</a>
				</div>
			</li>
			<li>
				<div class="am-gallery-item">
					<a
						href="${ctx}/api/dxwx/showIntroDetail?code=14"
						class=""> <img
						src="${ctx}/images/backend/image_14.png" />
					</a>
				</div>
			</li>
			<li>
				<div class="am-gallery-item">
					<a
						href="${ctx}/api/dxwx/showIntroDetail?code=15"
						class=""> <img
						src="${ctx}/images/backend/image_15.png" />
					</a>
				</div>
			</li>
			<li>
				<div class="am-gallery-item">
					<a
						href="${ctx}/api/dxwx/showIntroDetail?code=16"
						class=""> <img
						src="${ctx}/images/backend/image_16.png" />
					</a>
				</div>
			</li>
		</ul>
</div>

<!--[if lt IE 9]>
<script src="http://libs.baidu.com/jquery/1.11.1/jquery.min.js"></script>
<script src="http://cdn.staticfile.org/modernizr/2.8.3/modernizr.js"></script>
<script src="assets/js/polyfill/rem.min.js"></script>
<script src="assets/js/polyfill/respond.min.js"></script>
<script src="assets/js/amazeui.legacy.js"></script>
<![endif]-->

<!--[if (gte IE 9)|!(IE)]><!-->
<script src="${ctx}/js/common/jquery/jquery-1.7.1.js"></script>
<script src="http://cdn.amazeui.org/amazeui/2.1.0/js/amazeui.min.js"></script>
<!--<![endif]-->

</body>
</html>
