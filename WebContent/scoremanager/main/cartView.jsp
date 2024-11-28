<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<div class="productView">


<%-- ヘッダー --%>
<c:import url="../common/header.jsp"/>

<div class="main">
<%-- ナビゲーション --%>
<c:import url="../common/navigation.jsp"/>

<div class="content">

<h2>カート</h2>
<table>
  <c:forEach var="pList" items="${pList}" varStatus="status">
    <tr>
      <td>
        <img src="../img/product/${pList.image}" alt="${pList.productName}">
      </td>
    </tr>
    <tr>
      <td>${pList.productName}</td>
    </tr>
    <tr>
      <td>
        <span class="price" id="price-${status.index}">${pList.price}</span>円
      </td>
    </tr>
    <tr>
      <td>
       <c:forEach var="cList" items="${cList}">
      			<c:if test="${ cList.product.productId eq pList.productId }">
      				<input type="text" name="count" value="${ cList.count }" >
      			</c:if>
		</c:forEach>
      </td>
    </tr>
    <tr>
      <td>
      	<form action="CartDelete.action" method="post">
      		<input type="hidden" name="productId" value="${ pList.productId }">
	      	<input type="submit" value="カートから削除">
      	</form>
   	  </td>
    </tr>
  </c:forEach>
</table>

<!-- 合計金額を表示 -->
<p>合計: <span id="total" class="result">0</span>円</p>

<script>
function calculateTotal() {
	  let total = 0;
	  document.querySelectorAll('.quantity').forEach((select, index) => {
	    const quantity = parseInt(select.value, 10) || 0;
	    const price = parseInt(document.getElementById(`price-${index}`).textContent.trim(), 10) || 0;
	    total += quantity * price;
	  });
	  document.getElementById('total').textContent = total;
	}

	document.querySelectorAll('.quantity').forEach((select) => {
	  select.addEventListener('change', calculateTotal);
	});

	calculateTotal();
	</script>
</div>


<%-- フッター --%>
<c:import url="../common/footer.jsp"/>
</div>
</div>
</html>
