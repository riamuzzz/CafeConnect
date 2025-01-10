<%-- ヘッダー --%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<head>
<%-- ヘッダー --%>
<c:import url="./common/header.jsp"/>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<link rel='stylesheet' href='../css/style.css'>

	<title>かふぇコネクト</title>

</head>

<div class=otoiawase>
	<div class=otoi>
		<label>お問い合わせ</label>
	</div>
	<form action = "ContactExecute.action" method="post">

		<table class=hyou>
		<tr>
			<th><label>メールアドレス</label></th>
			<td><input type="text" name="mail"></td>
		</tr>
		<tr>
			<th><label>お名前</label></th>
			<td><input type="text" name="name"></td>
		</tr>
		<tr>
			<th><label>電話番号</label></th>
			<td><input type="text" name="tel"></td>
		</tr>
			<tr>
			<th><label>お問い合わせ内容</label></th>
			<td><textarea name="message"></textarea></td>
		</tr>
		</table>

		<div class=sousin>
			<input type="submit" value="送信する">
		</div>

	</form>

</div>