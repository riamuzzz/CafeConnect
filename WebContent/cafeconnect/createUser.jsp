<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<link href="css/style.css" rel="stylesheet" type="text/css" media="all">
<link rel='stylesheet' href='../css/userCreate.css'>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>かふぇコネクト</title>
</head>
<body>
	<%@ include file="common/header.jsp"%>

	<form action = "UserCreateExecute.action" method="post">
		<div class="userCreate">
		<p class="subTitle">新規会員登録</p>
		<table>
			<tr class="subTitle">
				<th>●会員情報</th>
			</tr>
			<tr>
				<th><label>氏名</label></th>
				<td><input type="text" name="name" required><td>
			</tr>
			<tr>
				<th><label>電話番号</label>
				<td><input type="tel" name="tel" placeholder="例：09012345678" maxlength="11" required><td>
			</tr>
			<tr>
				<th><label>住所</label></th>
				<td><input type="text" name="address" required></td>
			</tr>
			<tr>
				<th><label>メールアドレス</label></th>
				<td><input type="email" name="email" required></td>
			</tr>
			<tr>
				<th><label>パスワード</label></th>
				<td><input type="password" name="password" required></td>
			</tr>
			<tr>
				<th></th>
				<td></td>
			</tr>

			<tr class="subTitle">
				<th>●クレジットカード情報</th>
			</tr>

			<tr>
				<th><label>カード番号</label></th>
				<td><input type="text" name="card_number" maxlength="16" required></td>
			</tr>
			<tr>
				<th><label>有効期限</label></th>
				<td><input type="text" name="card_day" placeholder="例:01/01" maxlength="5"required></td>
			</tr>
			<tr>
				<th><label>セキュリティ番号</label></th>
				<td><input type="text" name="card_cvc"  maxlength="3" required></td>
			</tr>
			<tr>
				<th><label>カード名義</label></th>
				<td><input type="text" name="card_name" required></td>
			</tr>
		</table>

		<div class=sousin>
			<input type="submit" value="新規登録">
		</div>
	</div>
	</form>

	<%@ include file="common/footer.jsp"%>
</body>
</html>