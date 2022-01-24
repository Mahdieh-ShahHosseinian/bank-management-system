<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>BMS Home Page</title>
</head>
<style type="text/css">
h2 {
	color: #3377FF;
}
</style>
<body>
	<h2 align="center">Welcome to Bank Management System Demo</h2>
	<hr>
	<form:form action="branch/getAll" method="GET">

		<input type="submit" value="Login Administrator">
	</form:form>
	<br>

	<form:form action="employee/get" method="GET">

		<input type="submit" value="Login Employee">
	</form:form>

	<br>

	<form:form action="client/get" method="GET">

		<input type="submit" value="Login Client">
	</form:form>
</body>
</html>