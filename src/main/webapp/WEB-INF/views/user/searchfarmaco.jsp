<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<!DOCTYPE html>
<%@ include file="/WEB-INF/views/user/header.jsp" %>
<!-- Leaflet -->
<link rel="stylesheet" href="https://unpkg.com/leaflet/dist/leaflet.css" />
<script src="https://unpkg.com/leaflet/dist/leaflet.js"></script>

<!-- Maplibre GL -->
<link href="https://unpkg.com/maplibre-gl/dist/maplibre-gl.css" rel="stylesheet" />
<script src="https://unpkg.com/maplibre-gl/dist/maplibre-gl.js"></script>

<!-- Maplibre GL Leaflet  -->
<script src="https://unpkg.com/@maplibre/maplibre-gl-leaflet/leaflet-maplibre-gl.js"></script>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<%@ include file="/WEB-INF/views/user/menu.jsp" %>
<section class="mt-130">
	<div class="container-fluid">
	<!-- inizio ricerca -->
	<section class="ricerca">
		<div class="col-md-12 d-flex align-items-center gap-3">
	   		 <h3 class="mb-0 text-nowrap">Modifica ricerca</h3>
		    <form class="d-flex flex-grow-1" role="search" method="GET" action="searchfarmaco">
		        <div class="position-relative row">
		        	<input class="form-control ms-2 me-3 col" type="text" placeholder="Cap" name="cap" aria-label="Cap"/>
		            <input class="form-control me-3 col" id="farmaco" type="text" placeholder="Cerca farmaco" name="farmaco" aria-label="Farmaco" oninput="isExistFarmaco()"/>
		            <div id="autocompleteFarmaco" class="autocomplete-results position-absolute w-100 bg-white border rounded shadow-sm mt-1">
		                <div id="risultatoProdotto" class="autocomplete-item p-2">
		                    ${prodotto}
		                </div>
		            </div>
		        </div>
		        <button class="btn btn-outline-success ms-2" type="submit">Cerca</button>
		    </form>
		</div>
	</section>
	<!-- inizio descrizione farmaco -->
	<section class="ricerca">
		<div class="col-md-12 prodotto">
			<h3 class="mb-3">Prodotto cercato</h3>
			<div class="card noborder" >
			  <div class="row g-0">
			    <div class="col-md-2 border-right">
			      <img src="${pageContext.request.contextPath}/uploads/${prodotto.path}" class="img-fluid rounded-start" alt="...">
			    </div>
			    <div class="col-md-10">
			      <div class="card-body">
			        <h5 class="card-title">${prodotto.nome}<span class="badge text-bg-secondary ms-2">${prodotto.categoria}</span></h5>
			        <p class="card-text">${prodotto.descrizione}</p>
			        <p class="card-text"><small class="text-body-secondary">Guarda il foglietto illustrativo</small></p>
			      </div>
			    </div>
			  </div>
			</div>
		 </div>
	</section>
	
	<!-- inizio risultati ricerca -->
	<section class="ricerca">
	  <div class="row">
	  	<h3 class="mb-3 mt-3">Farmacie in zona</h3>
	    <div class="col-md-6">
	    <c:forEach var="f" items="${lista}">
	    <div class="card mb-3 col">
		  <div class="row g-0 opacityhover" 
		  	onmouseenter="evidenziaMarcatore('${f.idFarmacia}')" 
		  	onmouseleave="ripristinaMarcatore('${f.idFarmacia}')">
		    <div class="col-md-10">
		      <div class="card-body">
		        <h5 class="card-title">${f.farmaciaNome}</h5>
		        <p class="card-text"><i class="bi bi-compass"></i> ${f.indirizzo}</p>
		        <p class="card-text" class="container text-center">
		          <div class="row justify-content-between">
				    <div class="col-auto me-auto">
				      Quantità : 
				     <button type="button" id="decrement-${f.idFarmacia}" data-idfarmacia="${f.idFarmacia}" class="btn btn-outline-dark circle me-1" 
			       	 	onclick="decrementQuantit(this)">
						-
					 </button>
					 <span id="quantita-valore-${f.idFarmacia}">1</span>
					 <button type="button" id="increment-${f.idFarmacia}" data-idfarmacia="${f.idFarmacia}" class="btn btn-outline-dark circle ms-1"
					 	data-quantita="${f.quantita}"
			       	 	onclick="incrementQuantit(this)">
						+
					 </button>
				    </div>
				    <div class="col-auto">
				     <button type="button" class="btn btn-outline-success" 
			       	 	data-nome="${prodotto.nome}" 
			       	 	data-idprodotto="${prodotto.id}" 
			       	 	data-prezzo="${f.prezzo}"
			       	 	data-idfarmacia="${f.idFarmacia}"
			       	 	onclick="putInCart(this)">
						Aggiungi al carrello
					 </button>
				    </div>
				  </div>
		      </div>
		    </div>
		    <div class="col-md-2 bord-left">
		      <div class="card-body">
		      	<h2>
		      		<i class="bi bi-currency-euro"></i>${f.prezzo}
		      	</h2>
		      	<c:choose>
				    <c:when test="${f.quantita == 0}">
				        <span class="badge text-bg-danger">Terminate</span>
				    </c:when>
				
				    <c:when test="${f.quantita > 10}">
				        <span class="badge text-bg-success">Disponibile</span>
				    </c:when>
				
				    <c:when test="${f.quantita > 0 and f.quantita <= 10}">
				        <span class="badge text-bg-warning">Quasi terminate</span>
				    </c:when>
				
				    <c:otherwise>
				        <span class="badge text-bg-secondary">Non disponibile</span>
				    </c:otherwise>
				
				</c:choose>
		      </div>
		    </div>
		  </div>
		</div>
		</c:forEach>
	    </div>
	    <!-- inizio mappa -->
	    <div class="col-md-6">
	    	<div id="map" data-locations='[
	    		<c:forEach var="f" items="${lista}" varStatus="status">
	    		  {
	    		  	"id": ${f.idFarmacia},
			        "farmaciaNome": "<c:out value="${f.farmaciaNome}"/>",
			        "indirizzo": "<c:out value="${f.indirizzo}"/>",
			        "lat": ${f.lat},
			        "lon": ${f.lon}
			      }${!status.last ? "," : ""}
			    </c:forEach>
	    	]'></div>
	    </div>
	  </div>
	</section>
  </div>
</section>
<script>
	
	function incrementQuantit(a){
		let idFarmacia = a.getAttribute("data-idfarmacia").trim();
		let valore = document.getElementById("quantita-valore-" + idFarmacia); 
		let quantitaFarmaco = a.getAttribute('data-quantita');
		let quantitaAttuale = parseInt(valore.innerText);
		console.log(quantitaFarmaco);
		if(quantitaAttuale < quantitaFarmaco){
		    let nuovaQuantita = quantitaAttuale + 1;
		    valore.innerText= nuovaQuantita;
		}
	}
	function decrementQuantit(a){
		let idFarmacia = a.getAttribute("data-idfarmacia").trim();
		let element= document.getElementById('decrement-'+idFarmacia);
		let valore = document.getElementById("quantita-valore-" + idFarmacia); 
		let quantitaAttuale = parseInt(valore.innerText);
	    if (quantitaAttuale > 1) {
	    	let nuovaQuantita = quantitaAttuale - 1;
	    	valore.innerText= nuovaQuantita;
	    }
	}
</script>
<script src="${pageContext.request.contextPath}/js/cart.js"></script>
<script src="${pageContext.request.contextPath}/js/map.js"></script>
<script src="${pageContext.request.contextPath}/js/listfarmaci.js"></script>
</body>
</html>