package org.huang.seata.stock.dao;

import org.huang.seata.stock.entity.Stock;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class StockDao {
    private final JdbcTemplate jdbcTemplate;

    public StockDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public int updateStockById(long id,long stock) {
        return jdbcTemplate.update("update stock set stock=? where id=?",stock,id);
    }

    public Stock findById(long id) {
        return jdbcTemplate.queryForObject("select * from stock where id=?",new Object[]{id},(r,n) -> new Stock(r.getLong("id"),r.getLong("stock")));
    }
}
