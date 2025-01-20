<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>

<%-- ヘッダー --%>
<c:import url="./common/header.jsp"/>

<div class="mobMenu">
	<div class="menutitle1">
		<label>ドリンク</label>
	</div>

	<div class="drink">
		<table>
				<tr>

					<c:forEach var="pList" items="${ pList }">
						<c:choose>
							<c:when test="${ pList.category.categoryId eq 'CATE01' }">
								<th><a href="ProductDetailView.action?productId=${ pList.productId }"><img src="../img/product/${ pList.image }" width="110"></a></th>

								<td><a href="ProductDetailView.action?productId=${ pList.productId }">${ pList.productName }</a></td>
							</c:when>
						</c:choose>
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

					<c:forEach var="pList" items="${ pList }">
						<c:choose>
							<c:when test="${ pList.category.categoryId eq 'CATE03' }">
								<th><a href="ProductDetailView.action?productId=${ pList.productId }"><img src="../img/product/${ pList.image }" width="110"></a></th>

								<td><a href="ProductDetailView.action?productId=${ pList.productId }">${ pList.productName }</a></td>
							</c:when>
						</c:choose>
					</c:forEach>
				</tr>
		</table>
	</div>

	<div class="menutitle2">
		<label>豆</label>
	</div>

	<div class="food">
		<table>
				<tr>

					<c:forEach var="pList" items="${ pList }">
						<c:choose>
							<c:when test="${ pList.category.categoryId eq 'CATE02' }">

								<th><a href="ProductDetailView.action?productId=${ pList.productId }"><img src="../img/product/${ pList.image }" width="110"></a></th>

								<td><a href="ProductDetailView.action?productId=${ pList.productId }">${ pList.productName }</a></td>
							</c:when>
						</c:choose>
					</c:forEach>
				</tr>
		</table>
	</div>
</div>
<%-- フッター --%>
<c:import url="./common/footer.jsp"/>
</div>
</div>
</html>