<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<link rel="stylesheet"  href="../../css/settlement.css">
<div class="productView">


<%-- ヘッダー --%>
<c:import url="../common/main_header.jsp"/>

<div class="main">
<%-- ナビゲーション --%>
<c:import url="../common/navigation.jsp"/>

<div class="content">

	<h2>${category.categoryName }</h2>

	<table class="pay">
			<tr><td>お届け先：${ user.userName }</td></tr>
			<tr><td>${ user.address }</td></tr>

			<tr><td>変更<td></tr>

	</table>

	<table class="pay">

			<tr><td>お支払方法：${ card.cardNumber }</td></tr>
			<tr><td>カード氏名：${ card.cardName }</td></tr>
			<tr><td>有効期限：${ card.cardExpiryDate }</td></tr>

			<tr><td>変更<td></tr>
	</table>
	<div id="subkessai">
	<p id="orderProduct">注文商品</p>
	<table class="kessai">
    <tr class="item">
      <!-- 商品画像 -->
        <th><img src="../img/product/${product.image}" alt="${product.productName}"></th>
      <!-- 商品名 -->
      <td>${product.productName}<br><br>

      <!-- 価格 -->

      <!-- 個数 -->
      	  <span class="count"><strong>${count}g</strong></span>
      </td>
    </tr>
	</table>
	</div>
	<form action="SubscriptionSettlementExecute.action" method="post">
		<input type="hidden" name="productId" value="${product.productId}">
		<input type="hidden" name="count" value="${count}">
		<input type="submit" value="注文を確定する" class="subKessai2">
	</form>
	<form action="Subscription.action" method="post">
		<input type="submit" value="商品選択に戻る" class="cartBack2">
	</form>
</div>
<%-- フッター --%>
<c:import url="../common/footer.jsp"/>
</div>
</div>
</html>
