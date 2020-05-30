package org.huang.seata.account.dao;

import org.huang.seata.account.entity.Account;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.JdbcTemplate;

import static org.junit.jupiter.api.Assertions.assertEquals;


//@RunWith(SpringRunner.class)
@SpringBootTest
public class AccountDaoTest {

    @Autowired
    private AccountDao accountDao;
    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Test
    public void updateAccountById() {
        final long id = 2L;
        final long account1 = 1000L;
        jdbcTemplate.update("replace into account(id,account) values (?,?);", id, account1);
        Account byId = accountDao.findById(id);
        assertEquals(account1, byId.getAccount().longValue());

        final long account2 = 1001L;
        accountDao.updateAccountById(id, account2);
        Account byId2 = accountDao.findById(id);
        assertEquals(account2, byId2.getAccount().longValue());

        jdbcTemplate.update("delete from account where id=?", id);
    }
}
