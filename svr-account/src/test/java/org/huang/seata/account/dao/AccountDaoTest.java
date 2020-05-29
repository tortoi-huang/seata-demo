package org.huang.seata.account.dao;

import org.huang.seata.account.entity.Account;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.assertEquals;

@RunWith(SpringRunner.class)
@SpringBootTest
public class AccountDaoTest {

    @Autowired
    private AccountDao accountDao;

    @Test
    public void updateAccountById() {
        final long id = 1;
        Account byId = accountDao.findById(id);
        assertEquals(1000L,byId.getAccount().longValue());
        accountDao.updateAccountById(id,1001);
        Account byId2 = accountDao.findById(id);
        assertEquals(1001L,byId2.getAccount().longValue());
    }
}
