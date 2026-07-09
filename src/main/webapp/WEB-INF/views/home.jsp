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
	<div class="card custom-card">
	  <div class="card-body">
	    <h5 class="card-title">Se hai passato un guaio, sei nel posto giusto !</h5>
	    <p class="card-text">Inserisci il tuo cap ed il farmaco richiesto. Ti indirizzeremo alle farmacie di zona che ne hanno disponibilità</p>
	    <form class="d-flex mt-3" action="searchfarmaco" role="search" method="GET">
          <input class="form-control me-2" type="text" placeholder="Cap" name="cap" aria-label="Cap"/>
          <div class="position-relative w-100">
          	<input class="form-control me-2" id="farmaco" type="text" placeholder="Farmaco" name="farmaco" aria-label="Farmaco" oninput="isExistFarmaco()"/>
          		<div id="autocompleteFarmaco" class="autocomplete-results position-absolute w-100 bg-white border rounded shadow-sm mt-1">
		          <div id="risultatoProdotto" class="autocomplete-item p-2">
		            ${prodotto}
		          </div>
		        </div>
		      </div>
		     <button class="btn btn-outline-success ms-2" type="submit">Cerca</button>
          </div>
        </form>
	  </div>
	</div>
	</section>
<script src="${pageContext.request.contextPath}/js/listfarmaci.js"></script>
</body>
</html>