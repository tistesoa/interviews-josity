package com.jobsity.domain.handler.impl;

import com.jobsity.domain.BowlingScoreCalculationGame;
import com.jobsity.domain.handler.PlayersHandler;
import com.jobsity.domain.vo.Player;

import java.io.InputStream;
import java.util.Map;
import java.util.stream.Collectors;

public class PlayersInputHandler implements PlayersHandler {
    private final int minScore;
    private final int maxScore;
    private final int totalFrames;
    private final int rollsPerFrames;
    private final int extrasRoll;
    private final int maxSumRollScore;

    public PlayersInputHandler(int minScore, int maxScore, int totalFrames, int rollsPerFrames, int extrasRoll, int maxSumRollScore){
        this.maxScore = maxScore;
        this.minScore = minScore;
        this.totalFrames = totalFrames;
        this.rollsPerFrames = rollsPerFrames;
        this.extrasRoll = extrasRoll;
        this.maxSumRollScore = maxSumRollScore;

    }
    public Map<String, Player> loadPlayers(InputStream inputStream) {
        try {
            String content = new String(inputStream.readAllBytes());
            var ret = content.lines().map(this::parseAndPopulate).collect(Collectors.toMap(Player::getName, k -> k,  (player, newValue) -> {
                player.addRollScore(newValue.getRollScores().get(0));
                return player;
            }));
            if(ret.values().size() == 0){
                throw new RuntimeException("Invalid line format. Should be like 'Name   10'");
            }
            return ret;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    protected Player parseAndPopulate(String line) {
        String[] values = validLine(line);
        Player player = new Player(values[0],totalFrames, rollsPerFrames, extrasRoll, maxSumRollScore);
        player.addRollScore(values[1]);
        return player;
    }

    protected String[] validLine(String line) {
        String[] splitLine = line.split("\\s+");
        if (splitLine.length != 2) {
            throw new RuntimeException("Invalid line format. Should be like 'Name   10'");
        }
        validRollValue(splitLine[1]);
        return splitLine;
    }

    protected void validRollValue(String roll) {
        try {
            int r = Integer.parseInt(roll);
            if (r < this.minScore || r > this.maxScore) {
                throw new RuntimeException("Invalid value range for pins knocked down. Must be between 0 to 10");
            }
        } catch (NumberFormatException e) {
            if (!roll.equals(BowlingScoreCalculationGame.FAILURE_CHAR)) {
                throw new RuntimeException("Invalid value for pins knocked down");
            }
        }
    }
}
