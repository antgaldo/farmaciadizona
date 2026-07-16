const searchfarmaco = document.getElementById('searchfarmaco');


//form di ricerca in home
if (searchfarmaco) {
	const capInput = document.querySelector('input[name="cap"]');
	const farmacoInput = document.querySelector('input[name="farmaco"]');
	const erroreCap = capInput.parentElement.querySelector('.invalidinput');
	const erroreFarmaco = farmacoInput.parentElement.querySelector('.invalidinput');
    searchfarmaco.addEventListener('submit', function(event) {
        
        if (capInput) {
            const capValue = capInput.value.trim();
            if (capValue.length !== 5 || isNaN(capValue)) {
                event.preventDefault(); 
                capInput.classList.add("validation");
				erroreCap.classList.remove("displaynone");
            }
        }
		if(capInput.value.trim() === ""){
			event.preventDefault(); 
			farmacoInput.classList.add("validation");
			erroreFarmaco.classList.remove("displaynone");
		}
    });
	capInput.addEventListener('focus', function() {
        capInput.classList.remove("validation");
		erroreCap.classList.add("displaynone");
    });
	farmacoInput.addEventListener('focus', function() {
	    farmacoInput.classList.remove("validation");
		erroreFarmaco.classList.add("displaynone");
	});
} 