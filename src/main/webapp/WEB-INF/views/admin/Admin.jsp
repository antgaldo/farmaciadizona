<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<h1>Amministratore</h1>
<c:forEach var="p" items="${prodotti}">
    ${p.nomeProdotto}
    ${p.descrizione}
    ${p.prezzo}
    ${p.quantita}
    <br>
</c:forEach>
<h2>Inserisci prodotto</h2>
<form action="/farmaciadizona/admin" method="POST">
	<input type="hidden" name="action" value="addprodotto">
	<label>nome</label>
	<input id="nome" type="text" name="nome" placeholder="nome">
	<label>descrizione</label>
	<input id="descrizione" type="text" name="descrizione" placeholder="descrizione">
	<label>prezzo</label>
	<input id="prezzo" type="int" name="prezzo" placeholder="prezzo">
		<label>quantita</label>
	<input id="quantita" type="int" name="quantita" placeholder="quantita">
	<input type="submit" value="submit">
</form>
</body>
</html>