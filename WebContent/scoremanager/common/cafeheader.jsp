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
      <li><a class="menu__item" href="StockView.action">・在庫一覧</a></li>
      <li><a class="menu__item" href="ProductCreate.action">・商品登録</a></li>
      <li><a class="menu__item" href="ProductView.action">・商品一覧</a></li>
      <li><a class="menu__item" href="#">・売上一覧情報</a></li>
      <li><a class="menu__item" href="CafeUserView">・顧客一覧</a></li>
      <li><a class="menu__item" href="#">・注文一覧</a></li>
      <li><a class="menu__item" href="#">・発送済み一覧</a></li>
      <li><a class="menu__item" href="#">・注文一覧</a></li>
      <li><a class="menu__item" href="#">・お渡し済み一覧</a></li>
    </ul>
  </div>

<div class="cafe_header">

		<!-- パスワード変更 -->
		<label>パスワード変更</label>

		<!-- ログアウト -->
		<label>ログアウト</label>



</div>


</div>
