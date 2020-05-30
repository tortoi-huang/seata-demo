package org.huang.seata.client;

import org.huang.seata.client.rpc.AccountRpc;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ClientController {
    private final AccountRpc accountRpc;

    public ClientController(AccountRpc accountRpc) {
        this.accountRpc = accountRpc;
    }

    @GetMapping("/updateAccountById/{id}/{account}")
    public Integer updateAccountById(long id, long account) {
        try {
            return accountRpc.updateAccountById(id, account);
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
    }
}
