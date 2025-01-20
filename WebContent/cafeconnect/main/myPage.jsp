<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<%-- ヘッダー --%>
<c:import url="../common/main_header.jsp" />
<div class="main">
	<%-- ナビゲーション --%>
	<c:import url="../common/navigation.jsp" />
	<div class="mypage">
		<div class="name">
			<form action="UserUpdateExecute.action" method="post">
				<label>名前</label>
				<div class="input-contener">
					<input type="text" name="name" value="${ user.userName }">
					<input type="submit" value="変更">
				</div>
			</form>
		</div>
		<div class="tel">
			<form action="UserUpdateExecute.action" method="post">
				<label>電話番号</label>
				<div class="input-contener">
					<input type="text" name="tel" value="${ user.tel }"> <input
						type="submit" value="変更">
				</div>
			</form>
		</div>
		<div class="address">
			<form action="UserUpdateExecute.action" method="post">
				<label>住所</label>
				<div class="input-contener">
					<input type="text" name="address" value="${ user.address }">
					<input type="submit" value="変更">
				</div>
			</form>
		</div>
		<div class="mail">
			<form action="UserUpdateExecute.action" method="post">
				<label>メールアドレス</label>
				<div class="input-contener">
					<input type="text" name="email" value="${ user.email }"> <input
						type="submit" value="変更">
				</div>
			</form>
		</div>
		<div class="pass">
			<form action="#" method="post">
				<label>パスワード</label>
				<div class="input-contener">
					<input type="password" name="password"
						value="${ user.userPassword }"> <input type="submit"
						value="変更">
				</div>
			</form>
		</div>
		<div class="subscription">
			<label>入会しているサブスクリプション</label>
			<!-- USERSテーブルのsabscriptionがtrueなら入会中のプラン表示 -->
			<c:if test="${ user.subscription == false }">
				<div>
					<a href="Subscription.action">サブスクリプション登録</a>
				</div>
			</c:if>
			<c:if test="${ user.subscription == true }">
				<div>
					<a href="SabscriptionDetail.action">ライトプラン</a>
				</div>
			</c:if>
		</div>
	</div>
</div>
<%-- フッター --%>
<c:import url="../common/footer.jsp" />
<!-- navigation.jspの<div class="all">の終了タグ -->
</div>
</html>