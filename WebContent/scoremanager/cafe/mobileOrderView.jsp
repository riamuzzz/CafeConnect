<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<div class="cafe_user_view">

<%-- ヘッダー --%>
<c:import url="../common/cafeheader.jsp"/>

<div class="main">
<%-- ナビゲーション --%>
<c:import url="../common/cafenavigation.jsp"/>

	<div class="mobileOrderView1">

		<h2>モバイル注文一覧</h2>

		<c:choose>
			<c:when test="${order.size()>0}">

		<form action="MobileUpdateExecute.action" method="post">
			<div class="mobileOrderView2">
					<table>
						<tr>
							<th class="orderTime">日付</th>
							<th class="orderId">注文番号</th>
							<th class="productName">商品名</th>
							<th class="count">個数</th>
							<th class="receive">発送済み</th>

						</tr>
						<c:forEach var="order" items="${order}">
						<c:if test="${order.mobile == true}">

		               	<input type="hidden" name="orderId" value="${ order.orderId }">

							<tr>
								<td class="orderTime">${order.orderTime}</td>
								<td class="orderId">${order.orderId}</td>
								<td class="productName">${order.productName}</td>
								<td class="count">${order.count}</td>

								<td class="receive"><input type="checkbox" name="receive_${order.orderId}" value="${receive}"></td>

							</tr>
							</c:if>
						</c:forEach>
					</table>
					<input type=submit value="変更" class="bottom">
				</form>

				</c:when>
				<c:otherwise>
					<div>商品情報が存在しませんでした</div>
				</c:otherwise>
			</c:choose>
		</div>
	</div>

<%-- フッター --%>
<c:import url="../common/cafefooter.jsp"/>
</div>
</div>
</html>
