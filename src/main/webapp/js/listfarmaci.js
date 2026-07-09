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
