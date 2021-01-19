package com.machine.coding.common;

public class HandCricketUtils {
    public static boolean firstMovePlayerIsNotOneOfThePlayers(String firstMovePlayer,
                                                              String firstPlayer, String secondPlayer) {
        return (!firstMovePlayer.equals(firstPlayer) && firstMovePlayer.equals(secondPlayer));
    }

    public static String getNewFirstMovePlayer(String previousFirstMovePlayer,
                                               String firstPlayer, String secondPlayer) {
        if (previousFirstMovePlayer.equals(firstPlayer)) {
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
                                           int firstPlayerTotalScore, int secondPlayerTotalScore) {
        return (firstPlayerTotalScore > secondPlayerTotalScore) ? firstPlayer : secondPlayer;
    }
}
