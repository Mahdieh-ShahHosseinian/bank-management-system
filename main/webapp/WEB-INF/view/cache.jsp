<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%><!DOCTYPE html>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<html>
<head>
<meta charset="ISO-8859-1">
<title>BMS Cache</title>
<style>
table {
	border-collapse: inherit;
}

th {
	background-color: #4287f5;
	color: white;
}

th, td {
	width: 150px;
	text-align: center;
	padding: 5px;
}

h2 {
	color: #3377FF;
}
</style>
</head>
<body>
	<h2 align="center">Employee ${employee.firstname} ${employee.lastname}</h2>
	<hr>
	<a href="/">go back</a>
	<div align="center">

		<form:form
			action="http://localhost:2022/employee/get/${employee.employee_id}/cache/selected_account"
			method="POST">

			<label for="aci">Account ID: </label>
			<input name="account_id" id="aci" />

			<input type="submit" value="Cache">
		</form:form>

	</div>

</body>
</html>