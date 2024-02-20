package com.jobsity.domain.impl;

import com.jobsity.application.TenPinBowlingScoreCalculationApp;
import com.jobsity.domain.BowlingScoreCalculationGame;
import com.jobsity.domain.handler.PlayersHandler;
import com.jobsity.domain.handler.ScoreHandler;
import com.jobsity.domain.handler.impl.PlayersInputHandler;
import com.jobsity.domain.handler.impl.ScoreOutputHandler;
import com.jobsity.domain.vo.Player;
import lombok.Setter;

import java.io.InputStream;
import java.io.OutputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;

public class TenPinBowlingScoreCalculationGameImpl implements BowlingScoreCalculationGame {

    public static final int MAX_SCORE = 10;
    public static final int MAX_SUM_ROLL_SCORE = 120;
    public static final int MIN_SCORE_VALUE = 0;
    public static final int TOTAL_FRAMES = 10;
    public static final int ROLLS_PER_FRAME = 2;
    public static final int EXTRA_ROLL = 1;

    private Map<String, Player> playerList;

    @Setter
    private PlayersHandler inputHandler;
    @Setter
    private ScoreHandler resultHandler;

    public TenPinBowlingScoreCalculationGameImpl() {
        this.playerList = new HashMap<>();
        this.inputHandler = new PlayersInputHandler(MIN_SCORE_VALUE, MAX_SCORE, TOTAL_FRAMES, ROLLS_PER_FRAME, EXTRA_ROLL, MAX_SUM_ROLL_SCORE);
        this.resultHandler = new ScoreOutputHandler(TOTAL_FRAMES, MAX_SCORE);
    }

    @Override
    public void loadPlayers(InputStream inputStream) {
        TenPinBowlingScoreCalculationApp.logger.log(Level.INFO, "loading players");
        this.playerList = this.inputHandler.loadPlayers(inputStream);
    }


    @Override
    public void saveScore(OutputStream outputStream) {
        TenPinBowlingScoreCalculationApp.logger.log(Level.INFO, "saving score");
        this.resultHandler.loadResult(outputStream, playerList.values());
    }

    @Override
    public Map<String, Player> getPlayers() {
        return this.playerList;
    }

    @Override
    public void calculateScore() {
        calculateTenPinBowlingScore();
    }

    private void calculateTenPinBowlingScore() {
        for (Player player : playerList.values()) {
            int totalScore = MIN_SCORE_VALUE;
            int roll = 0;
            for (int frame = 1; frame <= TOTAL_FRAMES; frame++) {
                int frameScore = MIN_SCORE_VALUE;
                if (player.getRollScores().get(roll).getValue() == MAX_SCORE) {
                    //strike!!
                    frameScore = MAX_SCORE;
                    frameScore += player.getRollScores().get(roll + 1).getValue();
                    frameScore += player.getRollScores().get(roll + 2).getValue();
                    roll++;
                } else {
                    frameScore += player.getRollScores().get(roll).getValue();
                    frameScore += player.getRollScores().get(roll + 1).getValue();
                    if (frameScore == 10) {
                        //square!
                        frameScore += player.getRollScores().get(roll + 2).getValue();
                    }
                    roll = roll + 2;
                }
                totalScore += frameScore;
                player.addScore(frame - 1, totalScore);
            }
        }
    }
}
