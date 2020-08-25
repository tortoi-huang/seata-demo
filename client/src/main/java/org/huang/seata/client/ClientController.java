package org.huang.seata.client;

import lombok.extern.slf4j.Slf4j;
import org.huang.seata.account.api.AccountModel;
import org.huang.seata.client.rpc.AccountRpc;
import org.huang.seata.client.service.Order2Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
public class ClientController {
    private final AccountRpc accountRpc;
    private final Order2Service order2Service;

    public ClientController(AccountRpc accountRpc, Order2Service order2Service) {
        this.accountRpc = accountRpc;
        this.order2Service = order2Service;
    }

    @GetMapping("/updateAccountById/{id}/{account}")
    public Integer updateAccountById(@PathVariable long id, @PathVariable long account) {
        try {
            return accountRpc.updateAccountById(id, account);
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
    }

    /**
     * 设置初始值
     * @return 成功返回1 失败返回0
     */
    @GetMapping("/update/1")
    public Integer update1() {
        try {
            log.info("== update in client start");
            AccountModel model = new AccountModel(1L, 1001L, 1L, 20002L);
            order2Service.update(model, 1, 300003);
            log.info("== update in client end");
            return 1;
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
    }

    /**
     * 在没有分布式事务的情况下，账户回滚，积分扣减成功，库存未执行
     * @return 成功返回1 失败返回0
     */
    @GetMapping("/update/2")
    public Integer update2() {
        try {
            log.info("== update in client start");
            AccountModel model = new AccountModel(1L, -1L, 1L, 20022L);
            order2Service.update(model, 1, 300023);
            log.info("== update in client end");
            return 1;
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
    }

    /**
     * 在没有分布式事务的情况下，账户回滚，积分扣减回滚，库存未执行
     * @return 成功返回1 失败返回0
     */
    @GetMapping("/update/3")
    public Integer update3() {
        try {
            log.info("== update in client start");
            AccountModel model = new AccountModel(1L, 1031L, 1L, -1L);
            order2Service.update(model, 1, 300033);
            log.info("== update in client end");
            return 1;
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
    }

    /**
     * 在没有分布式事务的情况下，账户扣减成功，积分扣减成功，库存回滚
     * @return 成功返回1 失败返回0
     */
    @GetMapping("/update/4")
    public Integer update4() {
        try {
            log.info("== update in client start");
            AccountModel model = new AccountModel(1L, 1041L, 1L, 20042L);
            order2Service.update(model, 1, -1L);
            log.info("== update in client end");
            return 1;
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
    }
}
