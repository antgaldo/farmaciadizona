<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<!DOCTYPE html>
<%@ include file="/WEB-INF/views/user/header.jsp" %>
<html>
<head>
<meta charset="UTF-8">
<title>Ordini</title>
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
					    <tr class="alternacolore">
					      <td class="text-capitalize textFarmacia bzero verticaltop">${o.nomeFarmacia}</td>
					      <td class="text-capitalize textFarmacia bzero verticaltop">${o.ordineId}</td>
					      <td class="bzero verticaltop">${o.dataAcquisto}</td>
					      <td>
						    <c:forEach var="l" items="${o.listaOrdini}">
						        <div class="farmaco">
						            <span>${l.nomeProdotto}</span>
						            <span>x${l.quantitaProdotto}</span>
						            <span>€ ${l.prezzoProdotto}</span>
						        </div>
						    </c:forEach>
						
						    <div class="farmaco totale">
						        <span>Totale:</span>
						        <span></span>
						        <span>€ ${o.totale}</span>
						    </div>
						</td>
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