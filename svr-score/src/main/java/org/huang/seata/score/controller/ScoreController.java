package org.huang.seata.score.controller;

import org.huang.seata.score.service.ScoreService;
import org.springframework.web.bind.annotation.*;

@RestController
public class ScoreController {

	private final ScoreService scoreService;

	public ScoreController(ScoreService scoreService) {
		this.scoreService = scoreService;
	}

	@PutMapping("/updateScoreById/{id}")
	public int updateScoreById(@PathVariable long id, @RequestParam("score") long score) throws Exception {
		return scoreService.updateScoreById(id,score);
	}
}
