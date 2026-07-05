//al refresh pagina restituisci il carrello (il controllo se esiste o meno è spostato nella servlet)
window.onload= function(){
	var request = new XMLHttpRequest();
	request.open("GET", "addcartservlet",true);
	request.onload = function() {
	       if (request.status >= 200 && request.status < 400) {
	           handleCart(request); 
	       } else {
	           console.error("Errore del server:", request.status);
	       }
	   };

	request.send(null);
}


//funzione per l'inserimento dei prodotti 
function putInCart(a){
	let idProdotto = a.getAttribute("data-idprodotto");
	let nomeFarmaco = a.getAttribute("data-nome");
	let quantitaFarmaco= parseInt(document.getElementById("quantita-valore").innerText);
	let prezzoFarmaco = a.getAttribute("data-prezzo");
	let idFarmacia = a.getAttribute("data-idfarmacia");
	
	let farmaco = {
	    idProdotto: idProdotto,
	    nome: nomeFarmaco,
	    quantita: quantitaFarmaco,
	    prezzo: prezzoFarmaco,
	    idFarmacia: idFarmacia,
	};
	AjaxFarmaco('addcartservlet',"POST",farmaco,handleCart);
}

//funzione per la chiamata ajax 
function AjaxFarmaco(url,method,farmaco,hFunction){
	var request = new XMLHttpRequest();
	if(request){
		request.onreadystatechange = function(){
			if(this.readyState==4){
				if(this.status==200){
					hFunction(this);
				} else {
					alert("Il server ha risposto con errore: " + this.status);
				}
			}
		}
		setTimeout(function(){
			if(request.readyState < 4){
				request.abort();
			}
		},15000);
		if(method.toLowerCase()=="post"){
			request.open("POST", url,true);
		    request.setRequestHeader("Content-Type", "application/x-www-form-urlencoded");
		    //Impacchettiamo i dati nel formato chiave=valore
		    let data = "idProdotto=" + encodeURIComponent(farmaco.idProdotto) + 
	           "&nome=" + encodeURIComponent(farmaco.nome) + 
	           "&quantita=" + encodeURIComponent(farmaco.quantita) + 
	           "&idFarmacia=" + encodeURIComponent(farmaco.idFarmacia) + 
	           "&prezzo=" + encodeURIComponent(farmaco.prezzo);
		    request.send(data);
		}
		if(method.toLowerCase()=="get"){
			request.open("GET", url +"?" + "idProdotto=" + encodeURIComponent(farmaco) ,true);
			//request.setRequestHeader("Connection","close");
			request.send(null);
		}
	}
}

//funzione per cancellare il farmaco dal carrello
function deleteFarmaco(idProdotto) {
    AjaxFarmaco('removefromcartservlet',"GET",idProdotto,handleCart);
}
function handleCart(request){
    let response = JSON.parse(request.responseText);
    let totcart= response.length;
	let contextPath = window.location.pathname.substring(0, window.location.pathname.indexOf('/', 2));
	document.getElementById('showCart').innerHTML = response.map(farmaco => `
	    <div class="row p-2">
	        <div class="col">
	            <img class="img-thumbnail" src="${contextPath}/uploads/${farmaco.path}" alt="${farmaco.nome}">
	        </div>
	        <div class="col text-capitalize text-center">
	            ${farmaco.nome}
	        </div>
	        <div class="col text-center">
	            ${farmaco.quantita}
	        </div>
	        <div class="col text-center">
	            <i class="bi bi-trash3" onclick="deleteFarmaco(${farmaco.idProdotto})"></i>
	        </div>
	    </div>
	`).join('');
	
    let elemento = document.getElementById("navbarToggle");
    if(totcart==0) {
		document.getElementsByClassName("dropdown-menu")[0].classList.remove("show");
        elemento.classList.add("d-none");
    } else {
		elemento.classList.remove("d-none");
		elemento.innerHTML = "<span>" + totcart + "</span>";
		document.getElementsByClassName("dropdown-menu")[0].classList.add("show");
    }
}