<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<div class="productView">

<%-- ヘッダー --%>
<c:import url="../common/main_header.jsp"/>

<div class="main">

<div class="content">



	<div class="menutitle1">
		<label>${category.categoryName }</label>
	</div>
	<c:if test="${category.categoryName != '豆'}">
	<form action="MobMenu.action" method="post">
		<input type="submit" value="戻る" class="itiran">
	</form>
	</c:if>
	<div class="drink">
					<c:forEach var="drink" items="${ products }" varStatus="status">
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

<%-- フッター --%>
<c:import url="../common/footer.jsp"/>
</div>
</div>
</html>
