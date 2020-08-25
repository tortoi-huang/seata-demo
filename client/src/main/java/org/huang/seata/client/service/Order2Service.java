package org.huang.seata.client.service;

import org.huang.seata.account.api.AccountModel;

public interface Order2Service {
    void update(AccountModel model,long stockId,long stock) throws Exception;
}
