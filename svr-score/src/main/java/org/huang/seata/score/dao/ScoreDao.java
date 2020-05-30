package org.huang.seata.score.dao;

import org.huang.seata.score.entity.Score;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class ScoreDao {
    private final JdbcTemplate jdbcTemplate;

    public ScoreDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public int updateScoreById(long id, long score) {
        return jdbcTemplate.update("update score set score=? where id=?", score, id);
    }

    public Score findById(long id) {
        return jdbcTemplate.queryForObject("select * from score where id=?", new Object[]{id}, (r, n) -> new Score(r.getLong("id"), r.getLong("score")));
    }
}
