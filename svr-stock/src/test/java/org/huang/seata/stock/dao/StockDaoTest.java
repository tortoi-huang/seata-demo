package org.huang.seata.stock.dao;

import org.huang.seata.stock.entity.Stock;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.JdbcTemplate;

import static org.junit.jupiter.api.Assertions.assertEquals;

//@RunWith(SpringRunner.class)
@SpringBootTest
public class StockDaoTest {

    @Autowired
    private StockDao stockDao;
    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Test
    public void updateStockById() {
        final long id = 2;
        final long stock1 = 20000L;
        jdbcTemplate.update("replace into stock(id,stock) values (?,?);", id, stock1);
        Stock byId = stockDao.findById(id);
        assertEquals(stock1, byId.getStock().longValue());

        final long stock2 = 20001L;
        stockDao.updateStockById(id, stock2);
        Stock byId2 = stockDao.findById(id);
        assertEquals(stock2, byId2.getStock().longValue());

        jdbcTemplate.update("delete from stock where id=?", id);
    }
}
