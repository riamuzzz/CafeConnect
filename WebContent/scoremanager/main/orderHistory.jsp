<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>


<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<html>
<%-- ヘッダー --%>
<c:import url="../common/main_header.jsp" />
<div class="main">
	<%-- ナビゲーション --%>
	<c:import url="../common/navigation.jsp" />
	<div class="order-history">
		<c:forEach var="order" items="${orders}">
			<div class="order">
				<div class="day">
					<fmt:formatDate value="${order.orderTime}" pattern="yyyy年M月d日" />
					に購入
				</div>
				<div class="order-contents">
					<div class="image">
						<img src="../img/product/${order.product.image}">
					</div>
					<div class="name">${order.product.productName}
							<div class="price">￥${order.product.price}</div><div class="space"></div>
					</div>
					<div class="count">個数：${order.count}</div>
				</div>
			</div>
		</c:forEach>
	</div>
</div>
<%-- フッター --%>
<c:import url="../common/footer.jsp" />
<!-- navigation.jspの<div class="all">の終了タグ -->
</div>
</html>
