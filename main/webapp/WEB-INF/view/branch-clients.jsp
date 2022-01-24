<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%><!DOCTYPE html>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<html>
<head>
<meta charset="ISO-8859-1">
<title>BMS Branch Clients</title>
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
	<h2 align="center">Branches</h2>
	<hr>
	<a href="/">go back</a>

	<h3 align="center">${branch.branch_name} Client List</h3>
	<table align="center" border="1" cellpadding="5" cellspacing="1">
		<tr>
			<th>Name</th>
			<th>Phone</th>
			<!--<th>Date of Birth</th>-->
			<th>Address</th>
		</tr>
		<c:forEach var="client" items="${branch.clients}">
			<tr>
				<td>${client.firstname} 
					 ${client.lastname}</td>
				<td>${client.phone}</td>
				<!--<td>${client.DOB}</td>-->
				<td>${client.city} ${client.street} ${client.pin}</td>
			</tr>
		</c:forEach>
	</table>

</body>
</html>