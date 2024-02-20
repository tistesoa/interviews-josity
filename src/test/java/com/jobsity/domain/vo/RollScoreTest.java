package com.jobsity.domain.vo;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class RollScoreTest {

    @Test
    public void shouldCreateRollScoreWithNumber(){
        RollScore rollScore = new RollScore("5");
        assertEquals(5,rollScore.getValue());
    }
    @Test
    public void shouldCreateRollScoreWithFailure(){
        RollScore rollScore = new RollScore("F");
        assertEquals(0,rollScore.getValue());
    }

    @Test
    public void shouldThrowExceptionWhenValueIsNotANumberORFailure(){
        assertThrows(NumberFormatException.class, () -> new RollScore("D"));
    }
}
