<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<div class="top_page">


<%-- ヘッダー --%>
<c:import url="./common/header.jsp"/>


<div class="prode">
<%-- ナビゲーション --%>
<c:import url="./common/navigation.jsp"/>


	<img src="img/product/${ product.image }">
	<div class="probo">
	<table>
			<tr class="proNa"><td>${ product.productName }</td></tr>
			<tr class="proDe"><td>${ product.productDetail }</td></tr>
			<tr class="proPr"><td>${ product.price }円（税込）</td></tr>
	</table>
	<div class="prosyo">
	<form action="main/CartCreateExecute.action" method="post">
		<label>数量</label>
		<input type="number" name="num" value="${ num }">
		<input type="hidden" name="product" value="${ product.productId }">
		<input class="cart" type="submit" value="カートに追加">
	</form>

		<a href="TopPageExecute.action">選択に戻る</a>
	</div>
	</div>

<%-- フッター --%>
<c:import url="./common/footer.jsp"/>
</div>
</div>
</html>
