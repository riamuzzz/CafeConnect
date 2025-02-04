<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>

<div class="productView">


<%-- ヘッダー --%>
<c:import url="../common/main_header.jsp"/>

<div class="main">
<%-- ヘッダーのインポート --%>

<h1>ライトプラン</h1>
<c:forEach var="order" items="${orders}">
    <div>${order.product.productName} ${ order.count }g</div>
</c:forEach>

<%-- フッター --%>
<c:import url="../common/footer.jsp"/>


</html>

