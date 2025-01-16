<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<html>
<%-- ヘッダー --%>
<c:import url="../common/main_header.jsp" />
<div class="main">
	<%-- ナビゲーション --%>
	<c:import url="../common/navigation.jsp" />

	<div class="cartcontent">
		<form action="Settlement.action" method="post">
			<c:forEach var="pList" items="${pList}" varStatus="status">
				<div class="item" data-index="${status.index}">
					<!-- 商品画像 -->
					<div class="image">
						<img src="../img/product/${pList.image}">
					</div>
					<div class="name-price">
						<!-- 商品名 -->
						<div class="name">${pList.productName}</div>
						<!-- 価格 -->
						<div class="price">
							<span class="price" data-index="${status.index}">${pList.price}</span>円
						</div>
						<div class="space"></div>
					</div>
					<!-- 数量選択 -->
					<c:forEach var="cList" items="${cList}">
						<c:if test="${cList.product.productId eq pList.productId}">
							<!-- 初期値にユーザが選択した個数を設定 -->
							<div class="count-delete-button">
								<div class="count">
									個数：<input type="text" name="num${ status.index }"
										value="${cList.count}" class="quantity"
										data-index="${status.index}" onchange="calculateTotal()">
								</div>
								<!-- カート削除ボタン -->
								<div class="delete-button">
									<form action="CartDelete.action" method="post">
										<input type="hidden" name="productId"
											value="${pList.productId}"> <input type="submit"
											value="カートから削除">
									</form>
								</div>
							</div>
							<!-- 商品の選択個数をhiddenで送る -->
							<input type="hidden" name="num${ status.index }" value="${ num }">
							<!-- 商品IDをhiddenを送る -->
							<input type="hidden" name="productId${ status.index }"
								value="${ pList.productId }">
						</c:if>
					</c:forEach>
				</div>
			</c:forEach>
			<!-- 合計個数時間かかるから余裕あったら -->
			<div class="parent-confirm-next">
				<div class="confirm-next">
					<!-- 合計金額を表示 -->
					<div id="totalAmount">合計金額: 0円</div>
					<!-- レジへボタン -->
					<div class="confirm">
						<input type="submit" value="レジへ進む">
					</div>
					<div class="next">
						<a href="ProductView.action">買い物を続ける</a>
					</div>
				</div>
				<div class="space"></div>
			</div>
		</form>
	</div>
</div>
<script src="./js/cartView.js"></script>
<%-- フッター --%>
<c:import url="../common/footer.jsp" />
<!-- navigation.jspの<div class="all">の終了タグ -->
</div>
</html>
