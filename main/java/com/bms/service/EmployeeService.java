package com.bms.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bms.dao.EmployeeRepo;
import com.bms.model.Employee;

@Service
public class EmployeeService implements ServiceInterface<Employee> {

	@Autowired
	private EmployeeRepo repo;

	@Override
	public List<Employee> getAll() {
		return repo.findAll();
	}

	@Override
	public Employee add(Employee employee) {
		return repo.save(employee);
	}

	@Override
	public Employee get(int id) {
		return repo.findById(id).orElse(null);
	}

	@Override
	public Employee update(Employee employee, int id) {
		
		Employee updatedEmployee = get(id);
		updatedEmployee.setBranch(employee.getBranch());
		updatedEmployee.setDOB(employee.getDOB());
		updatedEmployee.setPhone(employee.getPhone());
		updatedEmployee.setSalary(employee.getSalary());
		updatedEmployee.setCity(employee.getCity());
		updatedEmployee.setStreet(employee.getStreet());
		updatedEmployee.setPin(employee.getPin());
		return add(updatedEmployee);
	}

	@Override
	public void delete(int id) {
		repo.deleteById(id);
	}
}
