<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<h1>Amministratore</h1>
<c:if test="${not empty error}">
    <p>${error}</p>
</c:if>
<h2>Cerca prodotto</h2>
<form action="/farmaciadizona/admin" method="POST">
	<input type="hidden" name="action" value="searchprodotto">
	<label>nome</label>
	<input id="nome" type="text" name="nome" placeholder="nome">
	<input type="submit" value="submit">
</form>
${prodottoTrovato.nome}
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