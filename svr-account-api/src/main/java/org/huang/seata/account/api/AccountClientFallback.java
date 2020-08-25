package org.huang.seata.account.api;

public class AccountClientFallback implements AccountClient {
    @Override
    public Integer updateAccountById2(long id, AccountModel account) {
        return -1;
    }
}
