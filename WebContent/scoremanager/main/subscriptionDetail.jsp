<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<%-- ヘッダーのインポート --%>


<c:forEach var="order" items="${orders}">
    <div>${order.product.productName}</div>
</c:forEach>

<%-- フッターのインポート --%>
<c:import url="../common/footer.jsp"/>
</html>
