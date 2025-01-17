<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<div class="productView">


<%-- ヘッダー --%>
<c:import url="../common/main_header.jsp"/>

<div class="main">
<%-- ナビゲーション --%>
<c:import url="../common/navigation.jsp"/>

<div class="content">
	<form action="SettlementExecute.action" method="post">

	<h2>${category.categoryName }</h2>

	<table>
			<tr><td>お届け先：${ user.userName }</td></tr>
			<tr><td>${ user.address }</td></tr>

			<tr><td><a href="MyPage.action">変更</a><td></tr>

	</table>

	<table>
			<tr><td>お支払方法：${ card.cardNumber }</td></tr>
			<tr><td>カード氏名：${ card.cardName }</td></tr>
			<tr><td>有効期限：${ card.cardExpiryDate }</td></tr>

			<tr><td><a href="MyPage.action">変更</a><td></tr>

	</table>

	<table>

<c:set var="totalPrice" value="0" />

<c:forEach var="pList" items="${pList}" varStatus="status">
  <tr class="item" data-index="${status.index}">

    <!-- 商品画像 -->
    <td>
      <img src="../img/product/${pList.image}" alt="${pList.productName}">
    </td>
    <!-- 商品名 -->
    <td>${pList.productName}</td>

    <!-- 価格 -->
    <td>
      <span class="price">${pList.price}</span>円
    </td>
    <!-- 個数 -->
    <td>
      <c:forEach var="cList" items="${cList}">
        <c:if test="${cList.product.productId eq pList.productId}">
          <span class="count">${cList.count}個</span>

          <!-- 合計金額を計算 -->
          <c:set var="itemTotal" value="${pList.price * cList.count}" />
          <c:set var="totalPrice" value="${totalPrice + itemTotal}" />
        </c:if>
      </c:forEach>
      <!-- 商品IDを送る -->
      <input type="hidden" name="productId${ status.index }" value="${ pList.productId }" >
    </td>
  </tr>
</c:forEach>

		<tr><td><a href="ProductView.action">買い物を続ける</a><td></tr>

	</table>
	<table>
		<td colspan="4" style="text-align: right;">ご請求額: <strong>${totalPrice}</strong>円</td>
	</table>
	<input type="submit" value="決済">
	</form>
</div>

<%-- フッター --%>
<c:import url="../common/footer.jsp"/>
</div>
</div>
</html>
