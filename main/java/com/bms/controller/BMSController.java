package com.bms.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.bms.service.BranchService;
import com.bms.service.ClientService;
import com.bms.service.EmployeeService;

@Controller
public class BMSController {

	@Autowired
	BranchService branchService;

	@Autowired
	EmployeeService employeeService;

	@Autowired
	ClientService clientService;

	@RequestMapping("/")
	public ModelAndView showHomePage(Authentication authentication) {

		ModelAndView mv = null;

		authentication = SecurityContextHolder.getContext().getAuthentication();
		switch (authentication.getAuthorities().toString()) {

		case "[ROLE_ADMIN]":
			mv = new ModelAndView("branches");
			mv.addObject("branches", branchService.getAll());
			mv.addObject("employees", employeeService.getAll());
			break;
		case "[ROLE_EMPLOYEE]":
			mv = new ModelAndView("employee");
			mv.addObject("employee", employeeService.get(Integer.parseInt(authentication.getName())));
			break;
		case "[ROLE_CLIENT]":
			mv = new ModelAndView("client");
			mv.addObject("client", clientService.get(Integer.parseInt(authentication.getName())));
			break;
		}
		return mv;
	}
}
