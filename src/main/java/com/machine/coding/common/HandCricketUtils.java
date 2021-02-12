package com.machine.coding.common;

import com.machine.coding.entities.PlayerScore;

public class HandCricketUtils {
    public static boolean firstMovePlayerIsNotOneOfThePlayers(String firstMovePlayer,
                                                              String firstPlayer, String secondPlayer) {
        return (!firstMovePlayer.equals(firstPlayer) && !firstMovePlayer.equals(secondPlayer));
    }

    public static String getNewFirstMovePlayer(String previousFirstMovePlayer,
                                               String firstPlayer, String secondPlayer) {
        if (isFirstPlayerMovingFirst(previousFirstMovePlayer, firstPlayer)) {
            return secondPlayer;
        }
        return firstPlayer;
    }

    public static boolean isFirstPlayerMovingFirst(String firstMovePlayer, String firstPlayer) {
        return (firstMovePlayer.equals(firstPlayer));
    }

    public static boolean areScoreSame(int firstPlayerScore, int secondPlayerScore) {
        return firstPlayerScore == secondPlayerScore;
    }

    public static String getWinnerForMatch(String firstPlayer, String secondPlayer,
                                           PlayerScore firstPlayerTotalScore, PlayerScore secondPlayerTotalScore) {
        return (firstPlayerTotalScore.getScore() > secondPlayerTotalScore.getScore()) ? firstPlayer : secondPlayer;
    }

    public static void printScores(String firstMovePlayer, String firstPlayer, String secondPlayer,
                                   int firstPlayerScore, int secondPlayerScore, PlayerScore totalScore) {
        System.out.println(firstPlayer + " throws " + firstPlayerScore + ", " + secondPlayer
                + " throws " + secondPlayerScore + ". " + firstMovePlayer + " score is " + totalScore.getScore());
    }
}
