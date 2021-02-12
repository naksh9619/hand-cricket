package com.machine.coding.service;

import com.machine.coding.entities.PlayerScore;
import com.machine.coding.exception.HandCricketException;

import lombok.extern.slf4j.Slf4j;

import org.springframework.beans.factory.annotation.Autowired;

import static com.machine.coding.common.HandCricketUtils.areScoreSame;
import static com.machine.coding.common.HandCricketUtils.firstMovePlayerIsNotOneOfThePlayers;
import static com.machine.coding.common.HandCricketUtils.getNewFirstMovePlayer;
import static com.machine.coding.common.HandCricketUtils.getWinnerForMatch;
import static com.machine.coding.common.HandCricketUtils.isFirstPlayerMovingFirst;
import static com.machine.coding.common.HandCricketUtils.printScores;

@Slf4j
public class HandCricketServiceImpl implements HandCricketService {

    private ScoreGenerator scoreGenerator;

    private int maxRounds;

    private int maxThrowCount;

    @Autowired
    public HandCricketServiceImpl(ScoreGenerator scoreGenerator, int maxRounds, int maxThrowCount) {
        this.scoreGenerator = scoreGenerator;
        this.maxRounds = maxRounds;
        this.maxThrowCount = maxThrowCount;
    }

    public String playGame(String firstMovePlayerForRound, String firstPlayer, String secondPlayer) {
        if (firstMovePlayerIsNotOneOfThePlayers(firstMovePlayerForRound, firstPlayer, secondPlayer)) {
            log.error("First move player not specified correctly.");
            throw new HandCricketException("First move player not mentioned correctly.");
        }
        PlayerScore firstPlayerScore = new PlayerScore();
        PlayerScore secondPlayerScore = new PlayerScore();
        int roundCount = 1;
        while (roundCount <= this.maxRounds) {
            System.out.println("Round " + roundCount + " : " + firstMovePlayerForRound + " is batting.");
            playCurrentRound(firstMovePlayerForRound, firstPlayer, secondPlayer, firstPlayerScore, secondPlayerScore);
            firstMovePlayerForRound = getNewFirstMovePlayer(firstMovePlayerForRound, firstPlayer, secondPlayer);
            roundCount++;
        }
        if (areScoreSame(firstPlayerScore.getScore(), secondPlayerScore.getScore())) {
            System.out.println("Match Tied");
            return "TIE";
        }
        String winner = getWinnerForMatch(firstPlayer, secondPlayer, firstPlayerScore, secondPlayerScore);
        System.out.println("Game winner is: " + winner);
        return winner;
    }

    private void playCurrentRound(String firstMovePlayer, String firstPlayer, String secondPlayer,
                                  PlayerScore firstPlayerTotalScore, PlayerScore secondPlayerTotalScore) {
        int throwCount = 1;
        while (throwCount <= this.maxThrowCount) {
            int firstPlayerScoreForMove = scoreGenerator.generateScore();
            int secondPlayerScoreForMove = scoreGenerator.generateScore();
            if (areScoreSame(firstPlayerScoreForMove, secondPlayerScoreForMove)) {
                System.out.println(firstPlayer + " throws " + firstPlayerScoreForMove + ", " + secondPlayer
                        + " throws " + secondPlayerScoreForMove + ". " + firstMovePlayer + " is out.");
                break;
            }
            if (isFirstPlayerMovingFirst(firstMovePlayer, firstPlayer)) {
                addScore(firstPlayerTotalScore, firstPlayerScoreForMove);
                printScores(firstMovePlayer, firstPlayer, secondPlayer, firstPlayerScoreForMove,
                        secondPlayerScoreForMove, firstPlayerTotalScore);
            } else if (firstMovePlayer.equals(secondPlayer)) {
                addScore(secondPlayerTotalScore, secondPlayerScoreForMove);
                printScores(firstMovePlayer, firstPlayer, secondPlayer, firstPlayerScoreForMove,
                        secondPlayerScoreForMove, secondPlayerTotalScore);
            }
            throwCount++;
        }
    }

    private void addScore(PlayerScore playerScore, int scoreForRound) {
        int points = playerScore.getScore();
        playerScore.setScore(points + scoreForRound);
    }
}
