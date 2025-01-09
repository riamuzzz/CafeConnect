<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<div class="top_page">


<%-- ヘッダー --%>
<c:import url="./common/header.jsp"/>


<div class="main">
<%-- ナビゲーション --%>
<c:import url="./common/navigation.jsp"/>

<table>
	<tr><td><img src="img/product/${ product.image }"></td></tr>
	<tr><td>${ product.productName }</td></tr>
	<tr><td>${ product.productDetail }</td></tr>
	<tr><td>${ product.price }円（税込）</td></tr>

</table>
<form action="main/CartCreateExecute.action" method="post">
	<label>数量</label>
	<input type="number" name="num" value="${ num }">
	<input type="hidden" name="product" value="${ product.productId }">
	<input type="submit" value="カートに追加">
</form>
<a href="main/CartView.action">レジに進む</a>
<a href="TopPageExecute.action">選択に戻る</a>

<%-- フッター --%>
<c:import url="./common/footer.jsp"/>
</div>
</div>
</html>
