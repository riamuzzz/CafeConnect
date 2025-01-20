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
		<label>豆</label>
	</div>


<div class="drink">
	<table>
		<c:forEach var="product" items="${products}">
			<tr>
				<td>
					<a href="ProductDetailView.action?productId=${ product.productId }"><img src="img/product/${ product.image }"></a>
				</td>
			</tr>
			<tr><td>${ product.productName }</td></tr>
			<tr><td>${ product.price }円</td></tr>
		</c:forEach>
	</table>
</div>

<%-- フッター --%>
<c:import url="./common/footer.jsp"/>
</div>

</html>
