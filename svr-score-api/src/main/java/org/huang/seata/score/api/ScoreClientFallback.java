package org.huang.seata.score.api;

public class ScoreClientFallback implements ScoreClient {
    @Override
    public Integer updateScoreById(long id, long score) {
        return -1;
    }
}
