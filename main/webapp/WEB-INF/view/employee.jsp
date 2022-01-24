<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%><!DOCTYPE html>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<html>
<head>
<meta charset="ISO-8859-1">
<title>BMS Employee Logged in</title>
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
	<h2 align="center">Hi ${employee.firstname} ${employee.lastname}</h2>
	<hr>
	<a href="/">go home</a>
	<div align="center">
	
		<form:form action="http://localhost:2022/employee/get/${employee.employee_id}/register-client" method="GET">
			<div align="center">
				<label for="efn">Client First Name: </label> <input
					name="firstname" id="efn" /> <br /> <br />
					<label for="eln">Client Last Name: </label> <input
					name="lastname" id="eln" /> <br /> <br />
					<label for="pn">Phone number: </label> <input
					name="phone" id="pn" /> <br /> <br />
					<!--<label for="dob">Date of Birth: </label> <input
					name="DOB" id="dob" /> <br /> <br />  -->
					<label for="cy">Address City: </label> <input
					name="city" id="cy" />
					<label for="st">street: </label> <input
					name="street" id="st" />
					<label for="pin">Pin: </label> <input
					name="pin" id="pin" /> <br /> <br />
					<input type="submit" value="Register">
			</div>
		</form:form>

		<br></br>

		<form:form
			action="http://localhost:2022/employee/get/${employee.employee_id}/requests"
			method="GET">

			<input type="submit" value="Requests">
		</form:form>

		<br></br>

		<form:form
			action="http://localhost:2022/employee/get/${employee.employee_id}/cache"
			method="GET">

			<input type="submit" value="Cache">
		</form:form>

	</div>

</body>
</html>