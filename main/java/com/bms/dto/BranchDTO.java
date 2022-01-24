package com.bms.dto;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

import com.bms.model.Employee;

public class BranchDTO {

	@NotEmpty(message = "name must not be null")
	@Size(min = 2, message = "Branch name should have at least 2 characters")
	private String branch_name;

	private String location;

	private Employee employee;

	public String getBranch_name() {
		return branch_name;
	}

	public void setBranch_name(String branch_name) {
		this.branch_name = branch_name;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public Employee getEmployee() {
		return employee;
	}

	public void setEmployee(Employee employee) {
		this.employee = employee;
	}
}
