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

	<h2>モバイル注文一覧</h2>

	<c:choose>
		<c:when test="${order.size()>0}">

	<form action="OrderUpdateExecute.action" method="post">

			<table>
				<tr>
					<th>日付</th>
					<th>注文番号</th>
					<th>商品名</th>
					<th>個数</th>
					<th>発送済み</th>

				</tr>
				<c:forEach var="order" items="${order}">
               	<input type="hidden" name="orderId" value="${ order.orderId }">

					<tr>
						<td>${order.orderTime}</td>
						<td>${order.orderId}</td>
						<td>${order.productName}</td>
						<td>${order.count}</td>

						<td><input type="checkbox" name="receive_${order.orderId}" value="${receive}"></td>

					</tr>
				</c:forEach>
			</table>
			<input type=submit value="変更">
		</form>

		</c:when>
		<c:otherwise>
			<div>商品情報が存在しませんでした</div>
		</c:otherwise>
	</c:choose>

<%-- フッター --%>
<c:import url="../common/cafefooter.jsp"/>
</div>
</div>
</html>
