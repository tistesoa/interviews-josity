package com.jobsity.domain.vo;

import com.jobsity.domain.impl.TenPinBowlingScoreCalculationGameImpl;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class PlayerTest {
    @Test
    public void shouldAddRollScoreCorrectly() {
        Player player = new Player("test", TenPinBowlingScoreCalculationGameImpl.TOTAL_FRAMES, TenPinBowlingScoreCalculationGameImpl.ROLLS_PER_FRAME, TenPinBowlingScoreCalculationGameImpl.EXTRA_ROLL, TenPinBowlingScoreCalculationGameImpl.MAX_SUM_ROLL_SCORE);
        player.addRollScore("10");
        assertEquals(10,player.getRollScores().get(0).getValue());
    }
    @Test
    public void shouldThrowErrorWhenExceededMaxValue() {
        Player player = new Player("test", TenPinBowlingScoreCalculationGameImpl.TOTAL_FRAMES, TenPinBowlingScoreCalculationGameImpl.ROLLS_PER_FRAME, TenPinBowlingScoreCalculationGameImpl.EXTRA_ROLL, TenPinBowlingScoreCalculationGameImpl.MAX_SUM_ROLL_SCORE);
        assertThrows(RuntimeException.class, () -> player.addRollScore("125"));
    }
}
