package com.jobsity.domain;

import com.jobsity.domain.impl.TenPinBowlingScoreCalculationGameImpl;
import com.jobsity.domain.vo.Player;

public class ObjectMother {

    public static Player createJeffPlayer() {
        Player jeff = new Player("Jeff", TenPinBowlingScoreCalculationGameImpl.TOTAL_FRAMES, TenPinBowlingScoreCalculationGameImpl.ROLLS_PER_FRAME, TenPinBowlingScoreCalculationGameImpl.EXTRA_ROLL, TenPinBowlingScoreCalculationGameImpl.MAX_SUM_ROLL_SCORE);
        jeff.addRollScore("10");
        jeff.addRollScore("7");
        jeff.addRollScore("3");
        jeff.addRollScore("9");
        jeff.addRollScore("0");
        jeff.addRollScore("10");
        jeff.addRollScore("0");
        jeff.addRollScore("8");
        jeff.addRollScore("8");
        jeff.addRollScore("2");
        jeff.addRollScore("F");
        jeff.addRollScore("6");
        jeff.addRollScore("10");
        jeff.addRollScore("10");
        jeff.addRollScore("10");
        jeff.addRollScore("8");
        jeff.addRollScore("1");
        jeff.addScore(0, 20);
        jeff.addScore(1, 39);
        jeff.addScore(2, 48);
        jeff.addScore(3, 66);
        jeff.addScore(4, 74);
        jeff.addScore(5, 84);
        jeff.addScore(6, 90);
        jeff.addScore(7, 120);
        jeff.addScore(8, 148);
        jeff.addScore(9, 167);
        return jeff;
    }

    public static Player createJohnPlayer() {
        Player john = new Player("John", TenPinBowlingScoreCalculationGameImpl.TOTAL_FRAMES, TenPinBowlingScoreCalculationGameImpl.ROLLS_PER_FRAME, TenPinBowlingScoreCalculationGameImpl.EXTRA_ROLL, TenPinBowlingScoreCalculationGameImpl.MAX_SUM_ROLL_SCORE);
        john.addRollScore("3");
        john.addRollScore("7");
        john.addRollScore("6");
        john.addRollScore("3");
        john.addRollScore("10");
        john.addRollScore("8");
        john.addRollScore("1");
        john.addRollScore("10");
        john.addRollScore("10");
        john.addRollScore("9");
        john.addRollScore("0");
        john.addRollScore("7");
        john.addRollScore("3");
        john.addRollScore("4");
        john.addRollScore("4");
        john.addRollScore("10");
        john.addRollScore("9");
        john.addRollScore("0");
        john.addScore(0, 16);
        john.addScore(1, 25);
        john.addScore(2, 44);
        john.addScore(3, 53);
        john.addScore(4, 82);
        john.addScore(5, 101);
        john.addScore(6, 110);
        john.addScore(7, 124);
        john.addScore(8, 132);
        john.addScore(9, 151);
        return john;
    }

    public static Player createCarlPlayer() {
        Player carl = new Player("Carl", TenPinBowlingScoreCalculationGameImpl.TOTAL_FRAMES, TenPinBowlingScoreCalculationGameImpl.ROLLS_PER_FRAME, TenPinBowlingScoreCalculationGameImpl.EXTRA_ROLL, TenPinBowlingScoreCalculationGameImpl.MAX_SUM_ROLL_SCORE);
        carl.addRollScore("10");
        carl.addRollScore("10");
        carl.addRollScore("10");
        carl.addRollScore("10");
        carl.addRollScore("10");
        carl.addRollScore("10");
        carl.addRollScore("10");
        carl.addRollScore("10");
        carl.addRollScore("10");
        carl.addRollScore("10");
        carl.addRollScore("10");
        carl.addRollScore("10");
        carl.addScore(0, 30);
        carl.addScore(1, 60);
        carl.addScore(2, 90);
        carl.addScore(3, 120);
        carl.addScore(4, 150);
        carl.addScore(5, 180);
        carl.addScore(6, 210);
        carl.addScore(7, 240);
        carl.addScore(8, 270);
        carl.addScore(9, 300);
        return carl;
    }
}
