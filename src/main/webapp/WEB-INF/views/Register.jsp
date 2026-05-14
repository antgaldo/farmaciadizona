<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<h1>Registrati come cliente</h1>
<form action="${pageContext.request.contextPath}/register" method="POST">
	<fieldset>
		<input name="action" value="registerUser" type="hidden">
		<label>nome</label>
		<input id="nome" type="text" name="nome" placeholder="nome">
		<label>password</label>
		<input id="password" type="password" name="password" placeholder="password">
		<label>email</label>
		<input id="email" type="text" name="email" placeholder="email">
		<input type="submit" value="registerUser" />
	</fieldset>
</form>
<h1>Registrati come farmacia</h1>
<form action="${pageContext.request.contextPath}/register" method="POST">
	<fieldset>
		<input name="action" value="registerAdmin" type="hidden">
		<label>nome</label>
		<input id="nome" type="text" name="nome" placeholder="nome">
		<label>password</label>
		<input id="password" type="password" name="password" placeholder="password">
		<label>email</label>
		<input id="email" type="text" name="email" placeholder="email">
		<label>Nome Farmacia</label>
		<input id="nomeFarmacia" type="text" name="nomeFarmacia" placeholder="nome Farmacia">
		<label>Cap</label>
		<input id="cap" type="integer" name="cap" placeholder="cap">
		<input type="submit" value="registerAdmin" />
	</fieldset>
</form>
</body>
</html>