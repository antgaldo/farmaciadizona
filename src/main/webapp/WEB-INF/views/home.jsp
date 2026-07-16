<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
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
	<section class="hero">
		<div class="card-home">
		  <div class="card-body-home">
		    <h5 class="card-title">Se hai passato un guaio, sei nel posto giusto !</h5>
		    <p class="card-text">Inserisci il tuo cap ed il farmaco richiesto. Ti indirizzeremo alle farmacie di zona che ne hanno disponibilità</p>
			<form class="form-home" id="searchfarmaco"  action="searchfarmaco" role="search" method="GET">
			  <div class="flex pos-relative col-md-6">
			    <input class="form-control" type="text" placeholder="Cap" name="cap" aria-label="Cap"/>
			    <div class="invalidinput displaynone">Il cap non è valido</div>
			  </div>
			  <div class="flex pos-relative col-md-6">
			    <input class="form-control" id="farmaco" type="text" placeholder="Farmaco" name="farmaco" aria-label="Farmaco" oninput="isExistFarmaco()"/>
			    <div class="invalidinput displaynone">Il campo del cap è vuoto</div>
			    <div id="autocompleteFarmaco" class="autocomplete-results">
			      <div id="risultatoProdotto" class="autocomplete-item p-2">
			        ${prodotto}
			      </div>
			    </div>
			  </div>
			  <button class="btn btn-outline-success" type="submit">Cerca</button>
			</form>
		  </div>
		</div>
	</section>
<script src="${pageContext.request.contextPath}/js/cart.js"></script>
<script src="${pageContext.request.contextPath}/js/listfarmaci.js"></script>
<script src="${pageContext.request.contextPath}/js/checkform.js"></script>
</body>
</html>