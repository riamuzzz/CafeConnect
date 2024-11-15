<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<link rel='stylesheet' href='../../css/style2.css'>
<div class="top_page">


<%-- ヘッダー --%>
<c:import url="../common/cafeheader.jsp"/>


<%-- ナビゲーション --%>
<c:import url="../common/cafenavigation.jsp"/>

<div class="wrap">
	<div class="lefttop">
	<p>在庫</p>
	<a href="MyPage.action">在庫一覧</a>
	<p>商品情報</p>
	<a href="MyPage.action">商品登録</a>
	<a href="CafeUserView.action">商品一覧</a>
	<p>売上情報</p>
	<a href="MyPage.action">売上情報一覧</a>
	</div>

	<div class="righttop">
	<p>顧客情報</p>
	<a href="MyPage.action">顧客情報一覧</a>
	<p>オンラインショップ・サブスク</p>
	<a href="MyPage.action">注文一覧</a>
	<a href="MyPage.action">発送済一覧</a>
	<p>モバイルオーダー</p>
	<a href="MyPage.action">注文一覧</a>
	<a href="MyPage.action">お渡し済一覧</a>
	</div>
</div>

<div class="content">

<%-- フッター --%>
<c:import url="../common/cafefooter.jsp"/>
</div>
</div>
</html>
