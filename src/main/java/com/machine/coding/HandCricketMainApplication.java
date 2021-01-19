package com.machine.coding;

import com.machine.coding.service.HandCricketService;
import com.machine.coding.service.HandCricketServiceImpl;
import com.machine.coding.service.ScoreGenerator;

import java.util.Scanner;

public class HandCricketMainApplication {
    public static void main(String[] args) {
        ScoreGenerator scoreGenerator = new ScoreGenerator();

        Scanner scanner = new Scanner(System.in);
        int maxRounds = 2;
        int maxThrowCount = 6;
        /*
         * Comment L14 and L15, uncomment L19 and L20 to make number of rounds
         * and number of throws configurable
         */
//        int maxRounds = scanner.nextInt();
//        int maxThrowCount = scanner.nextInt();
        String firstPlayer = scanner.next();
        String secondPlayer = scanner.next();
        String firstMovePlayer = scanner.next();
        HandCricketService handCricketService = new HandCricketServiceImpl(scoreGenerator, maxRounds, maxThrowCount);
        String gameResult = handCricketService.playGame(firstMovePlayer, firstPlayer, secondPlayer);
        System.out.println(gameResult);
    }
}
