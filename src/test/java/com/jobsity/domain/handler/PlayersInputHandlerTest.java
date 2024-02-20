package com.jobsity.domain.handler;

import com.jobsity.domain.ObjectMother;
import com.jobsity.domain.handler.impl.PlayersInputHandler;
import com.jobsity.domain.impl.TenPinBowlingScoreCalculationGameImpl;
import com.jobsity.domain.vo.Player;
import org.junit.jupiter.api.Test;

import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;


public class PlayersInputHandlerTest {

    private PlayersInputHandler handler;

    @Test
    public void shouldLoadPlayersCorrectly() {
        this.handler = new PlayersInputHandler(TenPinBowlingScoreCalculationGameImpl.MIN_SCORE_VALUE, TenPinBowlingScoreCalculationGameImpl.MAX_SCORE, TenPinBowlingScoreCalculationGameImpl.TOTAL_FRAMES, TenPinBowlingScoreCalculationGameImpl.ROLLS_PER_FRAME, TenPinBowlingScoreCalculationGameImpl.EXTRA_ROLL, TenPinBowlingScoreCalculationGameImpl.MAX_SUM_ROLL_SCORE);
        Map<String, Player> result = handler.loadPlayers(getClass().getClassLoader().getResourceAsStream("./positive/scores.txt"));
        assertEquals(2, result.values().size());
        assertNotNull(result.get("Jeff"));
        assertNotNull(result.get("John"));
        assertIterableEquals(ObjectMother.createJohnPlayer().getRollScores(), result.get("John").getRollScores());
        assertIterableEquals(ObjectMother.createJeffPlayer().getRollScores(), result.get("Jeff").getRollScores());
        assertEquals(0, result.get("John").getScores().size());
        assertEquals(0, result.get("Jeff").getScores().size());
    }

    @Test
    public void shouldLoadPerfectedPlayerCorrectly() {
        this.handler = new PlayersInputHandler(TenPinBowlingScoreCalculationGameImpl.MIN_SCORE_VALUE, TenPinBowlingScoreCalculationGameImpl.MAX_SCORE, TenPinBowlingScoreCalculationGameImpl.TOTAL_FRAMES, TenPinBowlingScoreCalculationGameImpl.ROLLS_PER_FRAME, TenPinBowlingScoreCalculationGameImpl.EXTRA_ROLL, TenPinBowlingScoreCalculationGameImpl.MAX_SUM_ROLL_SCORE);
        Map<String, Player> result = handler.loadPlayers(getClass().getClassLoader().getResourceAsStream("./positive/perfect.txt"));
        assertEquals(1, result.values().size());
        assertNotNull(result.get("Carl"));
        assertIterableEquals(ObjectMother.createCarlPlayer().getRollScores(), result.get("Carl").getRollScores());
        assertEquals(0, result.get("Carl").getScores().size());
    }

    @Test
    public void shouldThrowErrorEmptyScore() {
        this.handler = new PlayersInputHandler(TenPinBowlingScoreCalculationGameImpl.MIN_SCORE_VALUE, TenPinBowlingScoreCalculationGameImpl.MAX_SCORE, TenPinBowlingScoreCalculationGameImpl.TOTAL_FRAMES, TenPinBowlingScoreCalculationGameImpl.ROLLS_PER_FRAME, TenPinBowlingScoreCalculationGameImpl.EXTRA_ROLL, TenPinBowlingScoreCalculationGameImpl.MAX_SUM_ROLL_SCORE);
        var exception = assertThrows(RuntimeException.class, () -> handler.loadPlayers(getClass().getClassLoader().getResourceAsStream("./negative/empty.txt")));
        assertEquals("Invalid line format. Should be like 'Name   10'", exception.getCause().getMessage());
    }

    @Test
    public void shouldThrowErrorWithExtraScore() {
        this.handler = new PlayersInputHandler(TenPinBowlingScoreCalculationGameImpl.MIN_SCORE_VALUE, TenPinBowlingScoreCalculationGameImpl.MAX_SCORE, TenPinBowlingScoreCalculationGameImpl.TOTAL_FRAMES, TenPinBowlingScoreCalculationGameImpl.ROLLS_PER_FRAME, TenPinBowlingScoreCalculationGameImpl.EXTRA_ROLL, TenPinBowlingScoreCalculationGameImpl.MAX_SUM_ROLL_SCORE);
        var exception = assertThrows(RuntimeException.class, () -> handler.loadPlayers(getClass().getClassLoader().getResourceAsStream("./negative/extra-score.txt")));
        assertEquals("Carl player with exceeded roll score", exception.getCause().getMessage());
    }

    @Test
    public void shouldThrowErrorFreeTextScore() {
        this.handler = new PlayersInputHandler(TenPinBowlingScoreCalculationGameImpl.MIN_SCORE_VALUE, TenPinBowlingScoreCalculationGameImpl.MAX_SCORE, TenPinBowlingScoreCalculationGameImpl.TOTAL_FRAMES, TenPinBowlingScoreCalculationGameImpl.ROLLS_PER_FRAME, TenPinBowlingScoreCalculationGameImpl.EXTRA_ROLL, TenPinBowlingScoreCalculationGameImpl.MAX_SUM_ROLL_SCORE);
        var exception = assertThrows(RuntimeException.class, () -> handler.loadPlayers(getClass().getClassLoader().getResourceAsStream("./negative/free-text.txt")));
        assertEquals("Invalid line format. Should be like 'Name   10'", exception.getCause().getMessage());

    }

    @Test
    public void shouldThrowErrorInvalidScore() {
        this.handler = new PlayersInputHandler(TenPinBowlingScoreCalculationGameImpl.MIN_SCORE_VALUE, TenPinBowlingScoreCalculationGameImpl.MAX_SCORE, TenPinBowlingScoreCalculationGameImpl.TOTAL_FRAMES, TenPinBowlingScoreCalculationGameImpl.ROLLS_PER_FRAME, TenPinBowlingScoreCalculationGameImpl.EXTRA_ROLL, TenPinBowlingScoreCalculationGameImpl.MAX_SUM_ROLL_SCORE);
        var exception = assertThrows(RuntimeException.class, () -> handler.loadPlayers(getClass().getClassLoader().getResourceAsStream("./negative/invalid-score.txt")));
        assertEquals("Invalid value for pins knocked down", exception.getCause().getMessage());

    }

    @Test
    public void shouldThrowErrorNegativeScore() {
        this.handler = new PlayersInputHandler(TenPinBowlingScoreCalculationGameImpl.MIN_SCORE_VALUE, TenPinBowlingScoreCalculationGameImpl.MAX_SCORE, TenPinBowlingScoreCalculationGameImpl.TOTAL_FRAMES, TenPinBowlingScoreCalculationGameImpl.ROLLS_PER_FRAME, TenPinBowlingScoreCalculationGameImpl.EXTRA_ROLL, TenPinBowlingScoreCalculationGameImpl.MAX_SUM_ROLL_SCORE);
        var exception = assertThrows(RuntimeException.class, () -> handler.loadPlayers(getClass().getClassLoader().getResourceAsStream("./negative/negative.txt")));
        assertEquals("Invalid value range for pins knocked down. Must be between 0 to 10", exception.getCause().getMessage());
    }
}
