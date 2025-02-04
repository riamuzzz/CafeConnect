<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<link rel="stylesheet"  href="../../css/settlement.css">
<div class="productView">


	<%-- ヘッダー --%>
	<c:import url="../common/main_header.jsp" />

	<div class="main">
		<%-- ナビゲーション --%>
		<c:import url="../common/navigation.jsp" />

		<div class="content">
			<form action="SettlementExecute.action" method="post">

				<h2>${category.categoryName }</h2>

				<table class="pay">
					<tr>
						<th>お届け先</th>
					</tr>
					<tr>
						<td>氏名：${ user.userName }</td>
					</tr>
					<tr>
						<td>住所：${ user.address }</td>
					</tr>

					<tr>
						<td><a href="MyPage.action" id="update">変更</a></td>
					</tr>

				</table>

				<table class="pay">
					<tr>
						<th>お支払方法</th>
					</tr>
					<tr>
						<td>カード番号：${ card.cardNumber }</td>
					</tr>
					<tr>
						<td>カード氏名：${ card.cardName }</td>
					</tr>
					<tr>
						<td>有効期限：${ card.cardExpiryDate }</td>
					</tr>

					<tr>
						<td><a href="MyPage.action" id="update">変更</a>
						<td>
					</tr>

				</table>

				<p id="orderProduct">注文商品</p>
				<table class="kessai">
					<c:set var="totalPrice" value="0" />

					<c:forEach var="pList" items="${pList}" varStatus="status">

						<tr class="item" data-index="${status.index}">

							<!-- 商品画像 -->
							<th><img src="../img/product/${pList.image}"
								alt="${pList.productName}"></th>
							<!-- 商品名 -->
							<td>${pList.productName}<br><br>

							<!-- 価格 -->
							<span id="price"><strong>￥${pList.price}</strong></span><br><br>
							<!-- 個数 -->
							<c:forEach var="cList" items="${cList}"
									varStatus="status">
									<c:if test="${cList.product.productId eq pList.productId}">
										<span class="count">${cList.count}個</span>
										<!-- 商品IDを送る -->
										<input type="hidden" name="productId${ status.index }"
											value="${ pList.productId }">
										<input type="hidden" name="count${status.index}" value="${cList.count}">
										<!-- 合計金額を計算 -->
										<c:set var="itemTotal" value="${pList.price * cList.count}" />
										<c:set var="totalPrice" value="${totalPrice + itemTotal}" />
									</c:if>
								</c:forEach></td>
					</c:forEach>
				</table>
				<br>
				<table class="payment">
					<tr>
						<td>ご請求額: <strong>${totalPrice}</strong>円
						</td>
					</tr>
					</table>

				<input type="submit" value="注文を確定する" class="subKessai">

			</form>
			<form action="CartView.action" method="post">
				<input type="submit" value="カートに戻る" class="cartBack">
			</form>
		</div>

		<%-- フッター --%>
		<c:import url="../common/footer.jsp" />
	</div>
</div>
</html>
