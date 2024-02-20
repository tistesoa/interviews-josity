package com.jobsity.domain.handler;

import com.jobsity.domain.vo.Player;

import java.io.InputStream;
import java.util.Map;

public interface PlayersHandler {

    Map<String, Player> loadPlayers(InputStream inputStream);
}
