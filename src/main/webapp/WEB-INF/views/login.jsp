<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<!DOCTYPE html>
<%@ include file="/WEB-INF/views/admin/header.jsp" %>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>

<div class="container text-center">
 <a class="navbar-brand" href="app">
 	<img class="width200" src="${pageContext.request.contextPath}/img/logo.png"/>
 </a>
  <div class="row">
   <div class="col-md-6 offset-md-3">
   	<form action="login" method="POST">
	  <div class="mb-3">
	  	<label  class="form-label">email</label>
		<input id="email" type="text" name="email" placeholder="email"  class="form-control">
	  </div>
	  <div class="mb-3">
	    <label class="form-label">Password</label>
	    <input type="password" class="form-control" name="password" placeholder="password">
	  </div>
	  <button type="submit" class="btn btn-primary" value="submit">Submit</button>
	</form>
   </div>
  </div>
</div>
</body>
</html>