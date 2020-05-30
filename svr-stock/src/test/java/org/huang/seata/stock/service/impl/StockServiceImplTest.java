package org.huang.seata.stock.service.impl;

import org.huang.seata.stock.dao.StockDao;
import org.huang.seata.stock.entity.Stock;
import org.huang.seata.stock.service.StockService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.JdbcTemplate;

import static org.junit.jupiter.api.Assertions.*;

//@RunWith(SpringRunner.class)
@SpringBootTest
public class StockServiceImplTest {

    @Autowired
    private StockService stockService;

    @Autowired
    private StockDao stockDao;
    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Test
    public void updateStockById1() {
        final long id = 1L;
        final long stock1 = 1000L;

        jdbcTemplate.update("replace into stock(id,stock) values (?,?);", id, stock1);
        final long stock2 = 1002L;
        try {
            int i = stockService.updateStockById(id, stock2);
            assertEquals(1, i);
            Stock byId = stockDao.findById(id);
            assertEquals(stock2, byId.getStock().longValue());
        } catch (Exception e) {
            e.printStackTrace();
            fail();
        }
    }

    @Test
    public void updateStockById2() {
        final long id = 5L;
        final long stock1 = 1000L;

        jdbcTemplate.update("replace into stock(id,stock) values (?,?);", id, stock1);
        final long stock3 = 1002L;
        boolean runtime = false;
        try {
            stockService.updateStockById(id, stock3);
        } catch (RuntimeException e) {
            System.out.println(e.getMessage());
            runtime = true;
        } catch (Exception e) {
            fail();
        }
        Stock byId2 = stockDao.findById(id);
        //这里回滚了，值没有变
        assertEquals(stock1, byId2.getStock().longValue());
        assertTrue(runtime);
    }

    @Test
    public void updateStockById3() {
        final long id = 6L;
        final long stock1 = 1000L;

        jdbcTemplate.update("replace into stock(id,stock) values (?,?);", id, stock1);

        final long stock3 = 1002L;
        boolean exception = false;
        try {
            stockService.updateStockById(id, stock3);
        } catch (RuntimeException e) {
            fail();
        } catch (Exception e) {
            exception = true;
            System.out.println(e.getMessage());
        }
        Stock byId3 = stockDao.findById(id);
        //这里没有回滚，值变了
        assertEquals(stock3, byId3.getStock().longValue());
        assertTrue(exception);
    }
}
