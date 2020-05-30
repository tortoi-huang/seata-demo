package org.huang.seata.score.entity;

public class Score {
    private Long id;
    private Long score;

    public Score() {
    }

    public Score(Long id, Long score) {
        this.id = id;
        this.score = score;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getScore() {
        return score;
    }

    public void setScore(Long score) {
        this.score = score;
    }
}
