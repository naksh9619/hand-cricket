package com.machine.coding.service;

import java.util.Random;

public class ScoreGenerator {

    public int generateScore() {
        int[] exclusion = {5};
        Random random = new Random();
        int score = random.nextInt(7 - exclusion.length);
        for (int ex: exclusion) {
            if (score < ex) {
                break;
            }
            score++;
        }
        return score;
    }
}
