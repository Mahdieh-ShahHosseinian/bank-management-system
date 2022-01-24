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
	<h2 align="center">${client.firstname} ${client.lastname} -account
		ID: #${selected_account.account_id}</h2>
	<hr>
	<a href="/">go back</a>

	<h2 align="center">Balance: ${selected_account.balance}</h2>

	<div align="center">
		<form:form action="http://localhost:2022/client/get/${client.client_id}/account/${selected_account.account_id}/account_history" method="GET">

			<input type="submit" value="history">
		</form:form>
		<br></br>
		<form:form action="http://localhost:2022/client/get/${client.client_id}/account/${selected_account.account_id}/transfer" method="GET">

			<input type="submit" value="transfer">
		</form:form>
		<br></br>
		<h3 align="center">Loan List</h3>
		<table align="center" border="1" cellpadding="5" cellspacing="1">
			<tr>
				<th>Interest</th>
				<th>Duration</th>
				<th>Total Amount</th>
				<th>Remaining Amount</th>
			</tr>
			<c:forEach var="loan" items="${selected_account.loans}">
				<tr>
					<td>${loan.interest}</td>
					<td>${loan.duration}</td>
					<td>${loan.total_amount}</td>
					<td>${loan.remaining_amount}</td>
				</tr>
			</c:forEach>
		</table>
		<br></br>
		<form:form
			action="http://localhost:2022/client/get/${client.client_id}/account/${selected_account.account_id}/add_loan"
			method="POST">

			<input type="submit" value="Request loan">
		</form:form>
	</div>

</body>
</html>