package org.huang.seata.account.controller;

import org.huang.seata.account.service.AccountService;
import org.springframework.web.bind.annotation.*;

@RestController
public class AccountController {

	private final AccountService accountService;

	public AccountController(AccountService accountService) {
		this.accountService = accountService;
	}

	@PutMapping("/updateAccountById/{id}")
	public int updateAccountById(@PathVariable long id, @RequestParam("account") long account) throws Exception {
		return accountService.updateAccountById(id,account);
	}
}
