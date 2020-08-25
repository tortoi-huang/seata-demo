package org.huang.seata.stock.api;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "svr-stock", fallback = StockClientFallback.class)
public interface StockClient {

    @PutMapping(value = "/updateStockById/{id}", consumes = "application/x-www-form-urlencoded")
    Integer updateStockById(@PathVariable("id") long id, @RequestParam("stock") long stock);
}
