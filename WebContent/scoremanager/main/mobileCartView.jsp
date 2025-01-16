<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<html>
<link rel='stylesheet' href='../../css/mao.css'>

<div class="cartView">
	<%-- ヘッダー --%>
	<c:import url="../common/main_header.jsp" />
	<div class="main">
		<%-- ナビゲーション --%>
		<c:import url="../common/navigation.jsp" />

		<div class="cartcontent">
			<h1>カート</h1>
			<form action="Settlement.action" method="post">
				<table id="item-table">
					<c:forEach var="pList" items="${pList}" varStatus="status">
						<tr class="item" data-index="${status.index}">

							<!-- 商品画像 -->
							<td id="img"><img src="../img/product/${pList.image}"
								alt="${pList.productName}" id=img></td>

							<!-- 商品名 -->
							<td id="productName">${pList.productName}</td>

							<!-- 価格 -->
							<td id="price"><span class="price"
								data-index="${status.index}">${pList.price}</span>円</td>
							<!-- 数量選択 -->
							<td id="numc"><c:forEach var="cList" items="${cList}">
									<c:if test="${cList.product.productId eq pList.productId}">
										<input type="text" name="num${ status.index }"
											value="${cList.count}" class="quantity"
											data-index="${status.index}" onchange="calculateTotal()">
										<input type="hidden" name="num${ status.index }"
											value="${ num }">
										<!-- 商品IDをhiddenを送る -->
										<input type="hidden" name="productId${ status.index }"
											value="${ pList.productId }">
									</c:if>
								</c:forEach></td>

							<!-- カート削除ボタン -->
							<td id="del">
								<form action="CartDelete.action" method="post">
									<input type="hidden" name="productId"
										value="${pList.productId}"> <input type="submit"
										value="カートから削除">
								</form>
							</td>
						</tr>
					</c:forEach>
					<!-- 合計金額を表示 -->
					<td id="totalAmount">合計金額: 0円</td>
					<!-- レジへボタン -->
				</table>
				<input type="submit" value="レジへ進む" id="regi">
			</form>
		</div>

	</div>
</div>
<script src="./js/cartView.js"></script>
<%-- フッター --%>
<c:import url="../common/footer.jsp" />


</html>
