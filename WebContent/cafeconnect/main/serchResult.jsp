<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>

<%-- ヘッダー --%>
<c:import url="../common/main_header.jsp"/>
<div class="mobMenu">

	<c:set var="shouldBreak" value="false" />
	<c:forEach var="drink" items="${ products }" varStatus="status">
	    <c:if test="${shouldBreak == false}">
	        <!-- ここで条件をチェック -->

	        <c:if test="${drink.category.categoryId == 'CATE02'}">
	            <div class="menutitle1">
	                <label>豆</label>
	            </div>
	            <!-- フラグをtrueにして、以降のループをスキップ -->
	            <c:set var="shouldBreak" value="true" />
	        </c:if>
	    </c:if>
	</c:forEach>


	<div class="drink">
		<c:forEach var="drink" items="${ products }" varStatus="status">
			<c:if test="${drink.category.categoryId == 'CATE02' }">
				<table class="mobTable">
				<tr>
					<th><a class="mobImg" href="ProductDetailView.action?productId=${ drink.productId }"><img src="../img/product/${ drink.image }" width="110"></a></th>
				</tr>
				<tr>
					<td><a class="caption" href="ProductDetailView.action?productId=${ drink.productId }">${ drink.productName }</a></td>
				</tr>
				<tr>
					<td><a class="caption" href="ProductDetailView.action?productId=${ drink.productId }">${ drink.price }円 </a></td>
				</tr>
				</table>
			</c:if>
		</c:forEach>
	</div>

	<c:set var="shouldBreak" value="false" />
	<c:forEach var="drink" items="${ products }" varStatus="status">
	    <c:if test="${shouldBreak == false}">
	        <!-- ここで条件をチェック -->

	        <c:if test="${drink.category.categoryId == 'CATE01'}">
	            <div class="menutitle1">
	                <label>ドリンク</label>
	            </div>
	            <!-- フラグをtrueにして、以降のループをスキップ -->
	            <c:set var="shouldBreak" value="true" />
	        </c:if>
	    </c:if>
	</c:forEach>

	<div class="drink">
		<c:forEach var="drink" items="${ products }" varStatus="status">
			<c:if test="${drink.category.categoryId == 'CATE01' }">
				<table class="mobTable">
				<tr>
					<th><a class="mobImg" href="ProductDetailView.action?productId=${ drink.productId }"><img src="../img/product/${ drink.image }" width="110"></a></th>
				</tr>
				<tr>
					<td><a class="caption" href="ProductDetailView.action?productId=${ drink.productId }">${ drink.productName }</a></td>
				</tr>
				<tr>
					<td><a class="caption" href="ProductDetailView.action?productId=${ drink.productId }">${ drink.price }円 </a></td>
				</tr>
				</table>
			</c:if>
		</c:forEach>
	</div>


	<c:set var="shouldBreak" value="false" />
	<c:forEach var="drink" items="${ products }" varStatus="status">
	    <c:if test="${shouldBreak == false}">
	        <!-- ここで条件をチェック -->

	        <c:if test="${drink.category.categoryId == 'CATE03'}">
	            <div class="menutitle1">
	                <label>フード</label>
	            </div>
	            <!-- フラグをtrueにして、以降のループをスキップ -->
	            <c:set var="shouldBreak" value="true" />
	        </c:if>
	    </c:if>
	</c:forEach>

	<div class="drink">
		<c:forEach var="drink" items="${ products }" varStatus="status">
			<c:if test="${drink.category.categoryId == 'CATE03' }">
				<table class="mobTable">
				<tr>
					<th><a class="mobImg" href="ProductDetailView.action?productId=${ drink.productId }"><img src="../img/product/${ drink.image }" width="110"></a></th>
				</tr>
				<tr>
					<td><a class="caption" href="ProductDetailView.action?productId=${ drink.productId }">${ drink.productName }</a></td>
				</tr>
				<tr>
					<td><a class="caption" href="ProductDetailView.action?productId=${ drink.productId }">${ drink.price }円 </a></td>
				</tr>
				</table>
			</c:if>
		</c:forEach>
	</div>
</div>
</html>