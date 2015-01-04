<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/common/taglibs.jsp"%>

		<thead>
			<tr align="center">
				<th width="140">订单编号</th>
				<th width="70">产品类型</th>
				<th width="60">交易数量</th>
				<th width="80">交易金额</th>
				<th width="90">收货人员</th>
				<th width="100">收货电话</th>
				<th width="100">下单会员</th>
				<th width="60">订单状态</th>
				<th width="25">详情</th>
				<th width="25">处理</th>
			</tr>
		</thead>
		<tbody>
			<s:iterator value="#request.lastedOrderList" var="item">
				<tr target="sid" rel="${item.id}" align="center">
					<td title="${item.uuid}">${item.uuid}</td>
					<td>${item.typeStr}&nbsp;</td>
					<td>${item.count}</td>
					<td>${item.money}</td>
					<td title="${item.name}">${item.name}</td>
					<td title="${item.tel}">${item.tel}</td>
					<td title="${item.userPhone}">${item.userPhone}</td>
					<td>${item.statusStr}&nbsp;</td>
					<td>
						<a class="btnInfo" title="${item.uuid}-订单详情" target="dialog" rel="dialog_${item.id}" mask="false" minable="true"
							href="${ctx}/admin/order/list/showDetail.html?order.id=${item.id}&action=zdylbzs"
							width="920" height="520">查  看</a>
					</td>
					<td>
						<a class="btnAttach" title="${item.uuid}" target="navTab" rel="order_${item.id}" 
							href="${ctx}/admin/order/list/showIndex.html?id=${item.id}&navTabId=order_${item.id}&action=zdylbzs">处理</a>
					</td>
				</tr>
			</s:iterator>
		</tbody>