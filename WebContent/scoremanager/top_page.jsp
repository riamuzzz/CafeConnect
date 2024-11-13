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

<div class="content">
	<h2></h2>
	<img src=img/top/top1.jpg>

	<h2>ランキング</h2>
	<table>
		<c:forEach var="product" items="${products}">
			<tr><td><img src="img/product/${ product.image }"></td></tr>
			<tr><td>${ product.image }</td></tr>
			<tr><td>${ product.productName }</td></tr>
			<tr><td>${ product.price }円</td></tr>
		</c:forEach>
	</table>
</div>

<%-- フッター --%>
<c:import url="./common/footer.jsp"/>
</div>
</div>
</html>
