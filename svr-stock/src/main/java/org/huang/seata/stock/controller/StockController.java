package org.huang.seata.stock.controller;

import lombok.extern.slf4j.Slf4j;
import org.huang.seata.stock.service.StockService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
public class StockController {

    private final StockService stockService;

    public StockController(StockService stockService) {
        this.stockService = stockService;
    }

    @PutMapping("/updateStockById/{id}")
    public Integer updateStockById(@PathVariable long id, @RequestParam("stock") long stock) throws Exception {
        log.info("== updateStockById request param: id={}, account={}", id, stock);
        int count = stockService.updateStockById(id, stock);
        log.info("== updateStockById response result: count={}", count);
        return count;
    }
}
