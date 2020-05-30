package org.huang.seata.stock.service.impl;

import lombok.extern.slf4j.Slf4j;
import org.huang.seata.stock.dao.StockDao;
import org.huang.seata.stock.service.StockService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
public class StockServiceImpl implements StockService {
    private final StockDao stockDao;

    public StockServiceImpl(StockDao stockDao) {
        this.stockDao = stockDao;
    }

    @Override
    @Transactional
    public int updateStockById(long id, long stock) throws Exception {
        log.info("== request param: id={}, account={}", id, stock);
        int i = stockDao.updateStockById(id, stock);
        log.info("== response result: count={}", i);
        if (id % 10 == 5) {
            log.error("== RuntimeException rollback for id={}", id);
            throw new RuntimeException("异常回滚");
        }
        if (id % 10 == 6) {
            log.error("== Exception not rollback for id={}", id);
            throw new Exception("异常不回滚");
        }
        return i;
    }
}
