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

<table id="item-table">
  <c:forEach var="pList" items="${pList}" varStatus="status">
    <tr class="item" data-index="${status.index}">
      <!-- 商品画像 -->
      <td>
        <img src="../img/product/${pList.image}" alt="${pList.productName}">
      </td>
      <!-- 商品名 -->
      <td>${pList.productName}</td>

      <!-- 価格 -->
      <td>
        <span class="price" data-index="${status.index}">${pList.price}</span>円
      </td>
      <!-- 数量選択 -->
      <td>
        <c:forEach var="cList" items="${cList}">
          <c:if test="${cList.product.productId eq pList.productId}">
            <select name="num${ cList.product.productId }" class="quantity" data-index="${status.index}" onchange="calculateTotal()">
              <option value="1" <c:if test="${cList.count == 1}">selected</c:if>>1</option>
              <option value="2" <c:if test="${cList.count == 2}">selected</c:if>>2</option>
              <option value="3" <c:if test="${cList.count == 3}">selected</c:if>>3</option>
              <option value="4" <c:if test="${cList.count == 4}">selected</c:if>>4</option>
              <option value="5" <c:if test="${cList.count == 5}">selected</c:if>>5</option>
            </select>
          </c:if>
        </c:forEach>
      </td>
      <!-- カート削除ボタン -->
      <td>
        <form action="CartDelete.action" method="post">
          <input type="hidden" name="productId" value="${pList.productId}">
          <input type="submit" value="カートから削除">
        </form>
      </td>

    </tr>

  </c:forEach>

	<!-- 合計金額を表示 -->
	<td id="totalAmount">合計金額: 0円</td>

       <!-- レジへボタン -->
     <td>
       <form action="Settlement.action" method="post">

        <c:forEach var="cList" items="${cList}" varStatus="status">

         <input type="hidden" name="cList" value='[{}]'>

         </c:forEach>

         <input type="submit" value="レジへ進む">
       </form>
     </td>

</table>

</div>

</div>
</div>
<script src="./js/cartView.js"></script>
<%-- フッター --%>
<c:import url="../common/footer.jsp"/>


</html>
