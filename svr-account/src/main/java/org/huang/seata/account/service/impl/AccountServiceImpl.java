package org.huang.seata.account.service.impl;

import lombok.extern.slf4j.Slf4j;
import org.huang.seata.account.api.AccountModel;
import org.huang.seata.account.dao.AccountDao;
import org.huang.seata.account.rpc.ScoreRpc;
import org.huang.seata.account.service.AccountService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
public class AccountServiceImpl implements AccountService {
    private final AccountDao accountDao;
    private final ScoreRpc scoreRpc;

    public AccountServiceImpl(AccountDao accountDao, ScoreRpc scoreRpc) {
        this.accountDao = accountDao;
        this.scoreRpc = scoreRpc;
    }

    @Override
    @Transactional
    public int updateAccountById(long id, AccountModel account) throws Exception {
        log.info("== request param: id={}, account={}", id, account);
        int i = accountDao.updateAccountById(id, account.getAccount());
        scoreRpc.updateScoreById(account.getScoreId(), account.getScore());
        log.info("== response result: count={}", i);
        if (account.getAccount() < 0) {
            log.error("== RuntimeException rollback for id={}", id);
            throw new RuntimeException("异常回滚");
        }
        if (account.getAccount() > 10_000_000) {
            log.error("== Exception not rollback for id={}", id);
            throw new Exception("异常不回滚");
        }
        return i;
    }
}
