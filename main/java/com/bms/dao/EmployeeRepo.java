package com.bms.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.bms.model.Employee;

public interface EmployeeRepo extends JpaRepository<Employee, Integer> {

}
