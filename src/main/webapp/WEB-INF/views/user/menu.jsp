
<nav class="navbar bg-body-tertiary fixed-top">
  <div class="container-fluid">
    <a class="navbar-brand" href="#"><img class="width200" src="${pageContext.request.contextPath}/img/logo.png"/></a>
    <button class="navbar-toggler position-relative" type="button" data-bs-toggle="offcanvas" data-bs-target="#offcanvasNavbar" aria-controls="offcanvasNavbar" aria-label="Toggle navigation">
      <span class="navbar-toggler-icon"></span>
        <span class="position-absolute top-0 start-100 translate-middle badge rounded-pill bg-danger d-none" id="navbarToggle">
	    	
	   		<span class="visually-hidden">unread messages</span>
	 	 </span>
    </button>
    <div class="offcanvas offcanvas-end" tabindex="-1" id="offcanvasNavbar" aria-labelledby="offcanvasNavbarLabel">
      <div class="offcanvas-header">
        <h5 class="offcanvas-title" id="offcanvasNavbarLabel">Menu</h5>
        <button type="button" class="btn-close" data-bs-dismiss="offcanvas" aria-label="Close"></button>
      </div>
      <div class="offcanvas-body">
        <ul class="navbar-nav justify-content-end flex-grow-1 pe-3">
          <li class="nav-item">
            <a class="nav-link active" aria-current="page" href="#">
            <i class="bi bi-person"></i>
             Account</a>
          </li>
          <li class="nav-item dropdown">
            <a class="nav-link dropdown">
              <i class="bi bi-basket"></i> Carrello
            </a>
            <ul class="dropdown-menu">
              <div id="showCart" class="autocomplete-item p-2"></div>
              	<form id="cartForm" action="checkout" method="POST">
				    <input type="hidden" name="datiCart" id="datiCart">
                    <button type="submit" class="btn btn-outline-success">
						Checkout
					</button>
				</form>
            </ul>
          </li>
        </ul>
      </div>
    </div>
  </div>
</nav>