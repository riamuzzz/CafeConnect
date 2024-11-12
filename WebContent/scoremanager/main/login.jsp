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
	<p>ログイン</p>
	<form action = "LoginExecute.action" method="post">

		<!-- メールアドレス -->
		<label>メールアドレス</label>
			<input type="text" name="email" value="email">

		<!-- パスワード -->
		<label>パスワード</label>
			<input type="password" name="password" value="password">

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

<%@ include file="../common/footer.jsp" %>
</body>
</html>