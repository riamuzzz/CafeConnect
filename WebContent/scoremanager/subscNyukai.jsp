<%-- サブスク入会 --%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<link rel='stylesheet' href='../css/style.css'>

	<title>かふぇコネクト</title>

</head>
<body2>
	<div class="subscnyukai">

		<div class="title">
			<label>入会</label><br>
		</div>

		<div class="mail">
			<label>メールアドレス</label><br>
		</div>

		<div class="mail a">
			<input placeholder="メールアドレス">
		</div>

		<div class="pass">
			<label>パスワード</label><br>
		</div>

		<div class="pass a">
			<input placeholder="パスワード">
			<!-- 検索用ボタン -->
			<input type="submit" name="hyouzi" value="表示"/>
		</div>

		<div class="nyukaibotan">
			<a href="/main">入会</a>
		</div>

	</div>
</body2>