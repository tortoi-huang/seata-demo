package org.huang.seata.score.service.impl;

import lombok.extern.slf4j.Slf4j;
import org.huang.seata.score.dao.ScoreDao;
import org.huang.seata.score.service.ScoreService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
public class ScoreServiceImpl implements ScoreService {
    private final ScoreDao scoreDao;

    public ScoreServiceImpl(ScoreDao scoreDao) {
        this.scoreDao = scoreDao;
    }

    @Override
    @Transactional
    public int updateScoreById(long id, long score) throws Exception {
        log.info("== request param: id={}, account={}", id, score);
        int i = scoreDao.updateScoreById(id, score);
        log.info("== response result: count={}", i);
        if (id % 10 == 3) {
            log.error("== RuntimeException rollback for id={}", id);
            throw new RuntimeException("异常回滚");
        }
        if (id % 10 == 4) {
            log.error("== Exception not rollback for id={}", id);
            throw new Exception("异常不回滚");
        }
        return i;
    }
}
