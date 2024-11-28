<%-- 科目登録JSP --%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<body>
<c:import url="../common/header.jsp" />

<div class ="main">

<c:import url="../common/navigation.jsp" />
<div class ="con">
	<h2>クレジットカード 新規登録</h2>

	<!-- 完了したらExeに行ってくれる -->
	<form action = "CardCreateExecute.action" method="post">


		<!-- カード番号を入力するとこ -->
		<label>カード番号</label>
		<input type="text" name="card_number" maxlength="16" value="${card.cardNumber}" required />
		<div>${errors.get("list")}</div>

		<!-- 有効期限を入力するとこ -->
		<label>有効期限</label>
		<input type="month" name="card_day" value="${card.cardExpiryDate}" required/>

		<!-- セキュリティ番号を入力するとこ -->
		<label>セキュリティ番号</label>
		<input type="text" name="card_cvc" value="${card.cardCvc}" required/>

		<!-- 名義を入力するとこ -->
		<label>名義</label>
		<input type="text" name="card_name" value="${card.cardName}" required/>

		<input type="submit" value="登録">
	</form>

	<!-- 科目全表示画面に戻る -->
	<a href="MyPage.action">戻る</a>
</div>
</div>
</body>
</html>