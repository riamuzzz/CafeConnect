<%-- ヘッダー --%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<link rel='stylesheet' href='css/style.css'>

	<title>かふぇコネクト</title>

</head>

<div class="container">
<div class="header">
	<h2>かふぇコネクト</h2>

		<!-- 検索 -->
		<div class=search><input placeholder="キーワード検索">

		<!-- 検索用ボタン -->
		<input type="submit" name="kensaku" value="検索"/></div>

		<!-- トップ -->
		<div class=banner><img src=img/header/トップ.png width="30">
		<label>トップ</label>

		<!-- モバイル -->

		<img src=img/header/モバイル.jpeg width="40">
		<label>モバイル</label>

		<!-- カート -->
		<img src=img/header/カート.jpeg width="40">
		<label>カート</label></div><br>


		<!-- サブスク -->
		<a href="ProductView.action">
		<img src=img/header/コーヒー豆.jpg width="30">
		<label>サブスク</label></a>

		<!-- ショップ -->
		<img src=img/header/カート.jpeg width="40">
		<label>ショップ</label>

		<!-- モバイル -->
		<img src=img/header/モバイル.jpeg width="40">
		<label>モバイル</label>

		<!-- 店舗情報 -->
		<img src=img/header/店舗情報.jpg width="40">
		<label>店舗情報</label>

		<!-- お問い合わせ -->
		<img src=img/header/お問い合わせ.jpg width="35">
		<label>お問い合わせ</label>

		<!-- マイページ -->
		<a href="main/MyPage.action"><img src=img/header/マイページ.jpeg width="35">
		<label>マイページ</label></a>



</div>


</div>