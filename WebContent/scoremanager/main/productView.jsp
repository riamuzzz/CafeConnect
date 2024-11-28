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
		<c:forEach var="product" items="${products}">
			<tr>
				<td>
					<a href="ProductDetailView.action?productId=${ product.productId }"><img src="../img/product/${ product.image }"></a>
				</td>
			</tr>
			<tr><td>${ product.productName }</td></tr>
			<tr><td>${ product.price }円</td></tr>
		</c:forEach>
	</table>
</div>

<%-- フッター --%>
<c:import url="../common/footer.jsp"/>
</div>
</div>
</html>
