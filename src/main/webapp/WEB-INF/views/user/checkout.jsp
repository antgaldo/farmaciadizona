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
				<div class="col-md-6 pr-1">
					<h3 class="mb-3">Riepilogo</h3>
					<table class="table table-sm">
					  <tbody>
					  <c:forEach var="c" items="${cart}">
					    <tr>
					      <th scope="row"><img class="width70" src="${pageContext.request.contextPath}/uploads/${c.path}"/></th>
					      <td class="text-capitalize textFarmacia">${c.nomeFarmacia}</td>
					      <td>${c.nome}</td>
					      <td>${c.quantita}x</td>
					      <td>€ ${c.prezzo}</td>
					    </tr>
					   </c:forEach>
					   	<tr>
					      <th scope="row"></th>
					      <td></td>
					      <td class="text-capitalize textTotale">Totale:</td>
					      <td class="text-capitalize textTotale">${quantitaTotale}</td>
					      <td class="text-capitalize textTotale">€ ${totale}</td>
					    </tr>
					  </tbody>
					</table>
				</div>
				<div class="col-md-6">
					<h3 class="mb-3">Pagamento</h3>

					<div class="card">
						    <div class="card-body">
						    	  <% if (session.getAttribute("userid") == null) { %>
									  <div class="col-md-12 py-1">Fai 
									  	<a href="login">login</a> per continuare o 
									  	<a href="register">registrati</a>
									  </div>
								  <% } %>
								  <div class="col-md-12">								  
								    <div class="form-check">
								      <input class="form-check-input" type="radio" id="gridCheck" value="infarmacia" name="choise">
								      <label class="form-check-label" for="gridCheck">
								        Ritiro in farmacia
								      </label>
								    </div>
								     <div class="col-md-12">
									     <form class="py-1 displaynone" id="inFarmacia" action="order" method="POST">
										  <input type="hidden" name="inFarmacia">
										 	 <% if (session.getAttribute("userid") == null) { %>
										   		<button type="submit" class="btn btn-primary" disabled>Conferma</button>
										   	 <% } else { %>
										   	 	<button type="submit" class="btn btn-primary">Conferma</button>
										   	 <% } %>
										</form>
									</div>
								  </div>
								  <div class="col-md-12 py-1">
								    <div class="form-check">
								      <input class="form-check-input" type="radio" id="gridCheck" value="spedizione" name="choise">
								      <label class="form-check-label" for="gridCheck">
								        Spedizione
								      </label>
								    </div>
								    
								    <form class="py-1 displaynone" id="formSpedizione">
									  <div class="col-md-10">
									    <label for="inputAddress" class="form-label">Address</label>
									    <input type="text" class="form-control" id="inputAddress" placeholder="1234 Main St">
									  </div>
									  <div class="col-md-6">
									    <label for="inputCity" class="form-label">City</label>
									    <input type="text" class="form-control" id="inputCity">
									  </div>
									  <div class="col-md-2">
									    <label for="inputZip" class="form-label">Zip</label>
									    <input type="text" class="form-control" id="inputZip">
									  </div>
									  <div class="col-md-12 py-1">
									     <% if (session.getAttribute("userid") == null) { %>
									   		<button type="submit" class="btn btn-primary" disabled>Conferma</button>
									   	 <% } else { %>
									   	 	<button type="submit" class="btn btn-primary">Conferma</button>
									   	 <% } %>
									  </div>
									</form>
								  </div>
						    </div>
					    </div>
				</div>
			</div>
		</section>
	</div>
</section>
<script>
document.querySelectorAll('input[name="choise"]').forEach((radio) => {
  radio.addEventListener('change', function() {
    if (this.checked) {
    	if(this.value=="infarmacia"){
    		document.getElementById("inFarmacia").classList.remove("displaynone");
    		document.getElementById("formSpedizione").classList.add("displaynone");
    	}
    	if(this.value=="spedizione"){
    		document.getElementById("formSpedizione").classList.remove("displaynone");
    		document.getElementById("inFarmacia").classList.add("displaynone");
    	}
    }
  });
});
</script>
<script src="${pageContext.request.contextPath}/js/cart.js"></script>
</body>
</html>