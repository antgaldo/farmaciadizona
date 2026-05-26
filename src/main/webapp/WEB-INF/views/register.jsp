<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<h1>Registrati come cliente</h1>
<form action="${pageContext.request.contextPath}/register" method="POST">
	<fieldset>
		<input name="action" value="registerUser" type="hidden">
		<label>nome</label>
		<input id="nome" type="text" name="nome" placeholder="nome">
		<label>password</label>
		<input id="password" type="password" name="password" placeholder="password">
		<label>email</label>
		<input id="email" type="text" name="email" placeholder="email">
		<input type="submit" value="registerUser" />
	</fieldset>
</form>
<h1>Registrati come farmacia</h1>
<form action="${pageContext.request.contextPath}/register" method="POST">
	<fieldset>
		<input name="action" value="registerAdmin" type="hidden">
		<label>nome</label>
		<input id="nome" type="text" name="nome" placeholder="nome">
		<label>password</label>
		<input id="password" type="password" name="password" placeholder="password">
		<label>email</label>
		<input id="email" type="text" name="email" placeholder="email">
		<label>Nome Farmacia</label>
		<input id="nomeFarmacia" type="text" name="nomeFarmacia" placeholder="nome Farmacia">
		<label>Cap</label>
		<input id="cap" type="integer" name="cap" placeholder="cap">
		<input id="lat" type="hidden" name="lat">
		<input id="lon" type="hidden" name="lon">
		<label>Indirizzo</label>
		<input id="indirizzo" type="text" name="indirizzo" placeholder="Indirizzo" oninput="cercaIndirizzo()">
		<input type="submit" value="registerAdmin" />
	</fieldset>
</form>
<p>indirizzo: <div id="risultatoindirizzo"></div></p>
</body>
<script>
var timerRicerca;
function cercaIndirizzo(){
	clearTimeout(timerRicerca);
	var indirizzo= document.getElementById('indirizzo').value;
	if (indirizzo.length < 6) {
        document.getElementById("risultatoindirizzo").innerHTML = "";
        return;
    }
	timerRicerca= setTimeout(function(){
		var params= 'q=' + indirizzo;
		loadAjaxDoc('CercaIndirizzoJson',"GET",params,handleIndirizzo);
	},1000);
}

function loadAjaxDoc(url, method, params, cFunction){
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
    
    document.getElementById("risultatoindirizzo").innerHTML = response.map(place => {
        //Verifica dei campi che non siano null
        var address = place.address || {};
        var rawCity = address.city || address.town || address.village || "";
        var rawPostcode = address.postcode || "";
        var rawRoad = address.road || "";
        var rawLat = place.lat || "";
        var rawLon = place.lon || "";

        var city = rawCity.replace(/'/g, "\\'");
        var postcode = rawPostcode.replace(/'/g, "\\'");
        var road = rawRoad.replace(/'/g, "\\'");
        var lat = rawLat.replace(/'/g, "\\'");
        var lon = rawLon.replace(/'/g, "\\'");

        return "<button type='button' onclick=\"confermaIndirizzo('" 
               + postcode + "', '" 
               + city + "', '" 
               + road + "', '" 
               + lat + "', '" 
               + lon + "')\">" + 
               place.display_name + 
               "</button>";
    }).join("");
}

function confermaIndirizzo(postcode,city,road,lat,lon){
    var cap= document.getElementById("cap");
    if(cap!= null){
    	if(cap.value.trim() !== "" && cap.value.trim() !== postcode){
    		alert("Il cap non coincide");
    	} else {
    		cap.value = postcode;
    		document.getElementById("indirizzo").value = road + ", " + city + ", " + postcode;
    	}
    }
    document.getElementById("lat").value = lat;
    document.getElementById("lon").value = lon;
    document.getElementById("risultatoindirizzo").innerHTML = "";
}
</script>
</html>