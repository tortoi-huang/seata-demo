package org.huang.seata.score.service.impl;

import org.huang.seata.score.dao.ScoreDao;
import org.huang.seata.score.entity.Score;
import org.huang.seata.score.service.ScoreService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.JdbcTemplate;

import static org.junit.jupiter.api.Assertions.*;

//@RunWith(SpringRunner.class)
@SpringBootTest
public class ScoreServiceImplTest {

    @Autowired
    private ScoreService scoreService;

    @Autowired
    private ScoreDao scoreDao;
    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Test
    public void updateScoreById1() {
        final long id = 1L;
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
    }

    @Test
    public void updateScoreById2() {
        final long id = 3L;
        final long score1 = 1000L;

        jdbcTemplate.update("replace into score(id,score) values (?,?);", id, score1);

        final long score3 = 1002L;
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
        assertEquals(score1, byId2.getScore().longValue());
        assertTrue(runtime);
    }

    @Test
    public void updateScoreById() {
        final long id = 4L;
        final long score1 = 1000L;

        jdbcTemplate.update("replace into score(id,score) values (?,?);", id, score1);

        final long score3 = 1002L;
        boolean exception = false;
        try {
            scoreService.updateScoreById(id, score3);
        } catch (RuntimeException e) {
            fail();
        } catch (Exception e) {
            exception = true;
            System.out.println(e.getMessage());
        }
        Score byId3 = scoreDao.findById(id);
        //这里没有回滚，值变了
        assertEquals(score3, byId3.getScore().longValue());
        assertTrue(exception);
    }
}
