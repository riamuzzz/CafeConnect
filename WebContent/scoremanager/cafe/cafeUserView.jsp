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

	<h2>商品一覧</h2>

	<form method="get">
		<label>カテゴリ </label>
		<select name="f1">
			<option value="0">--------</option>
			<c:forEach var="category" items="${categoryName}">
				<%-- 現在のyearと選択されていたf1が一致していた場合selectedを追記 --%>
				<option value="${category.categoryId}" <c:if test="${category.categoryName==category.categoryName}">selected</c:if>>${category.categoryName}</option>
			</c:forEach>
		</select>

		<label>商品名</label>
		<label for="keyword">検索キーワード:</label>
        <input type="text" id="keyword" name="keyword" >
        <button type="submit">検索</button>

		<div>${errors.get("f1")}</div>
	</form>



			<table class="table table-hover">
				<tr>
					<th>商品名</th>
					<th>価格</th>
					<th>販売状況</th>
				</tr>
				<c:forEach var="productList" items="${productList}">
					<tr>
						<td>${productList.productName}</td>
						<td>${productList.price}</td>
						<td>${productList.sell}</td>
					</tr>

				</c:forEach>
			</table>

<%-- フッター --%>
<c:import url="../common/cafefooter.jsp"/>
</div>
</div>
</html>
