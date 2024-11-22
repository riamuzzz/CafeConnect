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

	<div class="cafeuser_view1">
		<h2>顧客一覧</h2>

		<table>
		<form method="get">
			<th><label for="userName">氏名:</label></th>
	        <td><input type="text" id="userName" name="userName" ></td>

			<th><label for="tel">電話番号:</label></th>
	        <td><input type="text" id="tel" name="tel" ></td>

	        <td><button type="submit" id="button">検索</button></td>

			<div>${errors.get("f1")}</div>
		</form>
		</table>
	</div>


		<div class="cafeuser_view2">
			<c:choose>
				<c:when test="${user.size()>0}">

					<table>
						<tr>
							<th class="userId">ユーザーID</th>
							<th class="userName">氏名</th>
							<th class="email">メールアドレス</th>
							<th class="tel">電話</th>
							<th class="address">住所</th>
							<th class="subscription">サブスク</th>

						</tr>
						<c:forEach var="user" items="${user}">
							<tr>
								<td class="userId">${user.userId}</td>
								<td class="userName">${user.userName}</td>
								<td class="email">${user.email}</td>
								<td class="tel">${user.tel}</td>
								<td class="address">${user.address}</td>
								<%-- サブスクフラグがたっている場合「○」それ以外は「×」を表示 --%>
								<td class="subscription">
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
				</c:when>
				<c:otherwise>
					<div>商品情報が存在しませんでした</div>
				</c:otherwise>
			</c:choose>
		</div>

<%-- フッター --%>
<c:import url="../common/cafefooter.jsp"/>
</div>
</div>
</html>
