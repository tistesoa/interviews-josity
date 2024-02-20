package com.jobsity.domain.handler;

import com.jobsity.domain.ObjectMother;
import com.jobsity.domain.handler.impl.ScoreOutputHandler;
import com.jobsity.domain.impl.TenPinBowlingScoreCalculationGameImpl;
import com.jobsity.domain.vo.Player;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ScoreHandlerTest {

    @Test
    public void shouldSaveScoreCorrecly() throws IOException {
        var output = new ByteArrayOutputStream();
        ScoreHandler handler = new ScoreOutputHandler(TenPinBowlingScoreCalculationGameImpl.TOTAL_FRAMES, TenPinBowlingScoreCalculationGameImpl.MAX_SCORE);
        Collection<Player> listPlayers = new ArrayList<>();
        listPlayers.add(ObjectMother.createCarlPlayer());
        handler.loadResult(output, listPlayers);
        assertEquals(new String(getClass().getClassLoader().getResourceAsStream("./output/perfect-result.txt").readAllBytes()),
                output.toString());
    }
}
