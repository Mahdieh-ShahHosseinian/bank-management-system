<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%><!DOCTYPE html>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<html>
<head>
<meta charset="ISO-8859-1">
<title>BMS Client Transfer</title>
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
	<h2 align="center">From account #${selected_account.account_id} Transfer to</h2>
	<hr>
	<a href="/client/get/${client.client_id}/account/${selected_account.account_id}">go back</a>
	<div align="center">

		<form:form
			action="http://localhost:2022/client/get/${client.client_id}/account/${selected_account.account_id}/transfer-accepted"
			method="GET">

			<label for="aci">Amount: </label>
			<input name="amount" id="aci" />
			
			<label for="dsa">Destination Account ID: </label>
			<input name="destAccount_id" id="dsa" />

			<input type="submit" value="Transfer">
		</form:form>

	</div>

</body>
</html>