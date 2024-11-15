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
				<input placeholder="キーワード検索">
				<!-- 検索用ボタン -->
				<input type="submit" name="kensaku" value="検索"/>
			</div>

			<div class=banner>
			<table>
					<tr>
						<th><a href="main/"><img src=img/header/トップ.png width="30"></a></th>
						<th><a href="main/"><img src=img/header/モバイル.jpeg width="40"></a></th>
						<th><a href="main/"><img src=img/header/カート.jpeg width="40"></a></th>
					</tr>
					<tr>
						<td><a href="main/">トップ</a></td>
						<td><a href="main/">モバイル</a></td>
						<td><a href="main/">カート</a></td>
					</tr>
				</table>
			</div>
		</div>

		<div class=menu>
			<table>
					<tr>
						<th><a href="main/"><img src=img/header/コーヒー豆.jpg width="30"></a></th>
						<th><a href="main/"><img src=img/header/カート.jpeg width="40"></a></th>
						<th><a href="main/"><img src=img/header/モバイル.jpeg width="40"></a></th>
						<th><a href="main/"><img src=img/header/店舗情報.jpg width="40"></a></th>
						<th><a href="main/"><img src=img/header/お問い合わせ.jpg width="35"></a></th>
						<th><a href="main/"><img src=img/header/マイページ.jpeg width="35"></a></th>
					</tr>
					<tr>
						<td><a href="main/">サブスク</a></td>
						<td><a href="main/">ショップ</a></td>
						<td><a href="main/">モバイル</a></td>
						<td><a href="main/">店舗情報</a></td>
						<td><a href="main/">お問い合わせ</a></td>
						<td><a href="main/">マイページ</a></td>
					</tr>
				</table>
			</div>
		</div>
	</div>
</div>