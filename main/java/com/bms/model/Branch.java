package com.bms.model;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

@Entity
public class Branch {

	@Id

	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int branch_id;

	@Column(unique = true, nullable = false, length = 15)
	private String branch_name;

	@Column(unique = false, nullable = false, length = 15)
	private String location;

	@OneToMany(mappedBy = "branch")
	private List<Employee> employees;

	@OneToMany(mappedBy = "branch", fetch = FetchType.EAGER)
	private List<Client> clients;

	@OneToOne
	@JoinColumn(name = "manager_id")
	private Employee employee;

	// setters and getters
	public int getBranch_id() {
		return branch_id;
	}

	public void setBranch_id(int branch_id) {
		this.branch_id = branch_id;
	}

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

	public List<Employee> getEmployees() {
		return employees;
	}

	public void setEmployees(List<Employee> employees) {
		this.employees = employees;
	}

	public List<Client> getClients() {
		return clients;
	}

	public void setClients(List<Client> clients) {
		this.clients = clients;
	}

	public Employee getEmployee() {
		return employee;
	}

	public void setEmployee(Employee employee) {
		this.employee = employee;
	}
}
