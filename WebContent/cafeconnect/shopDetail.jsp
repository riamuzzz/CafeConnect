<%-- サブスクリプション --%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<head>
<c:import url="./common/header.jsp"/>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<link rel='stylesheet' href='../css/style.css'>

	<title>かふぇコネクト</title>

</head>
<body>
<div class="subsc">

	<div class="title">
	<label>店舗情報</label><br>
	</div>

	<div class="setumei">
	<label>かふぇコネクトは、木の温もりを感じる落ち着いた空間で、ゆったりとしたひとときをお楽しみいただけるカフェです。<br>
			オーガニックコーヒーや自家製スイーツを取り揃え、心と体に優しいメニューをご提供しております。<br>
			一人でのんびり過ごすのも、大切な方との語りの時間にもぴったり。</label>
	</div>

	<div class="tenpogaiyou">

		<div class="tenpoimg">
		<img src=img/tenpo/店舗情報.jpg width="350">
		</div>

		<div class="syousai">
			<label class="zyusyo">〒○○○-○○○○<br> ○○県○○市○○区○○町○○○-○<br>
								     🚃アクセス：○○駅 徒歩5分<br><br>
								     🕒営業時間：10:00～20:00<br>
								     📅定休日：毎週水曜日・日曜日<br><br>
								     📞電話番号:○○○-○○○○-○○○○</label>
		</div>
	</div>

</div>
</body>