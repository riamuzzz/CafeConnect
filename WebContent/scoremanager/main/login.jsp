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
<%@ include file="../common/header.jsp" %>
<div class = "login">
	<div class = "strlogin">
		<p>ログイン</p>
	</div>
	<form action = "LoginExecute.action" method="post">

			<!-- メールアドレス -->
			<div class = "pass">
				<div class = "strpass">
					<label>メールアドレス</label>
				</div>
				<input type="text" name="email" value="email">
			</div>

			<!-- パスワード -->
			<div class = "pass">
				<div class = "strpass">
					<label>パスワード</label>
				</div>
				<input type="password" id=password  name="password" value="password">
			</div>
			<!-- パスワード表示チェックボックス -->
			<div class = "passchk">
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
			 </div>


			<!-- ログイン用ボタン -->
			<div class = "botan">
				<input type="submit" name="login" value="ログイン"/>
			</div>

	</form>
</div>
<%@ include file="../common/footer.jsp" %>
</body>
</html>