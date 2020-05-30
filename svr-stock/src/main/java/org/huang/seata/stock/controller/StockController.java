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
    public ResponseEntity<Integer> updateStockById(@PathVariable long id, @RequestParam("stock") long stock) {
        log.info("== request param: id={}, account={}", id, stock);
        try {
            int count = stockService.updateStockById(id, stock);
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
