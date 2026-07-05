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
<section class="mt-120">
	<div class="container-fluid">
	<div class="col-md-12 d-flex align-items-center gap-3 my-5">
   		 <h3 class="mb-0 text-nowrap">Prodotto cercato</h3>
	    <form class="d-flex flex-grow-1" role="search" method="POST">
	        <div class="position-relative">
	            <input class="form-control me-2" id="farmaco" type="text" placeholder="Cerca farmaco" name="farmaco" aria-label="Farmaco" oninput="isExistFarmaco()"/>
	            <div id="autocompleteFarmaco" class="autocomplete-results position-absolute w-100 bg-white border rounded shadow-sm mt-1">
	                <div id="risultatoProdotto" class="autocomplete-item p-2">
	                    ${prodotto}
	                </div>
	            </div>
	        </div>
	        <button class="btn btn-outline-success ms-2" type="submit">Cerca</button>
	    </form>
	</div>
	<div class="col-md-12 prodotto">
		<div class="card my-5" >
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
	
	
	  <div class="row">
	  	<h3 class="mb-3 mt-3">Farmacie in zona</h3>
	    <div class="col-md-6">
	    <c:forEach var="f" items="${lista}">
	    
	    <div class="card mb-3 col">
		  <div class="row g-0">
		    <div class="col-md-10">
		      <div class="card-body">
		        <h5 class="card-title">${f.farmaciaNome}</h5>
		        <p class="card-text">${f.indirizzo}</p>
		        <p class="card-text" class="container text-center">
		          <div class="row justify-content-between">
				    <div class="col-auto me-auto">
				      Quantità : 
				     <button type="button" id="decrement" class="btn btn-outline-dark circle me-1" 
			       	 	onclick="decrementQuantit(this)">
						-
					 </button>
					 <span id="quantita-valore">1</span>
					 <button type="button" id="increment" class="btn btn-outline-dark circle ms-1"
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
		      	<h1>
		      		<i class="bi bi-currency-euro"></i>${f.prezzo}
		      	</h1>
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
	    <div class="col-md-6">
	    	<div id="map"></div>
	    </div>
	  </div>
	</div>
</section>
<script>
const listaMappa = [
    <c:forEach var="f" items="${lista}" varStatus="status">
      {
        farmaciaNome: "${f.farmaciaNome}",
        indirizzo: "${f.indirizzo}",
        lat: "${f.lat}",
        lon: "${f.lon}",
      }${!status.last ? ',' : ''}
    </c:forEach>
  ];
  const map = L.map('map').setView([listaMappa[0].lat, listaMappa[0].lon], 9.5)
  L.maplibreGL({
    style: 'https://tiles.openfreemap.org/styles/liberty',
  }).addTo(map);
  
  const markerGroup = L.featureGroup().addTo(map);
  var myIcon = L.divIcon({
	  html: '<i class="bi bi-geo-alt-fill text-success fs-2"></i>',
      className: 'bg-transparent', 
      iconSize: [30, 30],
      iconAnchor: [15, 30], 
      popupAnchor: [0, -30]
	});
  listaMappa.forEach(f => {
      L.marker([parseFloat(f.lat), parseFloat(f.lon)], {icon: myIcon}).addTo(map);
      const marker = L.marker([parseFloat(f.lat), parseFloat(f.lon)], {icon: myIcon})
      .bindPopup('<b>' + f.farmaciaNome + '</b><br>' + f.indirizzo);
	  marker.addTo(markerGroup);
   });
  if (listaMappa.length > 0) {
      map.fitBounds(markerGroup.getBounds(), {
          padding: [50, 50], 
          maxZoom: 15        
      });
  } else {
      map.setView([41.9028, 12.4964], 6); 
  }
</script>
<script>
	let element= document.getElementById("decrement");
	let valore= document.getElementById("quantita-valore");

	function incrementQuantit(a){
		let quantitaFarmaco = a.getAttribute("data-quantita");
		let quantitaAttuale = parseInt(valore.innerText);
		if(quantitaAttuale < quantitaFarmaco){
		    let nuovaQuantita = quantitaAttuale + 1;
		    valore.innerText= nuovaQuantita;
		}
	}
	function decrementQuantit(a){
		let quantitaAttuale = parseInt(valore.innerText);
	    if (quantitaAttuale > 1) {
	    	let nuovaQuantita = quantitaAttuale - 1;
	    	valore.innerText= nuovaQuantita;
	    }
	}
</script>
</body>
</html>