<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<div class="cafe_user_view">

<%-- ヘッダー --%>
<c:import url="../common/cafeheader.jsp"/>

<div class="main">
<%-- ナビゲーション --%>
<c:import url="../common/cafenavigation.jsp"/>

	<div class="product_view1">
		<h2>商品一覧</h2>

		<table>
		<form method="get">
			<th><label>カテゴリ </label></th>
			<td>
			<select name="f1">
				<option value="0">--------</option>
				<c:forEach var="category" items="${categoryName}">
					<%-- 現在のyearと選択されていたf1が一致していた場合selectedを追記 --%>
					<option value="${category.categoryId}" <c:if test="${category.categoryName==category.categoryName}">selected</c:if>>${category.categoryName}</option>
				</c:forEach>
			</select>
			</td>

			<th><label for="keyword">商品名:</label></th>
	        <td><input type="text" id="keyword" name="keyword" ></td>
	        <td><button type="submit" id="button">検索</button><td>

			<div>${errors.get("f1")}</div>
		</form>
		</table>


		<div class="product_view2">
		<c:choose>
			<c:when test="${product.size()>0}">

				<table>
					<tr>
						<th>商品名</th>
						<th>価格</th>
						<th>販売状況</th>
					</tr>
					<c:forEach var="product" items="${product}">
						<tr>
							<td>${product.productName}</td>
							<td>${product.price}</td>
							<td class="text-center">
								<%-- 在学フラグがたっている場合「○」それ以外は「×」を表示 --%>
								<c:choose>
									<c:when test="${product.sell}">
										○
									</c:when>
									<c:otherwise>
										×
									</c:otherwise>
								</c:choose>
							</td>
							<td><a href="ProductUpdate.action?productId=${product.productId}">変更</a></td>
						</tr>
					</c:forEach>
				</table>
			</c:when>
			<c:otherwise>
				<div>商品情報が存在しませんでした</div>
			</c:otherwise>
		</c:choose>
		</div>
	</div>

<%-- フッター --%>
<c:import url="../common/cafefooter.jsp"/>
</div>
</div>
</html>
