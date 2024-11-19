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

	<h2>顧客一覧</h2>

	<form method="get">
		<label>氏名 </label>
		<label for="userName">検索キーワード:</label>
        <input type="text" id="userName" name="userName" >

		<label>電話番号</label>
		<label for="tel">検索キーワード:</label>
        <input type="text" id="tel" name="tel" >

        <button type="submit">検索</button>

		<div>${errors.get("f1")}</div>
	</form>



	<c:choose>
		<c:when test="${user.size()>0}">

			<table>
				<tr>
					<th>ユーザーID</th>
					<th>氏名</th>
					<th>メールアドレス</th>
					<th>電話</th>
					<th>住所</th>
					<th>サブスク</th>

				</tr>
				<c:forEach var="user" items="${user}">
					<tr>
						<td>${user.userId}</td>
						<td>${user.userName}</td>
						<td>${user.email}</td>
						<td>${user.tel}</td>
						<td>${user.address}</td>
						<%-- サブスクフラグがたっている場合「○」それ以外は「×」を表示 --%>
						<td>
							<c:choose>
								<c:when test="${user.subscription}">
									○
								</c:when>
								<c:otherwise>
									×
								</c:otherwise>
							</c:choose>
						</td>

					</tr>
				</c:forEach>
			</table>
			<a href="UserUpdate.action">変更</a>
		</c:when>
		<c:otherwise>
			<div>商品情報が存在しませんでした</div>
		</c:otherwise>
	</c:choose>

<%-- フッター --%>
<c:import url="../common/cafefooter.jsp"/>
</div>
</div>
</html>
