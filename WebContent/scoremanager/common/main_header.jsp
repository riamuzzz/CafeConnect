<%-- ヘッダー --%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<link rel='stylesheet' href='../../css/style.css'>
	<link rel='stylesheet' href='../../css/uchiyama.css'>

	<title>かふぇコネクト</title>

</head>

<div class="container">
	<div class="header">
		<div class=top>
			<h2>かふぇコネクト</h2>

			<div class="user">${ user.userName }さん</div>

			<!-- 検索 -->
			<div class=search>
				<input placeholder="キーワード検索">
				<!-- 検索用ボタン -->
				<input type="submit" name="kensaku" value="検索"/>
			</div>

			<div class=banner>
			<table>
					<tr>
						<th><a href="../TopPageExecute.action"><img src=../img/header/トップ.png width="30"></a></th>
						<th><a href="MobileCartView.action"><img src=../img/header/モバイル.jpeg width="40"></a></th>
						<th><a href="CartView.action"><img src=../img/header/カート.jpeg width="40"></a></th>
						<th><a href="Logout.action"><img src=../img/header/log.jpg width="43"></a></th>
					</tr>
					<tr>
						<td><a href="../TopPageExecute.action">トップ</a></td>
						<td><a href="MobileCartView.action">モバイル</a></td>
						<td><a href="CartView.action">カート</a></td>
						<td><a href="Logout.action">ログアウト</a></td>
					</tr>
				</table>
			</div>
		</div>


		<div class=menu>
			<table>
				<tr>
					<th><a href="Subscription.action"><img src=../img/header/サブスク.png width="30"></a></th>
					<th><a href="../ProductView.action?categoryId=CATE02"><img src=../img/header/ショップ.png width="40"></a></th>
					<th><a href="MobMenu.action"><img src=../img/header/モバイル.png width="40"></a></th>
					<th><a href="main/"><img src=../img/header/店舗情報.png width="40"></a></th>
					<th><a href="Contact.action"><img src=../img/header/お問い合わせ.png width="35"></a></th>
					<th><a href="MyPage.action"><img src=../img/header/マイページ.png width="35"></a></th>
				</tr>
			</table>
		</div>
	</div>
</div>