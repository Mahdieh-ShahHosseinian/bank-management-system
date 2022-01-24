<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%><!DOCTYPE html>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<html>
<head>
<meta charset="ISO-8859-1">
<title>BMS Cache Selected Account</title>
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
	<h2 align="center">${employee.firstname} ${employee.lastname} do the cache for the selected account: #${account.account_id}</h2>
	<hr>
	<a href="/employee/get/${employee.employee_id}/cache">go back</a>
	<div align="center">

		<form:form
			action="http://localhost:2022/employee/get/${employee.employee_id}/cache/selected_account/${account.account_id}/deposit"
			method="GET">

			<label for="dep">Deposit: </label>
			<input name="deposit_cache" id="dep" />

			<input type="submit" value="Enter">
		</form:form>
		<br></br>
		<form:form
			action="http://localhost:2022/employee/get/${employee.employee_id}/cache/selected_account/${account.account_id}/withdraw"
			method="GET">

			<label for="wit">Withdraw: </label>
			<input name="withdraw_cache" id="wit" />

			<input type="submit" value="Enter">
		</form:form>
		<br></br>
		<form:form
			action="http://localhost:2022/employee/get/${employee.employee_id}/cache/selected_account/${account.account_id}/installment-payment"
			method="GET">

			<label for="pay">Installment Payment: </label>
			<input name="payment" id="pay" />
			
			<label for="lid">Loan ID: </label>
			<input name="loan_id" id="lid" />

			<input type="submit" value="Enter">
		</form:form>

	</div>

</body>
</html>