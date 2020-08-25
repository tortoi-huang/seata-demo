package org.huang.seata.account.controller;

import lombok.extern.slf4j.Slf4j;
import org.huang.seata.account.api.AccountModel;
import org.huang.seata.account.service.Account2Service;
import org.huang.seata.account.service.AccountService;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
public class AccountController {
    private final AccountService accountService;
    private final Account2Service account2Service;

    public AccountController(AccountService accountService, Account2Service account2Service) {
        this.accountService = accountService;
        this.account2Service = account2Service;
    }

    @PutMapping("/updateAccountById/{id}")
    public Integer updateAccountById(@PathVariable long id, @RequestBody AccountModel account) throws Exception {
        log.info("== request param: id={}, account={}", id, account);
        int count = accountService.updateAccountById(id, account);
        log.info("== response result: count={}", count);
        return count;
    }

    @PutMapping("/updateAccountById2/{id}")
    public Integer updateAccountById2(@PathVariable long id, @RequestBody AccountModel account) throws Exception {
        log.info("== updateAccountById2 request param: id={}, account={}", id, account);
        int count = account2Service.updateAccountById(id, account);
        log.info("== updateAccountById2 response result: count={}", count);
        return count;
    }
}
