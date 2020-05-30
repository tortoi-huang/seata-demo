package org.huang.seata.stock.service.impl;

import org.huang.seata.stock.dao.StockDao;
import org.huang.seata.stock.service.StockService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class StockServiceImpl implements StockService {
	private final StockDao stockDao;

	public StockServiceImpl(StockDao stockDao) {
		this.stockDao = stockDao;
	}

	@Override
	@Transactional
	public int updateStockById(long id,long stock) throws Exception {
		int i = stockDao.updateStockById(id, stock);
		if(stock < 0) {
			throw new RuntimeException("异常回滚");
		}
		if(stock == 0) {
			throw new Exception("异常不回滚");
		}
		return i;
	}
}
