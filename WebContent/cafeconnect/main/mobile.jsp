<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<html>
<link rel='stylesheet' href='../../css/mao.css'>
	<%-- ヘッダー --%>
	<c:import url="../common/main_header.jsp" />
	<div class="main">
		<%-- ナビゲーション --%>
		<c:import url="../common/navigation.jsp" />
		<h2>受付番号</h2>
		<div class="receptionNumber">${receptionId}</div>
		<c:forEach var="mobile" items="${mobilies}" >
			<div class="mobileOrder">
				<div class="name-count">${mobile.product.productName}×${mobile.count}</div>
			</div>
		</c:forEach>
	</div>
<script src="./js/cartView.js"></script>
<%-- フッター --%>
<c:import url="../common/footer.jsp" />
</html>
