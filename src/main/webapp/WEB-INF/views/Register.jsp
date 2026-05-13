<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<form action="register" method="POST">
	<fieldset>
		<label>nome</label>
		<input id="nome" type="text" name="nome" placeholder="nome">
		<label>password</label>
		<input id="password" type="password" name="password" placeholder="password">
		<label>tipo di utente</label>
		<select id="ruolo" name="ruolo">
		  <option value="ADMIN">Farmacia</option>
		  <option value="USER">Utente</option>
		</select>
		<input type="submit" value="register" />
	</fieldset>
</form>
</body>
</html>