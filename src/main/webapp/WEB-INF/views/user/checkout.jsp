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
				<div class="col-md-6 pr-1">
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
										  <input type="hidden" name="inFarmacia" value="inFarmacia">
										 	 <% if (session.getAttribute("userid") == null) { %>
										   		<button type="submit" class="btn btn-outline-success" disabled>Conferma</button>
										   	 <% } else { %>
										   	 	<button type="submit" class="btn btn-outline-success">Conferma</button>
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
								    
								    <form class="py-1 displaynone" id="formSpedizione" action="order" method="POST">
								      <fieldset>Spedizione</fieldset>
								      <input type="hidden" name="formSpedizione" value="formSpedizione">
								      <div class="col-md-10 pos-relative">
									    <label for="inputNome" class="form-label">Nome</label>
									    <input type="text" name="nome" class="form-control" id="inputNome" placeholder="Nome">
									    <div class="invalidinput displaynone">Nome non valido</div>
									  </div>
									  <div class="col-md-10 pos-relative">
									    <label for="inputCognome" class="form-label">Cognome</label>
									    <input type="text" name="cognome" class="form-control" id="inputCognome" placeholder="Cognome">
									    <div class="invalidinput displaynone">Cognome non valido</div>
									  </div>
									  <div class="col-md-10 pos-relative">
									    <label for="inputAddress" class="form-label">Indirizzo</label>
									    <input type="text" name="indirizzo" class="form-control" id="inputAddress" placeholder="Indirizzo">
									    <div class="invalidinput displaynone">Il campo indirizzo è vuoto</div>
									  </div>
									  <div class="col-md-6 pos-relative">
									    <label for="inputCitta" class="form-label">citta</label>
									    <input type="text" name="citta" class="form-control" id="inputCitta">
									    <div class="invalidinput displaynone">Il campo citta è vuoto</div>
									  </div>
									  <div class="col-md-2 mb-20 pos-relative">
									    <label for="inputCap" class="form-label">cap</label>
									    <input type="text" name="cap" class="form-control" id="inputCap">
									    <div class="invalidinput displaynone">Il campo cap non è valido</div>
									  </div>
									  <fieldset>Pagamento</fieldset>
									  <div class="col-md-10 pos-relative">
									    <label for="inputCarta" class="form-label">Numero di carta</label>
									    <input type="text" name="carta" class="form-control" id="inputCarta" placeholder="Numero di carta">
									    <div class="invalidinput displaynone">Numero di carta non valido</div>
									  </div>
									  <div class="col-md-6 pos-relative">
									    <label for="inputScadenza" class="form-label">Scadenza</label>
									    <input type="text" name="scadenza" class="form-control" id="inputScadenza">
									    <div class="invalidinput displaynone">Scadenza non valida</div>
									  </div>
									  <div class="col-md-2 pos-relative">
									    <label for="inputCircuito" class="form-label">Circuito</label>
									    <select class="form-control" name="circuito" id="inputCircuito">
									    	<option value="vista">visa</option>
									    	<option value="mastercard">mastercard</option>
									    </select>
									  </div>
									  <div class="col-md-12 py-1">
									     <% if (session.getAttribute("userid") == null) { %>
									   		<button type="submit" class="btn btn-outline-success" disabled>Conferma</button>
									   	 <% } else { %>
									   	 	<button type="submit" class="btn btn-outline-success">Conferma</button>
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
<script src="${pageContext.request.contextPath}/js/checkform.js"></script>
</body>
</html>