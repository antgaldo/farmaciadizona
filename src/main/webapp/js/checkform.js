const searchfarmaco = document.getElementById('searchfarmaco');
const spedizionefarmaco= document.getElementById("form-2");

const registrazione= document.getElementById("formSpedizione");

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
		//controllo farmaco
		if(capInput.value.trim() === ""){
			event.preventDefault(); 
			farmacoInput.classList.add("validation");
			erroreFarmaco.classList.remove("displaynone");
		}
    });
	searchfarmaco.addEventListener('focus', function() {
        capInput.classList.remove("validation");
		erroreCap.classList.add("displaynone");
		farmacoInput.classList.remove("validation");
		erroreFarmaco.classList.add("displaynone");
    },true);
} 

//form checkout pagamento
if(spedizionefarmaco){
	
	const inputNome= document.querySelector('input[name="nome"]');
	const erroreNome = inputNome.parentElement.querySelector('.invalidinput');
	const inputCognome= document.querySelector('input[name="cognome"]');
	const erroreCognome = inputCognome.parentElement.querySelector('.invalidinput');
	const indirizzoInput = document.querySelector('input[name="indirizzo"]');
	const cittaInput = document.querySelector('input[name="citta"]');
	const capInput = document.querySelector('input[name="cap"]');
	const erroreIndirizzo = indirizzoInput.parentElement.querySelector('.invalidinput');
	const erroreCitta = cittaInput.parentElement.querySelector('.invalidinput');
	const erroreCap = capInput.parentElement.querySelector('.invalidinput');
	
	const inputCarta= document.querySelector('input[name="carta"]');
	const erroreCarta = inputCarta.parentElement.querySelector('.invalidinput');
	const inputScadenza= document.querySelector('input[name="scadenza"]');
	const erroreScadenza = inputScadenza.parentElement.querySelector('.invalidinput');
	spedizionefarmaco.addEventListener('submit', function(event){
		if(inputNome.value.trim()===""){
			event.preventDefault();
			inputNome.classList.add("validation");
			erroreNome.classList.remove("displaynone");
		}
		if(inputCognome.value.trim()===""){
			event.preventDefault();
			inputCognome.classList.add("validation");
			erroreCognome.classList.remove("displaynone");
		}
		if(indirizzoInput.value.trim()===""){
			event.preventDefault();
			indirizzoInput.classList.add("validation");
			erroreIndirizzo.classList.remove("displaynone");
		}
		if(capInput.value.trim()===""){
			event.preventDefault();
			capInput.classList.add("validation");
			erroreCap.classList.remove("displaynone");
		}
		if(cittaInput.value.trim()===""){
			event.preventDefault();
			cittaInput.classList.add("validation");
			erroreCitta.classList.remove("displaynone");
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
		cittaInput.classList.remove("validation");
		erroreCitta.classList.add("displaynone");
		indirizzoInput.classList.remove("validation");
		erroreIndirizzo.classList.add("displaynone");
		inputCarta.classList.remove("validation");
		erroreCarta.classList.add("displaynone");
		inputScadenza.classList.remove("validation");
		erroreScadenza.classList.add("displaynone");
	},true);
}