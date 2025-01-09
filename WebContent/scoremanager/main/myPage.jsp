<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<body>
<c:import url="../common/main_header.jsp" />
<a>asdfghjkl;</a>
<a href="CardCreate.action">クレジットカード登録</a>

	<form action="UserUpdateExecute.action" method="post">
		<label>名前</label>
		<input type="text" name="name" value="${ user.userName }">
		<input type="submit" value="変更">
	</form>
	<form action="UserUpdateExecute.action" method="post">
		<label>電話番号</label>
		<input type="text" name="tel" value="${ user.tel }">
		<input type="submit" value="変更">
	</form>
	<form action="UserUpdateExecute.action" method="post">
		<label>住所</label>
		<input type="text" name="address" value="${ user.address }">
		<input type="submit" value="変更">
	</form>
	<form action="UserUpdateExecute.action" method="post">
		<label>メールアドレス</label>
		<input type="text" name="email" value="${ user.email }">
		<input type="submit" value="変更">
	</form>
	<form action="#" method="post">
		<label>パスワード</label>
		<input type="password" name="password" value="${ user.userPassword }">
		<input type="submit" value="変更">
	</form>
	<form action="#" method="post">
		<label>入会しているサブスクリプション</label>
		<!-- USERSテーブルのsabscriptionがtrueなら入会中のプラン表示 -->
		<c:if test="${ user.subscription } == true">
			<div><!-- プラン表示 --></div>
		</c:if>
	</form>


<c:import url="../common/navigation.jsp" />
</body>
</html>