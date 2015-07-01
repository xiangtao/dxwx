<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/common/taglibs.jsp"%>
<!doctype html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>场馆介绍</title>
<script type="text/javascript"
	src="${ctx}/js/common/jquery/jquery-1.7.1.js"></script>
<script type="text/javascript"
	src="http://cdn.amazeui.org/amazeui/2.1.0/js/amazeui.min.js"></script>
<link type="text/css"
	href="http://cdn.amazeui.org/amazeui/2.1.0/css/amazeui.min.css"
	rel="stylesheet"></link>
</head>
<body>
	<div class="am-g am-g-fixed">
		<ul 
			class="am-gallery am-avg-sm-2
  am-avg-md-3 am-avg-lg-4 am-gallery-default"
			data-am-gallery="{ pureview: true }">
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

</body>
</html>