<%-- ヘッダー --%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<link rel='stylesheet' href='../css/style.css'>

	<title>かふぇコネクト</title>

</head>

<div class="container">
	<div class="header">
		<div class=top>
			<h2>かふぇコネクト</h2>

			<!-- 検索 -->
			<div class=search>
				<input class ="box" placeholder="キーワード検索">
				<!-- 検索用ボタン -->
				<input class="button" type="submit" name="kensaku" value="検索"/>
			</div>

			<div class=banner>
			<table>
					<tr>
						<th><a href="TopPageExecute.action"><img src=img/header/トップ.png></a></th>
						<th><a href="main/CartView.action"><img src=img/header/モバイル.jpeg></a></th>
						<th><a href="main/CartView.action"><img src=img/header/カート.jpeg></a></th>
					</tr>
					<tr>
						<td><a href="TopPageExecute.action">トップ</a></td>
						<td><a href="main/CartView.action">モバイル</a></td>
						<td><a href="main/CartView.action">カート</a></td>
					</tr>
				</table>
			</div>
		</div>

		<div class=menu>
			<table>
					<tr>
						<th><a href="subscription.jsp"><img src=img/header/コーヒー豆.jpg></a></th>
						<th><a href="ProductView.action?categoryId=CATE02"><img src=img/header/カート.jpeg></a></th>
						<th><a href="ProductView.action?categoryId=CATE01"><img src=img/header/モバイル.jpeg></a></th>
						<th><a href="main/"><img src=img/header/店舗情報.jpg></a></th>
						<th><a href="main/Contact.action"><img src=img/header/お問い合わせ.jpg></a></th>
						<th><a href="main/MyPage.action"><img src=img/header/マイページ.jpeg></a></th>
					</tr>
					<tr>
						<td><a href="subscription.jsp">サブスク</a></td>
						<td><a href="ProductView.action?categoryId=CATE02">ショップ</a></td>
						<td><a href="ProductView.action?categoryId=CATE01">モバイル</a></td>
						<td><a href="main/">店舗情報</a></td>
						<td><a href="main/Contact.action">お問い合わせ</a></td>
						<td><a href="main/MyPage.action">マイページ</a></td>
					</tr>
				</table>
			</div>
		</div>
	</div>
</div>