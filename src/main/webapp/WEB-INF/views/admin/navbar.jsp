<nav class="navbar bg-body-tertiary fixed-top">
  <div class="container-fluid">
    <a class="navbar-brand" ><img class="width200" src="${pageContext.request.contextPath}/img/logo.png"/></a>
    <% if (session.getAttribute("userid") != null) { %>
    	<div class="me-2">
    		<%= session.getAttribute("usernome") %>
    		(<%= session.getAttribute("nomeFarmacia") %>)
    		<a href="${pageContext.request.contextPath}/logout">logout</a>
    	</div>
    <%}%>
  </div>
</nav>