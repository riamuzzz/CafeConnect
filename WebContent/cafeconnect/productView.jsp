<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<html>
<%-- ヘッダー --%>
<head>
<c:import url="./common/header.jsp"/>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<link rel='stylesheet' href='../css/style.css'>

	<title>かふぇコネクト</title>

</head>

<div class="mobMenu">
	<div class="menutitle1">
		<label>コーヒー豆</label>
	</div>

	<div class="drink">
				<table class="mobTable">
					<c:forEach var="product" items="${ products }">
					<table class="mobTable">
					<tr>
						<th><a class="mobImg" href="ProductDetailView.action?productId=${ product.productId }"><img src="img/product/${ product.image }" width="110"></a></th>
					</tr>
					<tr>
						<td><a class="caption" href="ProductDetailView.action?productId=${ product.productId }">${ product.productName }</a></td>
					</tr>
					<tr>
						<td><a class="caption" href="ProductDetailView.action?productId=${ product.productId }">${ product.price }円 </a></td>
					</tr>
					</table>
					</c:forEach>
				</table>


	</div>

<%-- フッター --%>
<c:import url="./common/footer.jsp"/>
</div>

</html>
