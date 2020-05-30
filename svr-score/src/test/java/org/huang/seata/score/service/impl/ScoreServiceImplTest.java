package org.huang.seata.score.service.impl;

import org.huang.seata.score.dao.ScoreDao;
import org.huang.seata.score.entity.Score;
import org.huang.seata.score.service.ScoreService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.*;
import static org.junit.Assert.assertTrue;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ScoreServiceImplTest {

    @Autowired
    private ScoreService scoreService;

    @Autowired
    private ScoreDao scoreDao;
    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Test
    public void updateScoreById() {
        final long id = 3L;
        final long score1 = 1000L;

        jdbcTemplate.update("replace into score(id,score) values (?,?);", id, score1);
        final long score2 = 1002L;
        try {
            int i = scoreService.updateScoreById(id, score2);
            assertEquals(1, i);
            Score byId = scoreDao.findById(id);
            assertEquals(score2, byId.getScore().longValue());
        } catch (Exception e) {
            e.printStackTrace();
            fail();
        }

        final long score3 = -1002L;
        boolean runtime = false;
        try {
            scoreService.updateScoreById(id, score3);
        } catch (RuntimeException e) {
            System.out.println(e.getMessage());
            runtime = true;
        } catch (Exception e) {
            fail();
        }
        Score byId2 = scoreDao.findById(id);
        //这里回滚了，值没有变
        assertEquals(score2, byId2.getScore().longValue());
        assertTrue(runtime);

        boolean exception = false;
        try {
            scoreService.updateScoreById(id, 0);
        } catch (RuntimeException e) {
            fail();
        } catch (Exception e) {
            exception = true;
            System.out.println(e.getMessage());
        }
        Score byId3 = scoreDao.findById(id);
        //这里没有回滚，值变了
        assertEquals(0, byId3.getScore().longValue());
        assertTrue(exception);
    }
}
