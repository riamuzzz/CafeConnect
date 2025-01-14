<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<link href="css/style.css" rel="stylesheet" type="text/css" media="all">
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>かふぇコネクト</title>
</head>
<body>
<%@ include file="common/header.jsp" %>
<div class=log>
	<div class=logtitle>会員ログイン</div>

	<div class=kaiin>会員の方はこちら</div>

	<div class=subNyukai>サブスク入会</div>

	<c:choose>
		<c:when test="${ redirect eq '/CafeConnect/scoremanager/main/Settlement.action' || redirect eq '/CafeConnect/scoremanager/main/CartCreateExecute.action' }">
			<form action = "LoginExecute.action?redirect=${ redirect }&num=${ num }&product=${ product }" method="post">
			<!-- メールアドレス -->
			<label>メールアドレス</label>
				<input type="text" name="email" value="gst1@icloud.com">

			<!-- パスワード -->
			<label>パスワード</label>
				<input type="password" id="password" name="password" value="pass01">

			<!-- パスワード表示チェックボックス -->
				<input type="checkbox" id="showPassword" onchange="togglePasswordVisibility()" />
					<label for="showPassword">パスワードを表示する</label>
						<script>
							function togglePasswordVisibility() {
								let passwordInput = document.getElementById("password");
								let showPasswordCheckbox = document.getElementById("showPassword");
								if (showPasswordCheckbox.checked) {
									passwordInput.type = "text";
								} else {
									passwordInput.type = "password";
								}
							}
						</script>

			<!-- ログイン用ボタン -->
			<input type="submit" name="login" value="ログイン"/>
		</form>
		</c:when>
		<c:when test="${not empty redirect}">
			<form action = "LoginExecute.action?redirect=${ redirect }" method="post">
			<!-- メールアドレス -->
			<label>メールアドレス</label><br>
				<input type="text" name="email" value="gst1@icloud.com"><br>

			<!-- パスワード -->
			<label>パスワード</label><br>
				<input type="password" id="password" name="password" value="pass01"><br>

			<!-- パスワード表示チェックボックス -->
				<input type="checkbox" id="showPassword" onchange="togglePasswordVisibility()" />
					<label for="showPassword">パスワードを表示する</label>
						<script>
							function togglePasswordVisibility() {
								let passwordInput = document.getElementById("password");
								let showPasswordCheckbox = document.getElementById("showPassword");
								if (showPasswordCheckbox.checked) {
									passwordInput.type = "text";
								} else {
									passwordInput.type = "password";
								}
							}
						</script><br>

			<!-- ログイン用ボタン -->
			<input type="submit" name="login" value="ログイン"/>
		</form>
		</c:when>
		<c:otherwise>
			<form action = "LoginExecute.action" method="post">
			<!-- メールアドレス -->
			<label>メールアドレス</label>
				<input type="text" name="email" value="gst1@icloud.com">

			<!-- パスワード -->
			<label>パスワード</label>
				<input type="password" id="password" name="password" value="pass01">

			<!-- パスワード表示チェックボックス -->
				<input type="checkbox" id="showPassword" onchange="togglePasswordVisibility()" />
					<label for="showPassword">パスワードを表示する</label>
						<script>
							function togglePasswordVisibility() {
								let passwordInput = document.getElementById("password");
								let showPasswordCheckbox = document.getElementById("showPassword");
								if (showPasswordCheckbox.checked) {
									passwordInput.type = "text";
								} else {
									passwordInput.type = "password";
								}
							}
						</script>

			<!-- ログイン用ボタン -->
			<input type="submit" name="login" value="ログイン"/>
		</form>
		</c:otherwise>
	</c:choose>

	<div class=sinki>
		<label>新規会員登録の方はこちら</label>
	</div>

	<div class=touroku>
		<label>✉メールアドレスで会員登録</label>
	</div>

<%@ include file="common/footer.jsp" %>
</div>
</body>
</html>