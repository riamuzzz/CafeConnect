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

		<label>カテゴリ</label>
		<select name="category">
			<c:forEach var="category" items="${categorys}">
				<option value="${category.categoryId}">${category.categoryName}</option>
			</c:forEach>
		</select>

		<label>商品名</label>
		<input type="text" name="productName">

		<label>価格</label>
		<input type="number" name="price">

		<label>数量</label>
		<input type="number" name="count">

		<label>詳細</label>
		<textarea name="productDetail"></textarea>

		<label>写真</label>
		<input type="file" name="image">

		<label>販売状況</label>
		<input type="checkbox" name="sell"><br>

	<input type="submit" value="登録">

</form>


</div>
</div>
</html>
