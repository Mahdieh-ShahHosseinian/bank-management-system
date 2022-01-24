package com.bms.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.validation.Valid;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.bms.dto.ClientDTO;
import com.bms.exception.BalanceNotEnoughException;
import com.bms.exception.RecordNotFoundException;
import com.bms.model.Account;
import com.bms.model.Client;
import com.bms.model.Employee;
import com.bms.model.Loan;
import com.bms.model.Request;
import com.bms.model.Transaction;
import com.bms.service.AccountService;
import com.bms.service.ClientService;
import com.bms.service.EmployeeService;
import com.bms.service.LoanService;
import com.bms.service.RequestService;
import com.bms.service.TransactionService;

@Controller
@RequestMapping("/employee")
public class EmployeeController {

	@Autowired
	EmployeeService employeeService;

	@Autowired
	RequestService requestService;

	@Autowired
	ClientService clientService;

	@Autowired
	AccountService accountService;

	@Autowired
	LoanService loanService;

	@Autowired
	TransactionService transactionService;

	@Autowired
	ModelMapper mapper;

	@GetMapping("get/{employee_id}/requests")
	public ModelAndView getRequests(@PathVariable int employee_id) {

		ModelAndView mv = new ModelAndView("requests");

		mv.addObject("employee", employeeService.get(employee_id));
		mv.addObject("requests", requestService.getAll());

		return mv;
	}

	@GetMapping("get/{employee_id}/request/{request_id}/accept_request")
	public ModelAndView acceptRrequest(@PathVariable int employee_id, @PathVariable int request_id) {

		ModelAndView mv = new ModelAndView("requests");

		Request request = requestService.get(request_id);

		if (request.getType().equals("REQUEST_OPEN_ACCOUNT")) {

			Account account = new Account();
			account.setBalance(50000);
			account.setOpen_date(new Date());

			Client client = clientService.get(request.getClient_id());
			account.setClient(client);

			accountService.add(account);

		} else if (request.getType().equals("REQUEST_LOAN")) {

			Loan loan = new Loan();
			loan.setInterest(2);
			loan.setDuration(90);
			loan.setTotal_amount(3000000);
			loan.setRemaining_amount(3000000);
			loan.setAccount(accountService.get(request.getAccount_id()));

			loanService.add(loan);
		}

		requestService.delete(request_id);

		mv.addObject("employee", employeeService.get(employee_id));
		mv.addObject("requests", requestService.getAll());

		return mv;
	}

	@GetMapping("get/{employee_id}/request/{request_id}/decline_request")
	public ModelAndView declineRrequest(@PathVariable int employee_id, @PathVariable int request_id) {

		ModelAndView mv = new ModelAndView("requests");
		requestService.delete(request_id);

		mv.addObject("employee", employeeService.get(employee_id));
		mv.addObject("requests", requestService.getAll());

		return mv;
	}

	@GetMapping("get/{employee_id}/cache")
	public ModelAndView getClients(@PathVariable int employee_id) {

		ModelAndView mv = new ModelAndView("cache");

		mv.addObject("employee", employeeService.get(employee_id));

		return mv;
	}

	@PostMapping("get/{employee_id}/cache/selected_account")
	public ModelAndView findClient(@PathVariable int employee_id, int account_id) {

		Account account = accountService.get(account_id);

		if (account == null)
			throw new RecordNotFoundException("There is no such account");

		ModelAndView mv = new ModelAndView("cache-selected-account");
		mv.addObject("employee", employeeService.get(employee_id));
		mv.addObject("account", account);
		return mv;
	}

	@GetMapping("/get/{employee_id}/cache/selected_account/{account_id}/deposit")
	public ModelAndView deposit(@PathVariable int employee_id, @PathVariable int account_id, int deposit_cache) {

		ModelAndView mv = new ModelAndView("cache-selected-account");

		Account account = accountService.get(account_id);
		account = accountService.updateBalance(account.getBalance() + deposit_cache, account_id);

		Transaction trans = new Transaction();

		List<Account> accounts = new ArrayList<>();
		accounts.add(account);

		trans.setAccounts(accounts);
		trans.setAmount(deposit_cache);
		trans.setDate(new Date());
		trans.setType("DEPOSIT");

		transactionService.add(trans);

		mv.addObject("employee", employeeService.get(employee_id));
		mv.addObject("account", account);
		return mv;
	}

	@GetMapping("/get/{employee_id}/cache/selected_account/{account_id}/withdraw")
	public ModelAndView withdraw(@PathVariable int employee_id, @PathVariable int account_id, int withdraw_cache) {

		ModelAndView mv = new ModelAndView("cache-selected-account");

		Account account = accountService.get(account_id);
		if (account.getBalance() >= withdraw_cache) {
			account = accountService.updateBalance(account.getBalance() - withdraw_cache, account_id);

			Transaction trans = new Transaction();

			List<Account> accounts = new ArrayList<>();
			accounts.add(account);

			trans.setAccounts(accounts);
			trans.setAmount(withdraw_cache);
			trans.setDate(new Date());
			trans.setType("WITHDRAW");

			transactionService.add(trans);

		} else {
			throw new BalanceNotEnoughException();
		}

		mv.addObject("employee", employeeService.get(employee_id));
		mv.addObject("account", account);
		return mv;
	}

	@GetMapping("/get/{employee_id}/cache/selected_account/{account_id}/installment-payment")
	public ModelAndView installmentPayment(@PathVariable int employee_id, @PathVariable int account_id, int payment,
			int loan_id) {
		ModelAndView mv = new ModelAndView("cache-selected-account");

		Account account = accountService.get(account_id);
		if (account.getBalance() >= payment) {

			Loan loan = loanService.get(loan_id);

			if (loan == null || !loan.getAccount().equals(account))
				throw new RecordNotFoundException("There is no such loan in this account");

			account = accountService.updateBalance(account.getBalance() - payment, account_id);
			loan = loanService.updateRamainingAmount(loan.getRemaining_amount() - payment, loan_id);

			Transaction trans = new Transaction();

			List<Account> accounts = new ArrayList<>();
			accounts.add(account);

			trans.setAccounts(accounts);
			trans.setAmount(payment);
			trans.setDate(new Date());
			trans.setType("INSTALLMENT_PAYMENT");

			transactionService.add(trans);

		} else {
			throw new BalanceNotEnoughException();
		}

		mv.addObject("employee", employeeService.get(employee_id));
		mv.addObject("account", account);
		return mv;
	}

	@GetMapping("get/{employee_id}/register-client")
	public ModelAndView addToClients(@Valid ClientDTO clientDTO, @PathVariable int employee_id) {

		Client client = mapper.map(clientDTO, Client.class);
		Employee employee = employeeService.get(employee_id);

		client.setBranch(employee.getBranch());

		clientService.add(client);

		ModelAndView mv = new ModelAndView("employee");
		mv.addObject("employee", employee);
		return mv;
	}
}
