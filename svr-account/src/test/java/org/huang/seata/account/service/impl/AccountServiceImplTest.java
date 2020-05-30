package org.huang.seata.account.service.impl;

import org.huang.seata.account.dao.AccountDao;
import org.huang.seata.account.entity.Account;
import org.huang.seata.account.service.AccountService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class AccountServiceImplTest {

    @Autowired
    private AccountService accountService;

    @Autowired
    private AccountDao accountDao;
    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Test
    public void updateAccountById() {
        final long id = 3L;
        final long account1 = 1000L;

        jdbcTemplate.update("replace into account(id,account) values (?,?);", id,account1);
        final long account2 = 1002L;
        try {
            int i = accountService.updateAccountById(id, account2);
            assertEquals(1,i);
            Account byId = accountDao.findById(id);
            assertEquals(account2,byId.getAccount().longValue());
        } catch (Exception e) {
            e.printStackTrace();
            fail();
        }

        final long account3 = -1002L;
        boolean runtime = false;
        try {
            accountService.updateAccountById(id, account3);
        } catch (RuntimeException e) {
            System.out.println(e.getMessage());
            runtime = true;
        } catch (Exception e) {
            fail();
        }
        Account byId2 = accountDao.findById(id);
        //这里回滚了，值没有变
        assertEquals(account2,byId2.getAccount().longValue());
        assertTrue(runtime);

        boolean exception = false;
        try {
            accountService.updateAccountById(id, 0);
        }  catch (RuntimeException e) {
            fail();
        } catch (Exception e) {
            exception = true;
            System.out.println(e.getMessage());
        }
        Account byId3 = accountDao.findById(id);
        //这里没有回滚，值变了
        assertEquals(0,byId3.getAccount().longValue());
        assertTrue(exception);
    }
}
