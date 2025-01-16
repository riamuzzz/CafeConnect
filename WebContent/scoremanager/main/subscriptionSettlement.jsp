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

	<h2>${category.categoryName }</h2>

	<table>
			<tr><td>お届け先：${ user.userName }</td></tr>
			<tr><td>${ user.address }</td></tr>

			<tr><td>変更<td></tr>

	</table>

	<table>

			<tr><td>お支払方法：${ card.cardNumber }</td></tr>
			<tr><td>カード氏名：${ card.cardName }</td></tr>
			<tr><td>有効期限：${ card.cardExpiryDate }</td></tr>

			<tr><td>変更<td></tr>
    <tr class="item" >
      <!-- 商品画像 -->
        <img src="../img/product/${product.image}" alt="${product.productName}">
      <!-- 商品名 -->
      <td>${product.productName}</td>

      <!-- 価格 -->
      <td>
        <span class="price" >${product.price}</span>円
      </td>
      <!-- 個数 -->
      <td>
      	  <span class="count">${count}g</span>
      </td>
    </tr>

		<tr><td>変更<td></tr>

	</table>


</div>

<%-- フッター --%>
<c:import url="../common/footer.jsp"/>
</div>
</div>
</html>
