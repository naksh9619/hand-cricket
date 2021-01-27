package com.machine.coding.service;

import com.machine.coding.exception.HandCricketException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;

import static com.machine.coding.common.HandCricketUtils.*;

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
        int firstPlayerTotalScore = 0;
        int secondPlayerTotalScore = 0;
        int roundCount = 1;
        while (roundCount <= this.maxRounds) {
            System.out.println("Round " + roundCount + " : " + firstMovePlayerForRound + " is batting.");
            int scoreForRound = playCurrentRound(firstMovePlayerForRound, firstPlayer, secondPlayer);
            if (isFirstPlayerMovingFirst(firstMovePlayerForRound, firstPlayer)) {
                firstPlayerTotalScore += scoreForRound;
            } else {
                secondPlayerTotalScore += scoreForRound;
            }
            firstMovePlayerForRound = getNewFirstMovePlayer(firstMovePlayerForRound, firstPlayer, secondPlayer);
            roundCount++;
        }
        if (areScoreSame(firstPlayerTotalScore, secondPlayerTotalScore)) {
            System.out.println("Match Tied");
            return "TIE";
        }
        String winner = getWinnerForMatch(firstPlayer, secondPlayer, firstPlayerTotalScore, secondPlayerTotalScore);
        System.out.println("Game winner is: " + winner);
        return winner;
    }

    private int playCurrentRound(String firstMovePlayer, String firstPlayer, String secondPlayer) {
        int throwCount = 1;
        int firstPlayerTotalScore = 0;
        int secondPlayerTotalScore = 0;
        while (throwCount <= this.maxThrowCount) {
            int firstPlayerScoreForMove = scoreGenerator.generateScore();
            int secondPlayerScoreForMove = scoreGenerator.generateScore();
            if (areScoreSame(firstPlayerScoreForMove, secondPlayerScoreForMove)) {
                System.out.println(firstPlayer + " throws " + firstPlayerScoreForMove + ", " + secondPlayer
                        + " throws " + secondPlayerScoreForMove + ". " + firstMovePlayer + " is out.");
                break;
            }
            firstPlayerTotalScore += firstPlayerScoreForMove;
            secondPlayerTotalScore += secondPlayerScoreForMove;
            if (isFirstPlayerMovingFirst(firstMovePlayer, firstPlayer)) {
                printScores(firstMovePlayer, firstPlayer, secondPlayer, firstPlayerScoreForMove,
                        secondPlayerScoreForMove, firstPlayerTotalScore);
            } else if (firstMovePlayer.equals(secondPlayer)) {
                printScores(firstMovePlayer, firstPlayer, secondPlayer, firstPlayerScoreForMove,
                        secondPlayerScoreForMove, secondPlayerTotalScore);
            }
            throwCount++;
        }
        if (isFirstPlayerMovingFirst(firstMovePlayer, firstPlayer)) {
            return firstPlayerTotalScore;
        }
        return secondPlayerTotalScore;
    }
}
