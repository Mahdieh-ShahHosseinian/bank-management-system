<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%><!DOCTYPE html>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<html>
<head>
<meta charset="ISO-8859-1">
<title>BMS Branch Employees</title>
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

	<form:form action="register-employee" method="POST">
		<div align="center">
			<label for="efn">Employee First Name: </label> <input
				name="firstname" id="efn" /> <br /> <br />
				<label for="eln">Employee Last Name: </label> <input
				name="lastname" id="eln" /> <br /> <br />
				<label for="pn">Phone number: </label> <input
				name="phone" id="pn" /> <br /> <br />
				<!--<label for="dob">Date of Birth: </label> <input
				name="DOB" id="dob" /> <br /> <br />  -->
				<label for="sa">Salary: </label> <input
				name="salary" id="sa" /> <br /> <br />
				<label for="cy">Address City: </label> <input
				name="city" id="cy" />
				<label for="st">street: </label> <input
				name="street" id="st" />
				<label for="pin">Pin: </label> <input
				name="pin" id="pin" /> <br /> <br />
				<input type="submit" value="Register">
		</div>
	</form:form>

	<h3 align="center">${branch.branch_name} Employees List</h3>
	<table align="center" border="1" cellpadding="5" cellspacing="1">
		<tr>
			<th>Name</th>
			<th>Phone</th>
			<!--<th>Date of Birth</th>-->
			<th>Salary</th>
			<th>Address</th>
		</tr>
		<c:forEach var="employee" items="${branch.employees}">
			<tr>
				<td>${employee.firstname} 
					 ${employee.lastname}</td>
				<td>${employee.phone}</td>
				<!--<td>${employee.DOB}</td>-->
				<td>${employee.salary}</td>
				<td>${employee.city} ${employee.street} ${employee.pin}</td>
				<td><a
					href="/branch/get/${branch.branch_id}/employees/${employee.employee_id}/set-manager">set as manager</a></td>
			</tr>
		</c:forEach>
	</table>

</body>
</html>