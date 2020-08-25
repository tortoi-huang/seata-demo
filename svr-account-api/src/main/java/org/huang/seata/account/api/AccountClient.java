package org.huang.seata.account.api;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "svr-account", fallback = AccountClientFallback.class)
public interface AccountClient {

    @PutMapping(value = "/updateAccountById2/{id}", consumes = "application/json")
    Integer updateAccountById2(@PathVariable("id") long id, AccountModel account);
}
