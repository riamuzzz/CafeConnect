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
	<form action = "ProductCreateExecute.action" method="post">

		<table class=hyou>
		<tr>
			<th><label>メールアドレス</label></th>
			<td><input type="text" name="productName"></td>
		</tr>
		<tr>
			<th><label>お名前</label></th>
			<td><input type="text" name="price"></td>
		</tr>
		<tr>
			<th><label>電話番号</label></th>
			<td><input type="text" name="count"></td>
		</tr>
			<tr>
			<th><label>お問い合わせ内容</label></th>
			<td><textarea name="count"></textarea></td>
		</tr>
		</table>

	</form>

	<div class=sousin>
		<label>送信する</label>
	</div>

</div>