<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<div class="top_page">


<%-- ヘッダー --%>
<c:import url="../common/main_header.jsp"/>


<div class="main">

	<div class="proimage">
		<img src="../img/product/${ product.image }">
	</div>

	<div class="probo">
	<table>
		<tr class="proNa"><td>${ product.productName }</td></tr>
		<tr class="proDe"><td>${ product.productDetail }</td></tr>
	</table>

		<div class="prosyo">
		<form id="myForm" method="post">
			<c:if test="${ !error.isEmpty()  }">
				<label>${ error }</label>
			</c:if>
			<div class="aaa">
			<c:if test="${ error==null  }">
				<label class="g">残り${ g }g</label>
				<div>
					<c:choose>
					    <c:when test="${ (g div 10) < product.count }">
					        <c:choose>
					            <c:when test="${ product.count > 0 }">
					                10 × <input type="number" name="count" min="1" max="${ g div 10 }" value="${ count }">
					                <input class="cart" type="submit" onclick="changeAction('SubscriptionSettlement.action')" value="決済画面へ">
					            </c:when>
					            <c:otherwise>
					                <span style="color:red;">売り切れ</span>
					            </c:otherwise>
					        </c:choose>
					    </c:when>

					    <c:otherwise>
					        <c:choose>
					            <c:when test="${ product.count > 0 }">
					                10 × <input type="number" name="count" min="1" max="${ product.count }" value="${ count }">g
					                <input class="cart" type="submit" onclick="changeAction('SubscriptionSettlement.action')" value="決済画面へ">
					            </c:when>
					            <c:otherwise>
					                <span style="color:red;">売り切れ</span>
					            </c:otherwise>
					        </c:choose>
					    </c:otherwise>
					</c:choose>
					<input type="hidden" name="productId" value="${product.productId}">
				</div>
			</c:if>
			<!-- 決済確認画面に遷移 -->
			<a href="Subscription.action">選択を続ける</a>
			</div>
		</form>
		<script>
		    // actionを変更する関数
		    function changeAction(path) {
		        document.getElementById("myForm").action = path;
		    }
		</script>
		</div>
	</div>

</div>
</div>
</html>
