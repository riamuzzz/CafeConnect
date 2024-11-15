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


<a href="main/MyPage.action">マイページ</a>
<a href="Logout.action">ログアウト</a>
<div class="content">
	<img src=img/top/top1.jpg>

	<h2>ランキング</h2>
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
</div>
</html>