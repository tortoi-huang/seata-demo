package org.huang.seata.score.controller;

import lombok.extern.slf4j.Slf4j;
import org.huang.seata.score.service.ScoreService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
public class ScoreController {

    private final ScoreService scoreService;

    public ScoreController(ScoreService scoreService) {
        this.scoreService = scoreService;
    }

    @PutMapping("/updateScoreById/{id}")
    public Integer updateScoreById(@PathVariable long id, @RequestParam("score") long score) throws Exception {
        log.info("== updateScoreById request param: id={}, account={}", id, score);
        int count = scoreService.updateScoreById(id, score);
        log.info("== updateScoreById response result: count={}", count);
        return count;
    }
}
