package com.bms.controller;

import javax.validation.Valid;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.bms.dto.BranchDTO;
import com.bms.dto.EmployeeDTO;
import com.bms.model.Branch;
import com.bms.model.Employee;
import com.bms.service.BranchService;
import com.bms.service.EmployeeService;

@Controller
@RequestMapping("/branch")
public class BranchController {

	@Autowired
	BranchService branchService;

	@Autowired
	EmployeeService employeeService;

	@Autowired
	ModelMapper mapper;
	
	// POST
	@PostMapping("/register")
	public ModelAndView addToBranches(@Valid BranchDTO branchDTO) {

		branchService.add(mapper.map(branchDTO, Branch.class));

		ModelAndView mv = new ModelAndView("branches");
		mv.addObject("branches", branchService.getAll());
		//mv.addObject("employees", employeeService.getAll());
		return mv;
	}

	// DELETE
	@DeleteMapping("/delete/{id}")
	public void deleteBranch(@PathVariable int id) {
		branchService.delete(id);
	}

	@GetMapping("/get/{id}/employees")
	public ModelAndView getEmployees(@PathVariable int id) {

		ModelAndView mv = new ModelAndView("branch-employees");
		mv.addObject("branch", branchService.get(id));

		return mv;
	}
	
	@GetMapping("/get/{branch_id}/employees/{employee_id}/set-manager")
	public ModelAndView setManager(@PathVariable int branch_id, @PathVariable int employee_id) {

		Branch branch = branchService.get(branch_id);
		branch.setEmployee(employeeService.get(employee_id));
		branchService.update(branch, branch_id);
		
		ModelAndView mv = new ModelAndView("branch-employees");
		mv.addObject("branch", branch);

		return mv;
	}

	@GetMapping("get/{id}/clients")
	public ModelAndView getClients(@PathVariable int id) {

		ModelAndView mv = new ModelAndView("branch-clients");
		mv.addObject("branch", branchService.get(id));
		return mv;
	}

	@PostMapping("get/{id}/register-employee")
	public ModelAndView addToEmployeess(@PathVariable int id, @Valid EmployeeDTO employeeDTO) {

		Employee employee = mapper.map(employeeDTO, Employee.class);
		employee.setBranch(branchService.get(id));

		employeeService.add(employee);

		ModelAndView mv = new ModelAndView("branch-employees");
		mv.addObject("branch", branchService.get(id));

		return mv;
	}
}