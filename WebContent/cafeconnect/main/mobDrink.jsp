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
					<c:forEach var="drink" items="${ drinkProducts }" varStatus="status">
							<table class="mobTable">
							<tr>
								<th><a class="mobImg" href="ProductDetailView.action?productId=${ drink.productId }"><img src="../img/product/${ drink.image }" width="110"></a></th>
							</tr>
							<tr>
								<td><a class="caption" href="ProductDetailView.action?productId=${ drink.productId }">${ drink.productName }</a></td>
							</tr>
							<tr>
								<td><a class="caption" href="ProductDetailView.action?productId=${ drink.productId }">${ drink.price }円 </a></td>
							</tr>
							</table>
					</c:forEach>
	</div>
</div>