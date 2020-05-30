package org.huang.seata.stock.controller;

import org.huang.seata.stock.service.StockService;
import org.springframework.web.bind.annotation.*;

@RestController
public class StockController {

	private final StockService stockService;

	public StockController(StockService stockService) {
		this.stockService = stockService;
	}

	@PutMapping("/updateStockById/{id}")
	public int updateStockById(@PathVariable long id, @RequestParam("stock") long stock) throws Exception {
		return stockService.updateStockById(id,stock);
	}
}
