<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<link rel='stylesheet' href='../../css/style2.css'>


<%-- ヘッダー --%>
<c:import url="../common/cafeheader.jsp"/>

<%-- ナビゲーション --%>
<c:import url="../common/cafenavigation.jsp"/>

<div class="productcreate">

<form action = "ProductCreateExecute.action" method="post">
	<h1>商品登録</h1>
	<table>
	<tr>
		<th><label>カテゴリ</label></th>
		<td>
		<select name="category" id="category">
			<c:forEach var="category" items="${categorys}">
				<option value="${category.categoryId}">${category.categoryName}</option>
			</c:forEach>
		</select>
		</td>
	</tr>
	<tr>
		<th><label>商品名</label></th>
		<td><input type="text" name="productName"id="productName" class="productName"></td>
	</tr>
	<tr>
		<th><label>価格</label></th>
		<td><input type="number" name="price" id="price"></td>
	</tr>
	<tr>
		<th><label>数量</label></th>
		<td><input type="number" name="count" id="count" class="count"></td>
	</tr>
	<tr>
		<th><label>詳細</label></th>
		<td><textarea id="productDetail" name="productDetail" class="productDetail"></textarea></td>
	</tr>
	<tr>
		<th><label>販売状況</label></th>
		<td><input type="checkbox" name="sell" id="sell" class="sell"></td>
	</tr>
	</table>

	<input type="submit" value="登録" class="button" id="touroku">
</form>

</div>
<c:import url="../common/cafefooter.jsp"/>
</html>
