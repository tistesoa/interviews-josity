package com.jobsity.domain.handler.impl;

import com.jobsity.domain.BowlingScoreCalculationGame;
import com.jobsity.domain.handler.ScoreHandler;
import com.jobsity.domain.vo.Player;

import java.io.OutputStream;
import java.util.Collection;

public class ScoreOutputHandler implements ScoreHandler {
    private static final String PINFALLS = "Pinfalls";
    private static final String SCORE = "Score";
    private static final String FRAME = "Frame";
    private static final String LINE_BREAK = "\n";
    private static final String TAB = "\t";
    public static final String STRIKE = "X";
    public static final String SPARE = "/";

    private final int maxScore;
    private final int totalFrames;
    public ScoreOutputHandler(int totalFrames, int maxScore) {
        this.totalFrames = totalFrames;
        this.maxScore = maxScore;
    }
    @Override
    public void loadResult(OutputStream outputStream, Collection<Player> playerList) {
        StringBuilder builder = new StringBuilder();
        buildTopFrame(builder);
        buildBodyFrame(playerList, builder);
        try {
            outputStream.write(builder.toString().getBytes());
            outputStream.close();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    private void buildBodyFrame(Collection<Player> playerList, StringBuilder builder) {
        for (Player player : playerList) {
            builder.append(LINE_BREAK);
            builder.append(player.getName());
            buildPinfallsView(builder, player);
            buildScoreView(builder, player);
        }
    }

    private void buildTopFrame(StringBuilder builder) {
        builder.append(FRAME);
        for (int i = 1; i <= this.totalFrames; i++) {
            builder.append(TAB);
            builder.append(TAB);
            builder.append(i);
        }
    }

    private void buildScoreView(StringBuilder builder, Player player) {
        builder.append("\n");
        builder.append(SCORE);
        for (int i = 0; i < player.getScores().size(); i++) {
            builder.append(TAB);
            builder.append(TAB);
            builder.append(player.getScores().get(i));
        }
    }

    private void buildPinfallsView(StringBuilder builder, Player player) {
        builder.append(LINE_BREAK);
        builder.append(PINFALLS);
        for (int i = 0; i < player.getRollScores().size(); i++) {
            builder.append(TAB);
            if (player.getRollScores().get(i).getValue() == this.maxScore) {
                if (i < player.getRollScores().size() - 3) {
                    builder.append(TAB);
                }
                builder.append(STRIKE);
            } else {
                if (player.getRollScores().get(i).isFailure()) {
                    builder.append(BowlingScoreCalculationGame.FAILURE_CHAR);
                } else {
                    builder.append(player.getRollScores().get(i).getValue());
                }
                builder.append(TAB);
                if (player.getRollScores().get(i).getValue() + player.getRollScores().get(i + 1).getValue() == this.maxScore) {
                    builder.append(SPARE);
                }else {
                    builder.append(player.getRollScores().get(i + 1).getValue());
                }
                i++;
            }

        }
    }


}
