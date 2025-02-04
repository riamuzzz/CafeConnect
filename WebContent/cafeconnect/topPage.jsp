<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<div class="top_page">


<%-- ヘッダー --%>
<c:import url="./common/header.jsp"/>


<div class="main">


<div class="content">
	<div class="slide-container slide-paused" ontouchstart="">
	<%--<img class="koukoku" src=img/top/top1.jpg> --%>
	<div class="slide-container">
	   <div class="slide-wrapper">
	      <a href="Subscription.action"><img class="slide" src=img/top/top1.jpg></a>
	      <a href="ProductView.action?categoryId=CATE02"><img class="slide" src=img/top/top2.jpg></a>
	      <a href="Mobile.action"><img class="slide" src=img/top/top3.jpg></a>

	   </div>
	   <div class="slide-wrapper">
	      <a href="Subscription.action"><img class="slide" src=img/top/top1.jpg></a>
	      <a href="ProductView.action?categoryId=CATE02"><img class="slide" src=img/top/top2.jpg></a>
	      <a href="Mobile.action"><img class="slide" src=../img/top/top3.jpg></a>

	   </div>
	   <div class="slide-wrapper">
	      <a href="Subscription.action"><img class="slide" src=img/top/top1.jpg></a>
	      <a href="ProductView.action?categoryId=CATE02"><img class="slide" src=img/top/top2.jpg></a>
	      <a href="Mobile.action"><img class="slide" src=img/top/top3.jpg></a>
	   </div>
	</div>
	</div>


	<h2 class="ranking">ランキング</h2>

	<div class="Ranking">
					<c:forEach var="product" items="${ products }" varStatus="status">
					<c:if test="${ status.index < 5 }">
					<table class="topTable"  >
 <tr>
                <th>
                    <!-- 1位の場合は王冠を表示 -->
                    <c:if test="${status.index == 0}">
                                                     👑
                    </c:if>
                    <c:if test="${status.index != 0}">
                        ${status.index + 1}
                    </c:if>
                </th>
            </tr>

					<tr>
						<th><a class="topImg" href="ProductDetailView.action?productId=${ product.productId }"><img src="img/product/${ product.image }" width="110"></a></th>
					</tr>
					<tr>
						<td><a class="caption" href="ProductDetailView.action?productId=${ product.productId }">${ product.productName }</a></td>
					</tr>
					<tr>
						<td><a class="caption" href="ProductDetailView.action?productId=${ product.productId }">${ product.price }円 </a></td>
					</tr>
					</table>
					</c:if>
					</c:forEach>
	</div>

</div>
</div>
</div>
</html>
