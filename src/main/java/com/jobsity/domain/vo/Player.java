package com.jobsity.domain.vo;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.ArrayList;
import java.util.List;

@Data
@EqualsAndHashCode(of = {"name"})
public class Player {
    private String name;
    private List<RollScore> rollScores;
    private List<Integer> scores;
    private int totalRollValue;
    private final int maxSumRollScoreValue;
    public Player(String name, int totalFrames, int rollsPerFrame, int extrasRoll, int maxSumRollScoreValue) {
        this.totalRollValue = 0;
        this.name = name;
        this.maxSumRollScoreValue = maxSumRollScoreValue;
        this.rollScores = new ArrayList<>((totalFrames * rollsPerFrame) + extrasRoll);
        this.scores = new ArrayList<>(totalFrames);
    }

    public void addRollScore(String newPinFalls) {
        this.addRollScore(new RollScore(newPinFalls));
    }

    public void addRollScore(RollScore rollScore) {
        validTotalRollScoreValue(rollScore);
        rollScores.add(rollScore);
    }

    private void validTotalRollScoreValue(RollScore rollScore) {
        totalRollValue += rollScore.getValue();
        if(totalRollValue > maxSumRollScoreValue){
            throw new RuntimeException(String.format("%s player with exceeded roll score",this.name));
        }
    }

    public void addScore(int index, int totalScore) {
        this.scores.add(index, totalScore);
    }
}
