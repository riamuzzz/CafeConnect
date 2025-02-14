

<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<div class="top_page">


<%-- ヘッダー --%>
<c:import url="../common/main_header.jsp"/>


<div class="prode">

	<div class="proimage">
		<img src="../img/product/${ product.image }">
	</div>

	<div class="probo">
	<table>
			<tr class="proNa"><td>${ product.productName }</td></tr>
			<tr class="proDe"><td>${ product.productDetail }</td></tr>
			<tr class="proPr"><td>${ product.price }円（税込）</td></tr>
	</table>

	<div class="prosyo">
		<div class="suryo">数量を選択</div>
	<form action="CartCreateExecute.action" method="post">
		<div class="aaa">
				<c:choose>
				    <c:when test="${ product.count > 0 }">
				    	<label>数量</label>
				        <input type="number" name="num" value="1" min="1" max="${ product.count }" required>
				        <input type="hidden" name="product" value="${ product.productId }">
				        <input class="cart" type="submit" value="カートに追加">
				    </c:when>
				    <c:otherwise>
				        <span style="color:red;">売り切れ</span>
				    </c:otherwise>
				</c:choose>
				<a href="TopPageExecute.action">選択に戻る</a>
		</div>
	</form>


	</div>
	</div>

</div>
</div>
</html>
