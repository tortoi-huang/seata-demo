package org.huang.seata.client.service.impl;

import lombok.extern.slf4j.Slf4j;
import org.huang.seata.account.api.AccountClient;
import org.huang.seata.account.api.AccountModel;
import org.huang.seata.client.rpc.AccountRpc;
import org.huang.seata.client.rpc.StockRpc;
import org.huang.seata.client.service.Order2Service;
import org.huang.seata.client.service.OrderService;
import org.huang.seata.stock.api.StockClient;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class Order2ServiceImpl implements Order2Service {
    private final AccountClient accountRpc;
    private final StockClient stockRpc;

    public Order2ServiceImpl(AccountClient accountRpc, StockClient stockRpc) {
        this.accountRpc = accountRpc;
        this.stockRpc = stockRpc;
    }

    @Override
    public void update(AccountModel model,long stockId,long stock) throws Exception {
        log.info("== request param: id={}, account={}", model.getId(), model);
        Integer account = accountRpc.updateAccountById2(model.getId(), model);
        Integer sc = stockRpc.updateStockById(stockId, stock);
        log.info("== response result: account={}, stock={}", account, sc);
    }
}
