package com.jobsity.domain;

import com.jobsity.domain.vo.Player;

import java.io.InputStream;
import java.io.OutputStream;
import java.util.Map;

public interface BowlingScoreCalculationGame {
    String FAILURE_CHAR = "F";

    void calculateScore();

    void loadPlayers(InputStream inputStream);
    void saveScore(OutputStream outputStream);

    Map<String, Player> getPlayers();
}
