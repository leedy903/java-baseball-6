package baseball;

import camp.nextstep.edu.missionutils.Console;

public class Game {
    private static int gameNumberLength = 3;
    private static boolean isFinish = false;
    private static boolean isThreeStrike = false;

    public Game() {
    }

    public static void executeGame() {
        initGame();
        playGame();
        Console.close();
    }

    private static void initGame() {
        System.out.println("숫자 야구 게임을 시작합니다.");
        isFinish = false;
    }

    private static void playGame() {
        while (!isFinish) {
            playOneRound();
            checkReplay();
        }
    }

    private static void playOneRound() {
        isThreeStrike = false;
        Random random = new Random();
        String computer = random.pickRandomNumberInDigit(gameNumberLength);
        while (!isThreeStrike) {
            User user = new User();
            String userInput = user.getUserInput();
            playBaseballGame(computer, userInput);
        }
    }

    private static void playBaseballGame(String computer, String userInput) {
        int ball = countBall(computer, userInput);
        int strike = countStrike(computer, userInput);
        showResult(ball, strike);
        checkThreeStrike(strike);
    }

    private static int countBall(String computer, String user) {
        int ball = 0;
        for (int i = 0; i < gameNumberLength; i++) {
            char computerNumber = computer.charAt(i);
            for (int j = 0; j < gameNumberLength; j++) {
                char userNumber = user.charAt(j);
                if (i != j && computerNumber == userNumber) {
                    ball++;
                }
            }
        }
        return ball;
    }

    private static int countStrike(String computer, String user) {
        int strike = 0;
        for (int i = 0; i < gameNumberLength; i++) {
            char computerNumber = computer.charAt(i);
            char userNumber = user.charAt(i);
            if (computerNumber == userNumber) {
                strike++;
            }
        }
        return strike;
    }

    private static void showResult(int ball, int strike) {
        StringBuilder result = new StringBuilder();
        if (ball > 0 && strike > 0) {
            result.append(ball).append("볼 ").append(strike).append("스트라이크");
        } else if (ball > 0 && strike == 0) {
            result.append(ball).append("볼");
        } else if (strike > 0 && ball == 0) {
            result.append(strike).append("스트라이크");
        } else if (ball == 0 && strike == 0) {
            result.append("낫싱");
        }
        System.out.println(result);
    }

    private static void checkThreeStrike(int strike) {
        if (strike == gameNumberLength) {
            isThreeStrike = true;
        }
        System.out.println("3개의 숫자를 모두 맞히셨습니다! 게임 종료");
    }

    private static void checkReplay() {
        if (isThreeStrike == true) {
            System.out.println("게임을 새로 시작하려면 1, 종료하려면 2를 입력하세요.");
            User user = new User();
            String replay = user.getUserReplayInput();
            if (replay.equals("2")) {
                isFinish = true;
            }
        }
    }
}
