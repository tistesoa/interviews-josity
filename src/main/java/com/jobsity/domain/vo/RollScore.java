package com.jobsity.domain.vo;

import com.jobsity.domain.BowlingScoreCalculationGame;
import lombok.Data;

@Data
public class RollScore {
    private int value;
    private boolean failure;

    public RollScore(String newPinFalls){
        if (BowlingScoreCalculationGame.FAILURE_CHAR.equals(newPinFalls)) {
            this.failure = true;
            this.value = 0;
        } else {
            this.failure = false;
            this.value = Integer.parseInt(newPinFalls);
        }
    }
}
