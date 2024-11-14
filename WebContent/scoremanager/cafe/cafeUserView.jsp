<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<div class="top_page">


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
			<c:forEach var="category" items="${category}">
				<%-- 現在のyearと選択されていたf1が一致していた場合selectedを追記 --%>
				<option value="${category}" <c:if test="${category==f1}">selected</c:if>>${category}</option>
			</c:forEach>
		</select>
		<label>商品名</label>
		<select name="f2">
			<option value="0">--------</option>
			<c:forEach var="product_name" items="${product_name}">
				<%-- 現在のproduct_nameと選択されていたf2が一致していた場合selectedを追記 --%>
				<option value="${product_name}" <c:if test="${product_name==f2}">selected</c:if>>${product_name}</option>
			</c:forEach>
		</select>

		<button>絞込み</button>

		<div>${errors.get("f1")}</div>
	</form>
<div class="content">



<%-- フッター --%>
<c:import url="../common/cafefooter.jsp"/>
</div>
</div>
</div>
</html>
