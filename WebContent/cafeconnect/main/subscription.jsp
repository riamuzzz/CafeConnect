<%-- サブスクリプション --%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<link rel='stylesheet' href='../../css/style.css'>

	<title>かふぇコネクト</title>

</head>
<body>
<%-- ヘッダー --%>
<c:import url="../common/main_header.jsp"/>

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

		<form action="SubUserCreateExecute.action" method="post">
		<input type="submit" value="入会" class="nyu">
		</form>


	</div>

</div>
</body>