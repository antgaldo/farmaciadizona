document.querySelectorAll('input[name="choise"]').forEach((radio) => {
  radio.addEventListener('change', function() {
    if (this.checked) {
    	if((this.value=="form-1")&& document.getElementById("form-1")){
    		document.getElementById("form-1").classList.remove("displaynone");
    		document.getElementById("form-2").classList.add("displaynone");
    	}
    	if((this.value=="form-2")&& document.getElementById("form-2")){
    		document.getElementById("form-2").classList.remove("displaynone");
    		document.getElementById("form-1").classList.add("displaynone");
    	}
    }
  });
});