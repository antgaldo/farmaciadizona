window.onload = function() {
    let xhr = new XMLHttpRequest();
    xhr.open("GET","cartservlet",true);
    xhr.onload = function() {
        if(xhr.status === 200){
            handleCart(xhr);
        }
    };
    xhr.send();
};