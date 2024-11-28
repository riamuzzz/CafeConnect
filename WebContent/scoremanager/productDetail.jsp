<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<div class="top_page">


<%-- ヘッダー --%>
<c:import url="./common/header.jsp"/>


<div class="main">
<%-- ナビゲーション --%>
<c:import url="./common/navigation.jsp"/>

<table>
	<tr><td><img src="img/product/${ product.image }"></td></tr>
	<tr><td>${ product.productName }</td></tr>
	<tr><td>${ product.productDetail }</td></tr>
	<tr><td>${ product.price }円（税込）</td></tr>

</table>
<form id="myForm" method="post">
	<label>数量</label>
	<select name="num">
		<option value="1">1</option>
  		<option value="2">2</option>
  		<option value="3">3</option>
  		<option value="4">4</option>
  		<option value="5">5</option>
	</select>

	<input type="hidden" name="product" value="${ product.productId }">
	 <input type="submit" onclick="changeAction('main/Settlement.action')" value="決済画面へ">
	 <input type="submit" onclick="changeAction('main/CartCreateExecute.action')" value="カートに追加">
	<!--<button type="button" onclick="changeAction('main/Settlement.action')">決済画面へ</button>
	<button type="button" onclick="changeAction('main/CartCreate.action')">カートに追加</button> -->
</form>
<script>
    // actionを変更する関数
    function changeAction(path) {
        document.getElementById("myForm").action = path;
    }
</script>
<a href="TopPageExecute.action">選択を続ける</a>


<%-- フッター --%>
<c:import url="./common/footer.jsp"/>
</div>
</div>
</html>
