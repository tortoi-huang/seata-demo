package org.huang.seata.account.dao;

import org.huang.seata.account.entity.Account;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class AccountDao {
    private final JdbcTemplate jdbcTemplate;

    public AccountDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public int updateAccountById(long id, long account) {
        return jdbcTemplate.update("update account set account=? where id=?", account, id);
    }

    public Account findById(long id) {
        return jdbcTemplate.queryForObject("select * from account where id=?", new Object[]{id}, (r, n) -> new Account(r.getLong("id"), r.getLong("account")));
    }
}
