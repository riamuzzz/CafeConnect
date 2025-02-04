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

		<div class="cartcontent">
			<h1>カート</h1>
			<c:choose>
			<c:when test="${not empty pList}">
			<form id="myForm" method="post" accept-charset="UTF-8">
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
										<input type="number" name="num${ status.index }"
											value="${cList.count}" min="1" max="${pList.count }" class="quantity"
											data-index="${status.index}" onchange="calculateTotal()" required>
										<input type="hidden" name="num${ status.index }"
											value="${ num }">
										<!-- 商品IDをhiddenを送る -->
										<input type="hidden" name="productId${ status.index }"
											value="${ pList.productId }">
									</c:if>
								</c:forEach></td>

							<!-- カート削除ボタン -->
							<td id="del">
							<a href="CartDelete.action?productId=${ pList.productId }" style="color:blue; white-space: nowrap;">カートから削除</a>

							</td>
						</tr>
					</c:forEach>
					</table>

					<!-- 合計金額を表示 -->
					<table id="hoge">
					<tr>
						<td id="kin">合計金額：</td>
						<td id="totalAmount" style="width:30px;">${total}</td>
						<td id="kin">円</td>
					</tr>
					<tr>
						<td>
						<!-- レジへボタン -->
							<input type="submit" value="レジへ進む"
								onclick="changeAction('Settlement.action')" id="regi">
						<td>
					</tr>
					</table>
					</form>

			</c:when>
			<c:otherwise>
				<p>オンラインショップのカートの中身は空です</p>
			</c:otherwise>
			</c:choose>
		</div>

	</div>
</div>
<script src="./js/cartView.js"></script>
<%-- フッター --%>
<c:import url="../common/footer.jsp" />


</html>
