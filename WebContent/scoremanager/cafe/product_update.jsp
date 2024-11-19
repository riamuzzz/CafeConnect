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

<form action="ProductUpdateExecute.action" method="post">

	<label>カテゴリ </label>
		<select name="category">
			<option value="0">--------</option>
			<c:forEach var="category" items="${categories}">
				<%-- 現在のyearと選択されていたf1が一致していた場合selectedを追記 --%>
				<option value="${category.categoryId}" <c:if test="${category.categoryName==category.categoryName}">selected</c:if>>${category.categoryName}</option>
			</c:forEach>
		</select>
	<input type="hidden" name="id" value="${ id }">
	<input type="hidden" name="instockday" value="${ instockday }">
	<label>商品名</label>
		<input type="text" name="name" value="${name}">
	<label>価格</label>
		<input type="text" name="price" value="${price}">
	<label>数量</label>
		<input type="number" name="count" value="${ count }">
	<label>詳細</label>
		<input type="text" name="detail" value="${detail}">
	<label>写真</label>
		<input type="text" name="image" value="${image}">
	<label>販売状況</label>
		<input type="checkbox" name="is_sell" value="${is_sell}">
	<input type="submit" value="変更">

</form>

<a href="ProductView.action">戻る</a>

<%-- フッター --%>
<c:import url="../common/cafefooter.jsp"/>
</div>
</div>
</html>
