package com.bms.controller;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.bms.exception.BalanceNotEnoughException;
import com.bms.exception.RecordNotFoundException;
import com.bms.model.Account;
import com.bms.model.Request;
import com.bms.model.Transaction;
import com.bms.service.AccountService;
import com.bms.service.ClientService;
import com.bms.service.RequestService;
import com.bms.service.TransactionService;

@Controller
@RequestMapping("/client")
public class ClientController {

	@Autowired
	ClientService clientService;

	@Autowired
	AccountService accountService;

	@Autowired
	RequestService requestService;

	@Autowired
	TransactionService transactionService;

	@Autowired
	ModelMapper mapper;

	@GetMapping("get/{client_id}/account/{account_id}")
	public ModelAndView getAccounts(@PathVariable int client_id, @PathVariable int account_id) {

		ModelAndView mv = new ModelAndView("client-account");

		mv.addObject("client", clientService.get(client_id));
		mv.addObject("selected_account", accountService.get(account_id));

		return mv;
	}

	@PostMapping("get/{client_id}/add_account")
	public ModelAndView requestOpenAccount(@PathVariable int client_id, Request request) {

		ModelAndView mv = new ModelAndView("client");
		mv.addObject("client", clientService.get(client_id));

		request.setClient_id(client_id);
		request.setType("REQUEST_OPEN_ACCOUNT");
		requestService.add(request);

		return mv;
	}

	@PostMapping("get/{client_id}/account/{account_id}/add_loan")
	public ModelAndView requestLoan(@PathVariable int client_id, @PathVariable int account_id, Request request) {

		ModelAndView mv = new ModelAndView("client-account");
		mv.addObject("client", clientService.get(client_id));
		mv.addObject("selected_account", accountService.get(account_id));

		request.setAccount_id(account_id);
		request.setClient_id(client_id);
		request.setType("REQUEST_LOAN");
		requestService.add(request);

		return mv;
	}

	@GetMapping("get/{client_id}/account/{account_id}/account_history")
	public ModelAndView history(@PathVariable int client_id, @PathVariable int account_id) {

		ModelAndView mv = new ModelAndView("account_history");

		Account account = accountService.get(account_id);

		Calendar calendar = Calendar.getInstance();
		calendar.add(Calendar.MONTH, -1);

		List<Transaction> transactions = new ArrayList<>();

		for (Transaction trans : account.getTransactions()) {
			if (trans.getDate().after(calendar.getTime())) {
				transactions.add(trans);
			}
		}

		mv.addObject("client", clientService.get(client_id));
		mv.addObject("selected_account", account);
		mv.addObject("transactions", transactions);

		return mv;
	}

	@GetMapping("get/{client_id}/account/{account_id}/transfer")
	public ModelAndView transferControl(@PathVariable int client_id, @PathVariable int account_id) {

		ModelAndView mv = new ModelAndView("transfer");

		mv.addObject("client", clientService.get(client_id));
		mv.addObject("selected_account", accountService.get(account_id));

		return mv;
	}

	@GetMapping("get/{client_id}/account/{account_id}/transfer-accepted")
	public ModelAndView transferCache(@PathVariable int client_id, @PathVariable int account_id, int amount,
			int destAccount_id) {

		ModelAndView mv = new ModelAndView("transfer");
		Account srcAccount = accountService.get(account_id);
		Account destAccount = accountService.get(destAccount_id);

		if (destAccount == null)
			throw new RecordNotFoundException("There is no such account");

		if (srcAccount.getBalance() >= amount) {

			destAccount = accountService.updateBalance(destAccount.getBalance() + amount, destAccount_id);
			srcAccount = accountService.updateBalance(srcAccount.getBalance() - amount, account_id);

			Transaction trans = new Transaction();

			List<Account> accounts = new ArrayList<>();
			accounts.add(srcAccount);
			accounts.add(destAccount);

			trans.setAccounts(accounts);
			trans.setAmount(amount);
			trans.setDate(new Date());
			trans.setType("TRANSFER");

			transactionService.add(trans);
		} else {
			throw new BalanceNotEnoughException();
		}
		mv.addObject("client", clientService.get(client_id));
		mv.addObject("selected_account", srcAccount);

		return mv;
	}
}