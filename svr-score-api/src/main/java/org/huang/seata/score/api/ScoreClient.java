package org.huang.seata.score.api;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "svr-score", fallback = ScoreClientFallback.class)
public interface ScoreClient {

    @PutMapping(value = "/updateScoreById/{id}", consumes = "application/x-www-form-urlencoded")
    Integer updateScoreById(@PathVariable("id") long id, @RequestParam("score") long score);
}
