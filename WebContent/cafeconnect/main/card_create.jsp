<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<%-- ヘッダー --%>
<c:import url="../common/main_header.jsp" />
<div class="main">
	<c:import url="../common/navigation.jsp" />
	<div class="card">
		<form action="CardCreateExecute.action" method="post">
			<!-- カード番号を入力するとこ -->
			<div class="card-number">
				<label>カード番号</label>
				<div class="input-contener">
				<input type="text" name="card_number" maxlength="16" value="${card.cardNumber}"  required/>
				</div>
				<div>${errors.get("list")}</div>
			</div>
			<div class="card-day">
				<!-- 有効期限を入力するとこ -->
				<label>有効期限</label>
				<div class="input-contener">
				<input type="month" name="card_day" value="${card.cardExpiryDate}"  required/>
				</div>
			</div>
			<div class="card-cvc">
				<!-- セキュリティ番号を入力するとこ -->
				<label>セキュリティ番号</label>
				<div class="input-contener">
				<input type="text" name="card_cvc" value="${card.cardCvc}"  required/>
				</div>
			</div>
			<div class="card-name">
				<!-- 名義を入力するとこ -->
				<label>名義</label>
				<div class="input-contener">
				<input type="text" name="card_name" value="${card.cardName}"  required/>
				</div>
			</div>
			<div class="card-submit">
				<input type="submit" value="登録">
				<a class="return" href="MyPage.action">戻る</a>
			</div>
		</form>
		<!-- 科目全表示画面に戻る -->
	</div>
</div>
<!-- navigation.jspの<div class="all">の終了タグ -->
</div>
</html>