package org.huang.seata.client.service.impl;

import lombok.extern.slf4j.Slf4j;
import org.huang.seata.client.rpc.AccountRpc;
import org.huang.seata.client.rpc.StockRpc;
import org.huang.seata.client.service.OrderService;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class OrderServiceImpl implements OrderService {
    private final AccountRpc accountRpc;
    private final StockRpc stockRpc;

    public OrderServiceImpl(AccountRpc accountRpc, StockRpc stockRpc) {
        this.accountRpc = accountRpc;
        this.stockRpc = stockRpc;
    }

    @Override
    public void update(long id, long number) throws Exception {
        log.info("== request param: id={}, account={}", id, number);
        Integer account = accountRpc.updateAccountById(id, number);
        Integer stock = stockRpc.updateStockById(id, number);
        log.info("== response result: account={}, stock={}", account, stock);
    }
}
