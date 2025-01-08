<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<link rel='stylesheet' href='../../css/style2.css'>
<div class="sale">


<%-- ヘッダー --%>
<c:import url="../common/cafeheader.jsp"/>

<%-- ナビゲーション --%>
<c:import url="../common/cafenavigation.jsp"/>

<h2>売上情報</h2>

<form action = "SaleExecute.action" method="post">
	<table>
		<tr>
			<th><label>年</label></th>
			<td>
			<select name="year" id="year">
			<option value="0">--------</option>
				<c:forEach var="year" items="${years}">
					<option value="${year}"<c:if test="${year==selectYear}">selected</c:if>>${year}</option>
				</c:forEach>
			</select>
			</td>
			<th><label>月</label></th>
			<td>
			<select name="month" id="month">
			<option value="0">--------</option>
				<c:forEach var="month" items="${months}">
					<option value="${month}" <c:if test="${month==selectMonth}">selected</c:if>>${month}</option>
				</c:forEach>
			</select>
			</td>
			<td><input type="submit" value="検索" class="button" id="検索"></td>
		</tr>
	</table>
</form>

<img src="data:image/png;base64,${lineGraph}" alt="売上折れ線グラフ" />
<img src="data:image/png;base64,${barGraph}" alt="売上棒グラフ" />
<img src="data:image/png;base64,${graph}" alt="サブスク会員割合グラフ" />

</div>