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
			<div class="cafe">
			<h2>かふぇコネクト</h2>
			</div>

			<!-- 検索 -->
			<form action="Serch.action" method="post">
			<div class=search>
				<div class=kwdkensaku>
					<input placeholder="キーワード検索" name="keyword" style="width: 100%; height: 44px;">
					</div>
					<!-- 検索用ボタン -->
					<input type="submit" name="kensaku" value="検索"/>
			</div>
			</form>

			<div class=banner>
			<table>
					<tr>
						<th><a href="TopPageExecute.action"><img src=img/header/トップ.png width="30"></a></th>
						<th><a href="main/MobileCartView.action"><img src=img/header/モバイル.jpeg width="40"></a></th>
						<th><a href="main/CartView.action"><img src=img/header/カート.jpeg width="40"></a></th>
						<th><a href="Login.action"><img src=img/header/log.jpg width="43"></a></th>
					</tr>
					<tr>
						<td><a href="TopPageExecute.action">トップ</a></td>
						<td><a href="main/MobileCartView.action">モバイルカート</a></td>
						<td><a href="main/CartView.action">オンラインカート</a></td>
						<td><a href="Login.action">ログイン</a></td>
					</tr>
				</table>
			</div>
		</div>

		<div class=menu>
			<table>
					<tr>
						<th><a href="main/Subscription.action"><img src=img/header/サブスク.png></a></th>
						<th><a href="ProductView.action?categoryId=CATE02"><img src=img/header/ショップ.png></a></th>
						<th><a href="Mobile.action"><img src=img/header/モバイル.png></a></th>
						<th><a href="ShopDetail.action"><img src=img/header/店舗情報.png></a></th>
						<th><a href="Contact.action"><img src=img/header/お問い合わせ.png></a></th>
						<th><a href="main/MyPage.action"><img src=img/header/マイページ.png></a></th>
					</tr>
			</table>

		</div>
	</div>
</div>