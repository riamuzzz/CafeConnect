<%-- ヘッダー --%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<link rel='stylesheet' href='../../css/style2.css'>

	<title>かふぇコネクト</title>

</head>

<div class="container">

<div class="hamburger-menu">
    <input id="menu__toggle" type="checkbox" />
    <label class="menu__btn" for="menu__toggle">
      <span></span>
    </label>

    <ul class="menu__box">
      <li><a class="menu__item" href="CafeTop.action">・トップページ</a></li>
      <li><a class="menu__item" href="StockView.action">・在庫一覧</a></li>
      <li><a class="menu__item" href="ProductCreate.action">・商品登録</a></li>
      <li><a class="menu__item" href="ProductView.action">・商品一覧</a></li>
      <li><a class="menu__item" href="SaleExecute.action">・売上一覧情報</a></li>
      <li><a class="menu__item" href="CafeUserView.action">・顧客一覧</a></li>
      <label><ショップ/サブスク></label>
      <li><a class="menu__item" href="OnlineOrderView.action">・注文一覧</a></li>
      <li><a class="menu__item" href="OnlineReceive.action">・発送済み一覧</a></li>
      <label><モバイルオーダー></label>
      <li><a class="menu__item" href="MobileOrderView.action">・注文一覧</a></li>
      <li><a class="menu__item" href="MobileReceive.action">・お渡し済み一覧</a></li>
    </ul>
  </div>

<div class="cafe_header">

		<!-- パスワード変更 -->
		<a href="CafePassReset.action" id="link">パスワード変更</a>

		<!-- ログアウト -->
		<a href="../CafeLogout.action" id="link">ログアウト</a>


</div>


</div>
