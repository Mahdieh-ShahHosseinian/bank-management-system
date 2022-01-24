package com.bms.controller;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.bms.service.AccountService;

@Controller
@RequestMapping("/account")
public class AccountController {

	@Autowired
	AccountService accountService;

	@Autowired
	ModelMapper mapper;

	@GetMapping("/get/{id}")
	public ModelAndView get(@PathVariable int id) {

		ModelAndView mv = new ModelAndView("");
		mv.addObject(accountService.get(id));
		return mv;
	}
}