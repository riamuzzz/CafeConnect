<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<link rel='stylesheet' href='../../css/style2.css'>
<div class="top_page">


<%-- ヘッダー --%>
<c:import url="../common/cafeheader.jsp"/>

<%-- ナビゲーション --%>
<c:import url="../common/cafenavigation.jsp"/>

<div class="productcreate">

<form action = "ProductCreateExecute.action" method="post">

	<table>
	<tr>
		<th><label>カテゴリ</label></th>
		<td>
		<select id="category">
			<c:forEach var="category" items="${categorys}">
				<option value="${category.categoryId}">${category.categoryName}</option>
			</c:forEach>
		</select>
		</td>
	</tr>
	<tr>
		<th><label>商品名</label></th>
		<td><input type="text" id="productName" class="productName"></td>
	</tr>
	<tr>
		<th><label>価格</label></th>
		<td><input type="number" id="price" class="price"></td>
	</tr>
	<tr>
		<th><label>数量</label></th>
		<td><input type="number" id="count" class="count"></td>
	</tr>
	<tr>
		<th><label>詳細</label></th>
		<td><textarea id="productDetail" class="productDetail"></textarea></td>
	</tr>
	<tr>
		<th><label>写真</label></th>
		<td><input type="file" id="image" class="image"></td>
	</tr>
	<tr>
		<th><label>販売状況</label></th>
		<td><input type="checkbox" id="sell" class="sell"></td>
	</tr>
	</table>

	<input type="submit" value="登録" class="button" id="touroku">
</form>


</div>
</div>
</html>
