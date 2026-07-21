const searchfarmaco = document.getElementById('searchfarmaco');
const spedizionefarmaco= document.getElementById("form-2");

//form di ricerca in home
if (searchfarmaco) {
	const capInput = document.querySelector('input[name="cap"]');
	const farmacoInput = document.querySelector('input[name="farmaco"]');
	const erroreCap = capInput.parentElement.querySelector('.invalidinput');
	const erroreFarmaco = farmacoInput.parentElement.querySelector('.invalidinput');
    searchfarmaco.addEventListener('submit', function(event) {
        
		//controllo cap
        if (capInput) {
            const capValue = capInput.value.trim();
            if (capValue.length !== 5 || isNaN(capValue)) {
                event.preventDefault(); 
                capInput.classList.add("validation");
				erroreCap.classList.remove("displaynone");
            }
        }
	searchfarmaco.addEventListener('focus', function() {
        capInput.classList.remove("validation");
		erroreCap.classList.add("displaynone");
		},true);
	});
}
//form checkout pagamento
if(spedizionefarmaco){
	
	const capInput = document.querySelector('input[name="cap"]');
	const erroreCap = capInput.parentElement.querySelector('.invalidinput');
	
	const inputCarta= document.querySelector('input[name="carta"]');
	var erroreCarta = null;
	if (inputCarta) {
	    if (inputCarta.parentElement) {
	        erroreCarta = inputCarta.parentElement.querySelector('.invalidinput');
	    }
	}
	const inputScadenza= document.querySelector('input[name="scadenza"]');
	var erroreScadenza = null;
	if (inputScadenza) {
	    if (inputScadenza.parentElement) {
	        erroreScadenza = inputScadenza.parentElement.querySelector('.invalidinput');
	    }
	}
	spedizionefarmaco.addEventListener('submit', function(event){

		if(capInput.value.trim()===""){
			event.preventDefault();
			capInput.classList.add("validation");
			erroreCap.classList.remove("displaynone");
		}
		if(inputCarta.value.trim().length != 16){
			event.preventDefault();
			inputCarta.classList.add("validation");
			erroreCarta.classList.remove("displaynone");
		}

		if(inputScadenza.value.trim().length != 5){
			event.preventDefault();
			inputScadenza.classList.add("validation");
			erroreScadenza.classList.remove("displaynone");
		}
	})
	spedizionefarmaco.addEventListener('focus', function(){
		capInput.classList.remove("validation");
		erroreCap.classList.add("displaynone");
		inputCarta.classList.remove("validation");
		erroreCarta.classList.add("displaynone");
		inputScadenza.classList.remove("validation");
		erroreScadenza.classList.add("displaynone");
	},true);
}