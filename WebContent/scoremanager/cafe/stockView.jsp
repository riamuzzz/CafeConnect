<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<div class="cafe_user_view">

<%-- ヘッダー --%>
<c:import url="../common/cafeheader.jsp"/>

<div class="main">
<%-- ナビゲーション --%>
<c:import url="../common/cafenavigation.jsp"/>

	<div class="stock_view1">
		<h2>在庫一覧</h2>
	<form method="get">
		<label>カテゴリ </label>
		<select name="f1">
			<option value="0">--------</option>
			<c:forEach var="category" items="${categoryName}">
				<%-- 現在のyearと選択されていたf1が一致していた場合selectedを追記 --%>
				<option value="${category.categoryId}" <c:if test="${category.categoryName==category.categoryName}">selected</c:if>>${category.categoryName}</option>
			</c:forEach>
		</select>

		<label>商品名</label>
		<label for="keyword">検索キーワード:</label>
        <input type="text" id="keyword" name="keyword" >
        <button type="submit">検索</button>

		<div>${errors.get("f1")}</div>
	</form>



<c:choose>
    <c:when test="${product.size() > 0}">
    <c:if test="${not empty error}">
		<div>在庫数よりも小さい値を入力してください</div>
    </c:if>

        <form action="StockUpdateExecute.action" method="post">
            <table>
                <tr>
                    <th>商品名</th>
                    <th>在庫数</th>
                    <th>入庫数</th>
                    <th>廃棄数</th>
                    <th>最終入荷日</th>
                </tr>

                <!-- 商品情報のループ -->
                <c:forEach var="product" items="${product}">
                	<input type="hidden" name="productId" value="${ product.productId }">
                    <tr>
                        <td>${product.productName}</td>
                        <td>${product.count}</td>
                        <td>
                            <!-- 入庫数 -->
							<input type="number" name="join_${product.productId}" value="0" required>
							<input type="hidden" name="join_${product.productId}" value="${product.count}" required>
                        </td>
                        <td>
                            <!-- 廃棄数 -->
                            <input type="number" name="disposal_${product.productId}" value="0" required>
                            <input type="hidden" name="disposal_${product.productId}" value="${product.count}" required>
                        </td>
                        <c:set var="formattedDate">
                            <fmt:formatDate value="${product.inStockDay}" pattern="yyyy/MM/dd" />
                        </c:set>
                        <td>${formattedDate}</td>
                    </tr>
                </c:forEach>
            </table>

            <!-- 送信ボタン -->
            <input type="submit" value="変更">
        </form>

    </c:when>

    <c:otherwise>
        <div>商品情報が存在しませんでした</div>
    </c:otherwise>
</c:choose>
<%-- フッター --%>
<c:import url="../common/cafefooter.jsp"/>
</div>
</div>
</html>