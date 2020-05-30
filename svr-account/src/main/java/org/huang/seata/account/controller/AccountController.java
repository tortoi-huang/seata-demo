package org.huang.seata.account.controller;

import lombok.extern.slf4j.Slf4j;
import org.huang.seata.account.service.AccountService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
public class AccountController {
    private final AccountService accountService;

    public AccountController(AccountService accountService) {
        this.accountService = accountService;
    }

    @PutMapping("/updateAccountById/{id}")
    public ResponseEntity<Integer> updateAccountById(@PathVariable long id, @RequestParam("account") long account) {
        log.info("== request param: id={}, account={}", id, account);
        try {
            int count = accountService.updateAccountById(id, account);
            log.info("== response result: count={}", count);
            return ResponseEntity.ok(count);
        } catch (RuntimeException e) {
            log.error("== runtime:", e);
            return ResponseEntity.status(500).body(-1);
        } catch (Exception e) {
            log.error("== exception:", e);
            return ResponseEntity.status(500).body(-2);
        }
    }
}
