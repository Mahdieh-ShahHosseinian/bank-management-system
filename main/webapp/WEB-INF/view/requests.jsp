<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%><!DOCTYPE html>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<html>
<head>
<meta charset="ISO-8859-1">
<title>BMS Client Requests</title>
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
	<h2 align="center">Dear ${employee.firstname} ${employee.lastname}
		check the client requests</h2>
	<hr>
	<a href="/">go back</a>
	<div align="center">

		<h3 align="center">Request List</h3>

		<table align="center" border="1" cellpadding="5" cellspacing="1">
			<tr>
				<th>Client ID</th>
				<th>Account ID</th>
				<th>Request Type</th>
			</tr>
			<c:forEach var="request" items="${requests}">
				<tr>
					<td>${request.client_id}</td>
					<td>${request.account_id}</td>
					<td>${request.type}</td>
					<td><a
						href="http://localhost:2022/employee/get/${employee.employee_id}/request/${request.request_id}/accept_request">Accept</a></td>
					<td><a
						href="http://localhost:2022/employee/get/${employee.employee_id}/request/${request.request_id}/decline_request">Decline</a></td>
				</tr>
			</c:forEach>
		</table>
	</div>

</body>
</html>