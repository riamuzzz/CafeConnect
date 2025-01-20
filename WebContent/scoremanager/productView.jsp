<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<%-- ヘッダー --%>
<c:import url="./common/header.jsp"/>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<link rel='stylesheet' href='../css/style.css'>

</head>

<div class="Mobmenu">
	<div class="menutitle1">
		<label>豆</label>
	</div>

	<div class="drink">
	<table>
		<tr>
			<c:forEach var="product" items="${products}">
				<th><a href="ProductDetailView.action?productId=${ product.productId }"><img src="../img/product/${ product.image }" width="110"></a></th>
				<td><a href="ProductDetailView.action?productId=${ product.productId }">${ product.productName }</a></td>
			</c:forEach>
		</tr>
	</table>
</div>

<%-- フッター --%>
<c:import url="./common/footer.jsp"/>
</div>
</html>
