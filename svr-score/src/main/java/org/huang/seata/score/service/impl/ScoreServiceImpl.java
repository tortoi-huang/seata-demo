package org.huang.seata.score.service.impl;

import org.huang.seata.score.dao.ScoreDao;
import org.huang.seata.score.service.ScoreService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class ScoreServiceImpl implements ScoreService {
	private final ScoreDao scoreDao;

	public ScoreServiceImpl(ScoreDao scoreDao) {
		this.scoreDao = scoreDao;
	}

	@Override
	@Transactional
	public int updateScoreById(long id,long score) throws Exception {
		int i = scoreDao.updateScoreById(id, score);
		if(score < 0) {
			throw new RuntimeException("异常回滚");
		}
		if(score == 0) {
			throw new Exception("异常不回滚");
		}
		return i;
	}
}
