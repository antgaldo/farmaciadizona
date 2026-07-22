<nav class="navbar">
  <div class="container flex">
    <a href="app"><img class="width200" src="${pageContext.request.contextPath}/img/logo.png"/></a>
	    <% if (session.getAttribute("userid") != null) { %>
	    	<div class="ml-auto me-2"><%= session.getAttribute("usernome") %></div>
	    	<div class="me-2"><a class="registrati" href="${pageContext.request.contextPath}/logout">- logout</a></div>
	    <%}%>
    <button class="menu" type="button"
	        data-bs-toggle="offcanvas"
	        data-bs-target="#offcanvasNavbar"
	        aria-controls="offcanvasNavbar"
	        aria-label="Toggle navigation">
	    <span class="menu-icon"></span>
	    <span id="navbarToggle" class="navbar-badge"></span>
	</button>
    <div class="offcanvas offcanvas-end" tabindex="-1" id="offcanvasNavbar" aria-labelledby="offcanvasNavbarLabel">
      <div class="offcanvas-header">
        <h5 class="offcanvas-title" id="offcanvasNavbarLabel">Menu</h5>
        <button type="button" class="btn-close" data-bs-dismiss="offcanvas" aria-label="Close"></button>
      </div>
      <div class="offcanvas-body">
        <ul class="menu-panel">
		<% if (session.getAttribute("userid") == null) { %>
          <li class="nav-item">
            <a class="nav-link active" aria-current="page" href="login">
      	      <i class="bi bi-person"></i>
       		  Account
             </a>
          </li>
        <% } %>
        <% if (session.getAttribute("userid") != null) { %>
          <li class="nav-item">
            <a class="nav-link active" aria-current="page" href="order">
      	      <i class="bi bi-person"></i>
       		  Ordini
             </a>
          </li>
        <% } %>
          <li class="nav-item">
            <a class="nav-link active">
              <i class="bi bi-basket"></i> Carrello
            </a>
            <ul class="dropdown-menu">
              <div id="showCart" ></div>
              	<form id="cartForm" action="checkout" method="POST">
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