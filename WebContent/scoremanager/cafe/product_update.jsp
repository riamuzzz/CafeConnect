<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<link rel='stylesheet' href='../../css/style2.css'>

<%-- ヘッダー --%>
<c:import url="../common/cafeheader.jsp"/>

<div class="main">
<%-- ナビゲーション --%>
<c:import url="../common/cafenavigation.jsp"/>

<div class="productupdate">

<form action="ProductUpdateExecute.action" method="post">

	<table>
	<tr>
		<th><label>カテゴリ </label></th>
			<td>
			<select name="category" class="category">
				<option value="0">--------</option>
				<c:forEach var="category" items="${categories}">
					<%-- 現在のyearと選択されていたf1が一致していた場合selectedを追記 --%>
					<option value="${category.categoryId}" <c:if test="${category.categoryName==category.categoryName}">selected</c:if>>${category.categoryName}</option>
				</c:forEach>
			</select>
			</td>
	</tr>


	<input type="hidden" name="id" value="${ id }">
	<input type="hidden" name="instockday" value="${ instockday }">

	<tr>
		<th><label>商品名</label></th>
		<td><input type="text" name="name" class="productName" value="${name}" required></td>
	</tr>
	<tr>
		<th><label>価格</label></th>
		<td><input type="number" name="price" class="price" value="${price}" required></td>
	</tr>
	<tr>
		<th><label>数量</label></th>
		<td><input type="number" name="count" class="count" value="${ count }" required></td>
	</tr>
	<tr>
		<th><label>詳細</label></th>
		<!--変更前<td><input type="text" name="detail" class="productDetail" value="${detail}" required></td> -->
		<td><textarea id="productDetail" name="detail" class="productDetail" value="${detail}" required></textarea></td>
	</tr>
	<tr>
		<th><label>写真</label></th>
		<td><input type="file"  name="image" class="image" value="${image}" required></td>
	</tr>
	<tr>
		<th><label>販売状況</label></th>
		<td><input type="checkbox" name="is_sell" class="sell" value="${is_sell}"></td>
	</tr>
	</table>

	<input type="submit" value="変更" class="button">

</form>

<a href="ProductView.action">戻る</a>

</div>

<%-- フッター --%>
<c:import url="../common/cafefooter.jsp"/>
</div>
</html>
