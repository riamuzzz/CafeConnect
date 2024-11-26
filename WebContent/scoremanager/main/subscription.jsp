<%-- サブスクリプション --%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<link rel='stylesheet' href='../css/style.css'>

	<title>かふぇコネクト</title>

</head>
<body>
<div class="subsc">

	<div class="title">
	<label>サブスクリプション</label><br>
	</div>

	<div class="setumei">
	<label>毎月届く、おすすめコーヒーのセット<br>
		   月ごとに自分で選んで<br>
		   おすすめのコーヒー豆の詰め合わせが購入できます。<br>
		   毎月コーヒーを楽しみたい方におすすめです。</label>
	</div>

	<div class="gaiyou">
		<div class="puran">
		<label>ライトプラン</label>
		</div>

		<div class="subimg">
		<img src=../img/subscription/サブスクリプション.jpg width="120">
		</div>

		<div class="syousai">
			<label>225g 約15杯分<br>(3種類 x 5杯分)</label>
		</div>

		<div class="kingaku">
			<label>2780円（税込）</label>
		</div>

		<!-- ここから入会ページ作って❤ -->
		<!-- 次はsubscNyukai.jspです -->
		<form action = "SubUserCreateExecute.action" method="post">
		<div class="botan">
			<input type="submit" name="nyukai" value="入会"/>
		</div>
		</form>


	</div>

</div>
</body>