package com.machine.coding;

import com.machine.coding.service.HandCricketService;
import com.machine.coding.service.HandCricketServiceImpl;
import com.machine.coding.service.ScoreGenerator;

public class HandCricketMainApplication {
    public static void main(String[] args) {
        ScoreGenerator scoreGenerator = new ScoreGenerator();
        int maxRounds = 2;
        int maxThrowCount = 6;
        HandCricketService handCricketService = new HandCricketServiceImpl(scoreGenerator, maxRounds, maxThrowCount);
        handCricketService.playGame("A", "A", "B");
    }
}
