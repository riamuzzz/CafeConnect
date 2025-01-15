<%-- ヘッダー --%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<head>
<c:import url="../common/main_header.jsp"/>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<link rel='stylesheet' href='../css/style.css'>

	<title>かふぇコネクト</title>

</head>

<div class="mobMenu">
	<div class="menutitle1">
		<label>ドリンク</label>
	</div>

	<div class="drink">
		<table>
				<tr>
					<c:forEach var="drink" items="${ drinkProducts }">
						<th><a href="ProductDetailView.action?productId=${ drink.productId }"><img src="../img/product/${ drink.image }" width="110"></a></th>
					</c:forEach>
				</tr>
				<tr>
					<c:forEach var="drink" items="${ drinkProducts }">
						<td><a href="ProductDetailView.action?productId=${ drink.productId }">${ drink.productName }</a></td>
					</c:forEach>
				</tr>
		</table>
	</div>

	<div class="menutitle2">
		<label>フード</label>
	</div>

	<div class="food">
		<table>
				<tr>
					<c:forEach var="food" items="${ foodProducts }">
						<th><a href="ProductDetailView.action?productId=${ food.productId }"><img src="../img/product/${ food.image }" width="110"></a></th>
					</c:forEach>
				</tr>
				<tr>
					<c:forEach var="food" items="${ foodProducts }">
						<td><a href="ProductDetailView.action?productId=${ food.productId }">${ food.productName }</a></td>
					</c:forEach>
				</tr>
		</table>
	</div>
</div>