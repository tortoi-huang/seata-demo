package org.huang.seata.account.service;

import org.huang.seata.account.api.AccountModel;

public interface Account2Service {
    int updateAccountById(long id, AccountModel account) throws Exception;
}
