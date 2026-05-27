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
	    <form class="d-flex mt-3" role="search" method="POST">
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
</body>
<script>
	function isExistFarmaco(){
		var farmaco= "q=" + document.getElementById("farmaco").value;
		loadAjaxDoc('CercaFarmacoJson',"GET",farmaco,handleIndirizzo);
	}
	
	function loadAjaxDoc(url,method,params,cFunction){
		var request = new XMLHttpRequest();	
		if(request){
			request.onreadystatechange= function(){
				if(this.readyState==4){
					if(this.status==200){
						cFunction(this);
					} else {
						if(this.status==0){
							alert("problemi");
						}
						return null;
					}
				}
			}
			setTimeout(function(){
				if(request.readyState < 4){
					request.abort();
				} else {
					
				}
			},15000);
			if(method.toLowerCase()=="get"){
				if(params){
					request.open("GET",url + "?" + params,true);
				} else {
					request.open("GET",url,true);
				}
				//request.setRequestHeader("Connection","close");
				request.send(null);
			}
		}
	}
	
	function handleIndirizzo(request){
	    var response = JSON.parse(request.responseText);
	    document.getElementById("risultatoProdotto").innerHTML =
	        response.map(function(place){
	            var nomeprodotto = place.replace(/'/g, "\\'");
	            return "<button type='button' " +
	                   "class='list-group-item list-group-item-action' " +
	                   "onclick=\"confermaProdotto('" + nomeprodotto + "')\">" +
	                   place +
	                   "</button>";

	        }).join("");
	    document.getElementById("autocompleteFarmaco").classList.add("displayblock");
	}
	function confermaProdotto(nomeprodotto){
	    var input = document.getElementById("farmaco");
	    input.value = nomeprodotto;
	    document.getElementById("risultatoProdotto").innerHTML = "";
	    document.getElementById("autocompleteFarmaco").classList.remove("displayblock");
	    input.focus();
	}
	
</script>
</html>