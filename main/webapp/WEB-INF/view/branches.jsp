<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%><!DOCTYPE html>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<html>
<head>
<meta charset="ISO-8859-1">
<title>BMS Branches</title>
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
	<a href="/">go home</a>

	<form:form action="branch/register" method="POST">
		<div align="center">
			<label for="brn">Branch Name: </label> <input name="branch_name"
				id="brn" /> <br /> <br /> <label for="brl">Location: </label> <input
				name="location" id="brl" /> <br /> <br /> <label for="mngr">

<!--			branch Manager: </label> <select name="employee">
				<c:forEach var="employee" items="${employees}">
					<option value="${employee.employee_id}"
						label="${employee.lastname}" />
				</c:forEach>  
			</select> <br /> <br /> --> <input type="submit" value="Register">
		</div>
	</form:form>

	<h3 align="center">Branch List</h3>
	<table align="center" border="1" cellpadding="5" cellspacing="1">
		<tr>
			<th>Name</th>
			<th>Location</th>
			<th>Manager</th>
		</tr>
		<c:forEach var="branch" items="${branches}">
			<tr>
				<td>${branch.branch_name}</td>
				<td>${branch.location}</td>
				<td>${branch.employee.firstname} ${branch.employee.lastname}</td>
				<td><a
					href="/branch/get/${branch.branch_id}/employees">show employees</a></td>
				<td><a
					href="/branch/get/${branch.branch_id}/clients">show clients</a></td>
			</tr>
		</c:forEach>
	</table>

</body>
</html>