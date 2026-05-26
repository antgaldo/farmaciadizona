<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<!DOCTYPE html>
<%@ include file="/WEB-INF/views/user/header.jsp" %>
<link rel="stylesheet" href="https://unpkg.com/leaflet@1.9.4/dist/leaflet.css"
integrity="sha256-p4NxAoJBhIIN+hmNHrzRCf9tD/miZyoHS5obTRR9BMY="
crossorigin=""/>
 <script src="https://unpkg.com/leaflet@1.9.4/dist/leaflet.js"
     integrity="sha256-20nQCchB9co0qIjJZRGuk2/Z9VM+kNiyxNV1lvTlZBo="
     crossorigin=""></script>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<%@ include file="/WEB-INF/views/user/menu.jsp" %>
<section class="mt-150">
	<div class="container-fluid">
	  <div class="row">
	  	<h1>Farmacie</h1>
	    <div class="col-md-6">
	    <c:forEach var="f" items="${lista}">
	       <div class="card mb-3">
			  <div class="card-body">
			    <h5 class="card-title">${f.farmaciaNome}</h5>
			    <p class="card-text">${f.prodottoNome}</p>
			    <a href="#" class="btn btn-primary">Button</a>
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
	var map = L.map('map').setView([51.505, -0.09], 13);
	L.tileLayer('https://tile.openstreetmap.org/{z}/{x}/{y}.png', {
	    maxZoom: 19,
	    attribution: '&copy; <a href="http://www.openstreetmap.org/copyright">OpenStreetMap</a>'
	}).addTo(map);
</script>
</body>
</html>