package org.huang.seata.score.dao;

import org.huang.seata.score.entity.Score;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.assertEquals;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ScoreDaoTest {

    @Autowired
    private ScoreDao scoreDao;
    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Test
    public void updateScoreById() {
        final long id = 2;
        final long score1 = 300000L;
        jdbcTemplate.update("replace into score(id,score) values (?,?);", id,score1);
        Score byId = scoreDao.findById(id);
        assertEquals(score1,byId.getScore().longValue());

        final long score2 = 300001L;
        scoreDao.updateScoreById(id,score2);
        Score byId2 = scoreDao.findById(id);
        assertEquals(score2,byId2.getScore().longValue());

        jdbcTemplate.update("delete from score where id=?", id);
    }
}
