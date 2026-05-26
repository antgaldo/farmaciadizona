<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<!DOCTYPE html>
<%@ include file="/WEB-INF/views/user/header.jsp" %>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>

	<%@ include file="/WEB-INF/views/user/menu.jsp" %>
	<section class="hero">
	<div class="card custom-card">
	  <div class="card-body">
	    <h5 class="card-title">Se hai passato un guaio, sei nel posto giusto !</h5>
	    <p class="card-text">Inserisci il tuo cap ed il farmaco richiesto. Ti indirizzeremo alle farmacie di zona che ne hanno disponibilità</p>
	    <form class="d-flex mt-3" role="search" method="POST">
          <input class="form-control me-2" type="text" placeholder="Cap" name="cap" aria-label="Cap"/>
          <input class="form-control me-2" type="text" placeholder="Farmaco" name="farmaco" aria-label="Farmaco"/>
          <button class="btn btn-outline-success" type="submit">Cerca</button>
        </form>
	  </div>
	</div>
	</section>
</body>
</html>