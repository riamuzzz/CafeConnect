<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<%-- ヘッダー --%>
<c:import url="../common/main_header.jsp" />
<div class="main">
	<div class="subscription-select">
		<h2 class="title">${category.categoryName }選択（３種）</h2>
		<div class="products">
			<c:forEach var="product" items="${products}">
				<div class="product">
					<div class="image">
						<a
							href="ProductSelectDetailView.action?productId=${ product.productId }">
							<img src="../img/product/${ product.image }" style="width: 160px; height: 160px">
						</a>
					</div>
					<div class="name">${ product.productName }</div>
				</div>
			</c:forEach>
		</div>
	</div>
	<%-- フッター --%>
	<c:import url="../common/footer.jsp" />
</div>
</html>
