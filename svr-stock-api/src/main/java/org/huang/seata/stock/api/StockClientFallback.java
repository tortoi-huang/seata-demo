package org.huang.seata.stock.api;

public class StockClientFallback implements StockClient {
    @Override
    public Integer updateStockById(long id, long stock) {
        return -1;
    }
}
