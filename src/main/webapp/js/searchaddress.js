
var timerRicerca;
function cercaIndirizzo(){
	clearTimeout(timerRicerca);
	var indirizzo= document.getElementById('indirizzo').value;
	var cap= document.getElementById('cap').value;
	if (indirizzo.length < 6) {
        document.getElementById("risultatoindirizzo").innerHTML = "";
        return;
    }
	timerRicerca= setTimeout(function(){
		var params = 'indirizzo=' + encodeURIComponent(indirizzo) + '&cap=' + encodeURIComponent(cap);
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

	   return "<button type='button' " +
	          "class='list-group-item list-group-item-action' " +
	          "onclick=\"confermaIndirizzo('"
				  + postcode + "', '" 
				  + city + "', '" 
				  + road + "', '" 
				  + lat + "', '" 
				  + lon + "')\">" + 
	          place.display_name +
	          "</button>";
    }).join("");
	document.getElementById("autocompleteIndirizzo").classList.add("displayblock");
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