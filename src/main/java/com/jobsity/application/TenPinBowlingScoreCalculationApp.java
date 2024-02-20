package com.jobsity.application;


import com.jobsity.domain.BowlingScoreCalculationGame;
import com.jobsity.domain.impl.TenPinBowlingScoreCalculationGameImpl;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.logging.Level;
import java.util.logging.Logger;

public class TenPinBowlingScoreCalculationApp {
    public static Logger logger = Logger.getLogger(TenPinBowlingScoreCalculationApp.class.getName());
    public static void main(String[] args) {
        validInputs(args);
        try {
            logger.log(Level.INFO, "Starting score bowling calculation with input: {0}",args[0]);
            BowlingScoreCalculationGame game = new TenPinBowlingScoreCalculationGameImpl();
            game.loadPlayers(Files.newInputStream(Paths.get(args[0])));
            game.calculateScore();
            game.saveScore(Files.newOutputStream(Paths.get(args[1])));
            logger.log(Level.INFO, "Score calculated and save successfully in: {0}",args[1]);
        }catch (Exception e){
            logger.log(Level.SEVERE, e.getCause().getMessage());
//            throw new RuntimeException(e);
        }
        logger.log(Level.INFO, "finished");

    }

    public static void validInputs(String[] args){
        if(args == null || args.length < 2 || args[0] == null || args[1] == null){
            throw new RuntimeException("Must inform a valid program arguments: inputPath outputPath");

        }
    }

}