<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<link rel='stylesheet' href='../../css/style2.css'>


<%-- ヘッダー --%>
<c:import url="../common/topcafeheader.jsp"/>


<%-- ナビゲーション --%>
<c:import url="../common/cafenavigation.jsp"/>

	<form action="CafePassResetExecute.action" method="post">
		<c:if test="${not empty error}"><div>現在のパスワードが一致しません</div></c:if>
		<label>現在のパスワード</label>
		<input type="password" id="oldPass" name="oldPass">
		<input type="checkbox" id="showPass1" onchange="togglePasswordVisibility()" />
		<label>新しいパスワード</label>
		<input type="password" id="newPass" name="newPass">
		<input type="checkbox" id="showPass2" onchange="togglePasswordVisibility2()" />
		<input type="submit" value="変更">
		<script>
			function togglePasswordVisibility() {
				let passwordInput = document.getElementById("oldPass");
				let showPasswordCheckbox = document.getElementById("showPass1");
				if (showPasswordCheckbox.checked) {
					passwordInput.type = "text";
				} else {
					passwordInput.type = "password";
				}
			}

			function togglePasswordVisibility2() {
				let passwordInput = document.getElementById("newPass");
				let showPasswordCheckbox = document.getElementById("showPass2");
				if (showPasswordCheckbox.checked) {
					passwordInput.type = "text";
				} else {
					passwordInput.type = "password";
				}
			}
		</script>
	</form>

<%-- フッター --%>
<c:import url="../common/cafefooter.jsp"/>
</html>
