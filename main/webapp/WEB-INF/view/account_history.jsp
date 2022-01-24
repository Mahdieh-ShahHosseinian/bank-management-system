<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%><!DOCTYPE html>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<html>
<head>
<meta charset="ISO-8859-1">
<title>BMS Client History</title>
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
	<h2 align="center">${client.firstname} ${client.lastname} Account #${selected_account.account_id} history</h2>
	<hr>
	<a href="/client/get/${client.client_id}/account/${selected_account.account_id}">go back</a>
	<div align="center">

		<h3 align="center">Transaction List</h3>

		<table align="center" border="1" cellpadding="5" cellspacing="1">
			<tr>
				<th>ID</th>
				<th>Date</th>
				<th>Type</th>
				<th>Amount</th>
			</tr>
			<c:forEach var="transaction" items="${transactions}">
				<tr>
					<td>${transaction.transaction_id}</td>
					<td>${transaction.date}</td>
					<td>${transaction.type}</td>
					<td>${transaction.amount}</td>
				</tr>
			</c:forEach>
		</table>
	</div>

</body>
</html>