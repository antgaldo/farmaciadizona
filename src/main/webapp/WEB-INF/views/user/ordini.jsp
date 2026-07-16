<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<!DOCTYPE html>
<%@ include file="/WEB-INF/views/user/header.jsp" %>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<%@ include file="/WEB-INF/views/user/menu.jsp" %>
<section class="mt-130">
	<div class="container">
		<section class="ricerca">
			<div class="flex">
				<div class="col-md-12">
					<h3 class="mb-3">Riepilogo</h3>
					<table class="table table-sm">
					  <thead>
				        <tr>
				            <th scope="col">Farmacia</th>
				            <th scope="col">id Ordine</th>
				            <th scope="col">data di acquisto</th>
				            <th scope="col">lista farmaci</th>
				        </tr>
			    	  </thead>
					  <tbody>
					  <c:forEach var="o" items="${ordini}">
					    <tr>
					      <td class="text-capitalize textFarmacia">${o.nomeFarmacia}</td>
					      <td class="text-capitalize textFarmacia">${o.ordineId}</td>
					      <td>${o.dataAcquisto}</td>
					      <td>
		                    <table class="table table-sm table-borderless mb-0">
		                        <tbody>
		                            <c:forEach var="l" items="${o.listaOrdini}">
		                                <tr>
		                                    <td>${l.nomeProdotto}</td>
		                                    <td>x${l.quantitaProdotto}</td>
		                                    <td>&euro; ${l.prezzoProdotto}</td>
		                                </tr>
		                            </c:forEach>
		                        </tbody>
		                    </table>
					      </td>
					    </tr>
					    <tr>
					      <td colspan="3"></td>
					      <td class="textEnd textTotale" colspan="4">Totale: € ${o.totale}</td>
					    </tr>
					   </c:forEach>
					  </tbody>
					</table>
				</div>
			</div>
		</section>
	</div>
</section>
<script src="${pageContext.request.contextPath}/js/cart.js"></script>
</body>
</html>