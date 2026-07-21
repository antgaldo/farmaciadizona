<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<%@ include file="/WEB-INF/views/admin/header.jsp" %>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>Registrazione</title>
</head>
<body>


<div class="container text-center">
 <a class="navbar-brand" href="app">
 	<img class="width200" src="${pageContext.request.contextPath}/img/logo.png"/>
 </a>
  <div class="row">
   <div class="col-md-6 offset-md-3">
	<div class="form-check textleft my-1">
	  <input class="form-check-input" type="radio" id="gridCheck" value="form-1" name="choise">
	  <label class="form-check-label" for="gridCheck">
	    Registrati come Utente
	  </label>
	</div>
   	<form class="displaynone" id="form-1"  action="${pageContext.request.contextPath}/register" method="POST">
		<input name="action" value="registerUser" type="hidden">
		<div class="mb-3">
			<label class="form-label">Nome</label>
			<input id="nome" required type="text" class="form-control" name="nome" placeholder="nome">
		</div>
		<div class="mb-3">
			<label class="form-label">Password</label>
			<input id="password" required class="form-control" type="password" name="password" placeholder="password">
		</div>
		<div class="mb-3">
			<label class="form-label">Email</label>
			<input id="email" required class="form-control" type="text" name="email" placeholder="email">
		</div>
		<c:if test="${not empty errore}">
			<div class="alert alert-danger">
		 		${errore}
		  	</div>
		</c:if>
		<button type="submit" value="registerUser" class="btn btn-outline-success my-1" >Conferma</button>
	</form>
  	<div class="form-check textleft">
		<input class="form-check-input" type="radio" id="gridCheck" value="form-2" name="choise">
        <label class="form-check-label" for="gridCheck">
  		  Registrati come Farmacia
  		</label>
	</div>
   	<form class="displaynone" id="form-2" action="${pageContext.request.contextPath}/register" method="POST">
		<input name="action" value="registerAdmin" type="hidden">
		<div class="mb-3">
			<label class="form-label">Nome</label>
			<input id="nome" required type="text" class="form-control" name="nome" placeholder="nome">
		</div>
		<div class="mb-3">
			<label class="form-label">Password</label>
			<input id="password" required class="form-control" type="password" name="password" placeholder="password">
		</div>
		<div class="mb-3">
			<label class="form-label">Email</label>
			<input id="email" required class="form-control" type="email" name="email" placeholder="email">
		</div>
		<div class="mb-3">
			<label class="form-label">Nome Farmacia</label>
			<input id="nomeFarmacia" required class="form-control" type="text" name="nomeFarmacia" placeholder="nome Farmacia">
		</div>
		<div class="mb-3">
			<label class="form-label">Cap</label>
			<input id="cap" required class="form-control" type="integer" name="cap" placeholder="cap">
		</div>
		<input id="lat" type="hidden" name="lat">
		<input id="lon"  type="hidden" name="lon">
		<div class="mb-3 pos-relative">
			<label class="form-label">Indirizzo</label>
			<input id="indirizzo" required class="form-control" type="text" name="indirizzo" placeholder="Indirizzo" oninput="cercaIndirizzo()">
			<div id="autocompleteIndirizzo" class="autocomplete-results">
				<div id="risultatoProdotto" class="autocomplete-item p-2">
			  		<div id="risultatoindirizzo"></div>
			  	</div>
			</div>
		</div>
		<c:if test="${not empty errore}">
			<div class="alert alert-danger">
	   		 ${errore}
	   		 </div>
		</c:if>
		<button type="submit" value="registerAdmin" class="btn btn-outline-success my-1" >Conferma</button>
	</form>
   </div>
  </div>
</div>




</body>
<script src="${pageContext.request.contextPath}/js/searchaddress.js"></script>
<script src="${pageContext.request.contextPath}/js/choiceradio.js"></script>
<script src="${pageContext.request.contextPath}/js/checkform.js"></script>
</html>