<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%><!DOCTYPE html>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<html>
<head>
<meta charset="ISO-8859-1">
<title>BMS Client Logged in</title>
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
	<h2 align="center">Dear ${client.firstname} ${client.lastname} select an account to cont.</h2>
	<hr>
	<a href="/">go home</a>
	<div align="center">

		<h3 align="center">Account List</h3>

		<table align="center" border="1" cellpadding="5" cellspacing="1">
			<tr>
				<th>ID</th>
				<th>Balance</th>
				<th>Opened</th>
			</tr>
			<c:forEach var="account" items="${client.accounts}">
				<tr>
					<td>${account.account_id}</td>
					<td>${account.balance}</td>
					<td>${account.open_date}</td>
					<td><a
						href="/client/get/${client.client_id}/account/${account.account_id}">Select</a></td>
				</tr>
			</c:forEach>
		</table>
		<br>
		<form:form action="http://localhost:2022/client/get/${client.client_id}/add_account" method="POST">

			<input type="submit" value="Request new account">
		</form:form>
		<br>
	</div>

</body>
</html>