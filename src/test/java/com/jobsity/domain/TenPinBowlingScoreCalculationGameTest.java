package com.jobsity.domain;

import com.jobsity.domain.impl.TenPinBowlingScoreCalculationGameImpl;
import com.jobsity.domain.vo.Player;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

public class TenPinBowlingScoreCalculationGameTest {
    BowlingScoreCalculationGame tenPinBowlingGame;

    @Test
    public void shouldLoadPlayerAndCalculateCorrectly() {
        var expectedJeff = ObjectMother.createJeffPlayer();
        var expectedJohn = ObjectMother.createJohnPlayer();
        this.tenPinBowlingGame = new TenPinBowlingScoreCalculationGameImpl();
        tenPinBowlingGame.loadPlayers(getClass().getClassLoader().getResourceAsStream("./positive/scores.txt"));
        tenPinBowlingGame.calculateScore();
        var result = tenPinBowlingGame.getPlayers();
        assertEquals(2, result.size());
        assertNotNull(result.get(expectedJeff.getName()));
        assertNotNull(result.get(expectedJohn.getName()));
        assertIterableEquals(expectedJohn.getRollScores(), result.get(expectedJohn.getName()).getRollScores());
        assertIterableEquals(expectedJeff.getRollScores(), result.get(expectedJeff.getName()).getRollScores());
        assertEquals(10, result.get(expectedJohn.getName()).getScores().size());
        assertEquals(10, result.get(expectedJeff.getName()).getScores().size());
        assertIterableEquals(expectedJohn.getScores(), result.get(expectedJohn.getName()).getScores());
        assertIterableEquals(expectedJeff.getScores(), result.get(expectedJeff.getName()).getScores());

    }

    @Test
    public void shouldCalculatePlayerPerfectedScoreCorrectly() {
        var expected = ObjectMother.createCarlPlayer();
        this.tenPinBowlingGame = new TenPinBowlingScoreCalculationGameImpl();
        tenPinBowlingGame.loadPlayers(getClass().getClassLoader().getResourceAsStream("./positive/perfect.txt"));
        tenPinBowlingGame.calculateScore();
        Map<String, Player> result = tenPinBowlingGame.getPlayers();
        assertEquals(1, result.values().size());
        assertNotNull(result.get(expected.getName()));
        assertIterableEquals(expected.getRollScores(), result.get(expected.getName()).getRollScores());
        assertEquals(10, result.get(expected.getName()).getScores().size());
        assertIterableEquals(expected.getScores(), result.get(expected.getName()).getScores());
    }

    @Test
    public void shouldSaveScoreCorrectly() throws IOException {
        var output = new ByteArrayOutputStream();
        this.tenPinBowlingGame = new TenPinBowlingScoreCalculationGameImpl();
        tenPinBowlingGame.loadPlayers(getClass().getClassLoader().getResourceAsStream("./positive/perfect.txt"));
        tenPinBowlingGame.calculateScore();
        tenPinBowlingGame.saveScore(output);
        assertEquals(new String(getClass().getClassLoader().getResourceAsStream("./output/perfect-result.txt").readAllBytes()),
                output.toString());

    }

    @Test
    public void shouldThrowErrorEmptyScore() {
        this.tenPinBowlingGame = new TenPinBowlingScoreCalculationGameImpl();
        var exception = assertThrows(RuntimeException.class, () -> tenPinBowlingGame.loadPlayers(getClass().getClassLoader().getResourceAsStream("./negative/empty.txt")));
        assertEquals("Invalid line format. Should be like 'Name   10'", exception.getCause().getMessage());
    }

    @Test
    public void shouldThrowErrorWithExtraScore() {
        this.tenPinBowlingGame = new TenPinBowlingScoreCalculationGameImpl();
        var exception = assertThrows(RuntimeException.class, () -> tenPinBowlingGame.loadPlayers(getClass().getClassLoader().getResourceAsStream("./negative/extra-score.txt")));
        assertEquals("Carl player with exceeded roll score", exception.getCause().getMessage());
    }

    @Test
    public void shouldThrowErrorFreeTextScore() {
        this.tenPinBowlingGame = new TenPinBowlingScoreCalculationGameImpl();
        var exception = assertThrows(RuntimeException.class, () -> tenPinBowlingGame.loadPlayers(getClass().getClassLoader().getResourceAsStream("./negative/free-text.txt")));
        assertEquals("Invalid line format. Should be like 'Name   10'", exception.getCause().getMessage());

    }

    @Test
    public void shouldThrowErrorInvalidScore() {
        this.tenPinBowlingGame = new TenPinBowlingScoreCalculationGameImpl();
        var exception = assertThrows(RuntimeException.class, () -> tenPinBowlingGame.loadPlayers(getClass().getClassLoader().getResourceAsStream("./negative/invalid-score.txt")));
        assertEquals("Invalid value for pins knocked down", exception.getCause().getMessage());

    }

    @Test
    public void shouldThrowErrorNegativeScore() {
        this.tenPinBowlingGame = new TenPinBowlingScoreCalculationGameImpl();
        var exception = assertThrows(RuntimeException.class, () -> tenPinBowlingGame.loadPlayers(getClass().getClassLoader().getResourceAsStream("./negative/negative.txt")));
        assertEquals("Invalid value range for pins knocked down. Must be between 0 to 10", exception.getCause().getMessage());
    }
}
