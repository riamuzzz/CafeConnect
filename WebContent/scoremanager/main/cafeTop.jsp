<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<div class="top_page">


<%-- ヘッダー --%>
<c:import url="./common/cafeheader.jsp"/>


<div class="main">
<%-- ナビゲーション --%>
<c:import url="./common/cafenavigation.jsp"/>

<a href="main/MyPage.action">マイページ</a>
<a href="Login.action">login</a>
<div class="content">



<%-- フッター --%>
<c:import url="./common/cafefooter.jsp"/>
</div>
</div>
</html>
