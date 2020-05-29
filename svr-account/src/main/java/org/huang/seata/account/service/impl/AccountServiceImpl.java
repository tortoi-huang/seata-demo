package org.huang.seata.account.service.impl;

import org.huang.seata.account.dao.AccountDao;
import org.huang.seata.account.service.AccountService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class AccountServiceImpl implements AccountService {
	private final AccountDao accountDao;

	public AccountServiceImpl(AccountDao accountDao) {
		this.accountDao = accountDao;
	}

	@Override
	@Transactional
	public int updateAccountById(long id,long account) throws Exception {
		int i = accountDao.updateAccountById(id, account);
		if(account < 0) {
			throw new RuntimeException("异常回滚");
		}
		if(account == 0) {
			throw new Exception("异常不回滚");
		}
		return i;
	}
}
