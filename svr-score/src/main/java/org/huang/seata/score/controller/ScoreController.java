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
    public ResponseEntity<Integer> updateScoreById(@PathVariable long id, @RequestParam("score") long score) {
        log.info("== request param: id={}, account={}", id, score);
        try {
            int count = scoreService.updateScoreById(id, score);
            log.info("== response result: count={}", count);
            return ResponseEntity.ok(count);
        } catch (RuntimeException e) {
            log.error("== runtime:", e);
            return ResponseEntity.status(500).body(-1);
        } catch (Exception e) {
            log.error("== exception:", e);
            return ResponseEntity.status(500).body(-2);
        }
    }
}
