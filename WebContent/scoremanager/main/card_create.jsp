<%-- 科目登録JSP --%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<body>
<c:import url="/common/header.jsp" />

<div class ="main">

<c:import url="/common/navi.jsp" />
<div class ="con">
	<h2>クレジットカード 新規登録</h2>

	<!-- 完了したらExeに行ってくれる -->
	<form action = "CardCreateExecute.action" method="post">


		<!-- カード番号を入力するとこ -->
		<label>カード番号</label>
		<input type="text" name="card_number"
			placeholder="カード番号を入力してください" maxlength="16" value="${card_number}" required />
		<div>${errors.get("list")}</div>

		<!-- 有効期限を入力するとこ -->
		<label>有効期限</label>
		<input type="month" name="card_day"
			placeholder="有効期限を入力してください"  value="${card_day}" required/>

		<!-- セキュリティ番号を入力するとこ -->
		<label>セキュリティ番号</label>
		<input type="text" name="card_cvc"
			placeholder="セキュリティ番号を入力してください"  value="${card_cvc}" required/>

		<!-- 名義を入力するとこ -->
		<label>名義</label>
		<input type="text" name="card_name"
			placeholder="名義を入力してください"  value="${card_name}" required/>

		<input type="submit" value="登録">
	</form>

	<!-- 科目全表示画面に戻る -->
	<a href="------------.action">戻る</a>
</div>
</div>
</body>
</html>