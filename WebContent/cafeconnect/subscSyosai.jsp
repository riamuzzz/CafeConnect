<%-- サブスクリプション --%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<head>
<c:import url="./common/header.jsp"/>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<link rel='stylesheet' href='../css/style.css'>

	<title>かふぇコネクト</title>

</head>
<body>
<div class="syosai">
		<div class="syohin">
			<table>
				<c:forEach var="product" items="${products}">
					<tr>
						<td>
							<img src="img/product/${ product.image }">
						</td>
					</tr>
					<tr><td>${ product.productName }</td></tr>

					<tr><td>${ product.productDetail }</td></tr>

				</c:forEach>
			</table>
		</div>

		<div class="kaikei">
			<lavel>グラムを入力</lavel>

				<div class="ryo">残り○○g</div>

				<div class="kazu">
					<lavel>10×<input placeholder="1">g</lavel>
				</div>

				<div class="bota1">
					<a href="/main">選択を続ける</a>
				</div>
				<div class="bota2">
					<a href="/main">決済画面へ</a>
				</div>
		</div>

</div>
</body>