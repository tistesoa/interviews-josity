package com.jobsity.domain.handler;

import com.jobsity.domain.vo.Player;

import java.io.OutputStream;
import java.util.Collection;

public interface ScoreHandler {

    void loadResult(OutputStream outputStream, Collection<Player> players);
}
