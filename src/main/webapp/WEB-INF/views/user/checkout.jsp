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
	<div class="container-fluid row">
		
		<section class="ricerca col-md-6 bnone">
			<div class="prodotto">
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
		</section>
		<section class="ricerca col-md-6 bnone">
			<div class="prodotto">
				<h3 class="mb-3">Pagamento</h3>
				
				
				<div class="card mb-3 col">
				  <div class="row g-0">
				  	<div class="col-md-12">
					    <div class="card-body">
					    	  <% if (session.getAttribute("userid") == null) { %>
								  <div class="col-12">Fai 
								  	<a href="login">login</a> per continuare o 
								  	<a href="register">registrati</a></div>
								  <div class="col-12">
							  <% } %>
							  
							    <div class="form-check">
							      <input class="form-check-input" type="radio" id="gridCheck" value="infarmacia" name="choise">
							      <label class="form-check-label" for="gridCheck">
							        Ritiro in farmacia
							      </label>
							    </div>
							    
							     <form class="row g-3 my-4 dnone" id="inFarmacia" action="order" method="POST">
								  <input type="hidden" name="inFarmacia">
								  <div class="col-12">
								 	 <% if (session.getAttribute("userid") == null) { %>
								   		<button type="submit" class="btn btn-primary" disabled>Conferma</button>
								   	 <% } else { %>
								   	 	<button type="submit" class="btn btn-primary">Conferma</button>
								   	 <% } %>
								  </div>
								</form>
								
							  </div>
							  <div class="col-12">
							    <div class="form-check">
							      <input class="form-check-input" type="radio" id="gridCheck" value="spedizione" name="choise">
							      <label class="form-check-label" for="gridCheck">
							        Spedizione
							      </label>
							    </div>
							    
							    <form class="row g-3 my-4 dnone" id="formSpedizione">
								  <div class="col-md-6">
								    <label for="inputEmail4" class="form-label">Email</label>
								    <input type="email" class="form-control" id="inputEmail4">
								  </div>
								  <div class="col-md-6">
								    <label for="inputPassword4" class="form-label">Password</label>
								    <input type="password" class="form-control" id="inputPassword4">
								  </div>
								  <div class="col-12">
								    <label for="inputAddress" class="form-label">Address</label>
								    <input type="text" class="form-control" id="inputAddress" placeholder="1234 Main St">
								  </div>
								  <div class="col-md-6">
								    <label for="inputCity" class="form-label">City</label>
								    <input type="text" class="form-control" id="inputCity">
								  </div>
								  <div class="col-md-4">
								    <label for="inputState" class="form-label">State</label>
								    <select id="inputState" class="form-select">
								      <option selected>Choose...</option>
								      <option>...</option>
								    </select>
								  </div>
								  <div class="col-md-2">
								    <label for="inputZip" class="form-label">Zip</label>
								    <input type="text" class="form-control" id="inputZip">
								  </div>
								  <div class="col-12">
								    <div class="form-check">
								      <input class="form-check-input" type="checkbox" id="gridCheck">
								      <label class="form-check-label" for="gridCheck">
								        Check me out
								      </label>
								    </div>
								  </div>
								  <div class="col-12">
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
				
			</div>
		</section>


	</div>
</section>
<script>
document.querySelectorAll('input[name="choise"]').forEach((radio) => {
  radio.addEventListener('change', function() {
    if (this.checked) {
    	if(this.value=="infarmacia"){
    		document.getElementById("inFarmacia").classList.remove("dnone");
    		document.getElementById("formSpedizione").classList.add("dnone");
    	}
    	if(this.value=="spedizione"){
    		document.getElementById("formSpedizione").classList.remove("dnone");
    		document.getElementById("inFarmacia").classList.add("dnone");
    	}
    }
  });
});
</script>
</body>
</html>