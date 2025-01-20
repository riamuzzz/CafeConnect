
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>かふぇコネクト</title>
<link rel="stylesheet"  href="../css/style2.css">
</head>
<body>
<div class="Login">
	<form action = "CafeLoginExecute.action" method="post">

		<h2>ログイン</h2>

		<!-- メールアドレス -->
		<label>ID</label>
			<input type="text" name="email" value="USER001">

		<!-- パスワード -->
		<label>パスワード</label>
			<input type="password" id="password" name="password" value="1">

		<!-- パスワード表示チェックボックス -->
		<input type="checkbox" id="showPassword" onchange="togglePasswordVisibility()" />
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
		<input type="submit" name="login" id="loginbutton" value="ログイン"/>

	</form>
</div>


</body>
</html>
