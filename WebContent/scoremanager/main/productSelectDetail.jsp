<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<div class="top_page">


<%-- ヘッダー --%>
<c:import url="../common/header.jsp"/>


<div class="main">
<%-- ナビゲーション --%>
<c:import url="../common/navigation.jsp"/>

<table>
	<tr><td><img src="../img/product/${ product.image }"></td></tr>
	<tr><td>${ product.productName }</td></tr>
	<tr><td>${ product.productDetail }</td></tr>
	<tr><td>${ product.price }円（税込）</td></tr>
</table>
<form id="myForm" method="post">
	<label>残り${ g }g</label>
	<div>
		10 × <input type="number" name="count" value="${ count }">
	</div>
	<!-- 決済確認画面に遷移 -->
	 <input type="submit" onclick="changeAction('Settlement.action')" value="決済画面へ">
	 <!-- 選択を続けるを押すとカートに商品が追加され選択画面に戻る -->
	 <input type="submit" onclick="changeAction('main/CartCreateExecute.action')" value="選択を続ける">
</form>
<script>
    // actionを変更する関数
    function changeAction(path) {
        document.getElementById("myForm").action = path;
    }
</script>
<a href="TopPageExecute.action">選択を続ける</a>


<%-- フッター --%>
<c:import url="../common/footer.jsp"/>
</div>
</div>
</html>
