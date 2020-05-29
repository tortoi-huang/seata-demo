package org.huang.seata.account.service.impl;

import org.huang.seata.account.dao.AccountDao;
import org.huang.seata.account.entity.Account;
import org.huang.seata.account.service.AccountService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

@RunWith(SpringRunner.class)
@SpringBootTest
public class AccountServiceImplTest {

    @Autowired
    private AccountService accountService;

    @Autowired
    private AccountDao accountDao;

    @Test
    public void updateAccountById() {
        final long id = 1;
        try {
            int i = accountService.updateAccountById(id, 1002);
            assertEquals(1,i);
            Account byId = accountDao.findById(id);
            assertEquals(1002,byId.getAccount().longValue());
        } catch (Exception e) {
            fail();
        }

        try {
            int i = accountService.updateAccountById(id, -1002);
            assertEquals(1,i);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        Account byId2 = accountDao.findById(id);
        assertEquals(1002,byId2.getAccount().longValue());

        try {
            int i = accountService.updateAccountById(id, 0);
            assertEquals(1,i);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        Account byId3 = accountDao.findById(id);
        assertEquals(0,byId3.getAccount().longValue());
    }
}
