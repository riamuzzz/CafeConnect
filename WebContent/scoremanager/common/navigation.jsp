<%-- サイドバー --%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<div class="all">
	<div class="nav">
		<div class="name">${user.userName}</div>
		<div class="account_information"><a href="MyPage.action">アカウント情報</a></div>
		<div class="purchase_history">購入履歴</div>
		<div class="card_create"><a href="CardCreate.action">クレジットカード登録</a></div>

		<div class="space"></div>
	</div>