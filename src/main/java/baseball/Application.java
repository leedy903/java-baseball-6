package baseball;

import camp.nextstep.edu.missionutils.Console;
import camp.nextstep.edu.missionutils.Randoms;
import java.util.ArrayList;
import java.util.List;

public class Application {
    private static final int randomNumberLength = 3;
    private static boolean isFinish = false;
    private static boolean isThreeStrike = false;

    public static void main(String[] args) {
        executeGame();
    }

    private static void executeGame() {
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
        String computer = getRandomNumber();
        while (!isThreeStrike) {
            String user = getUserInput();
            int ball = countBall(computer, user);
            int strike = countStrike(computer, user);
            showResult(ball, strike);
            checkThreeStrike(strike);
        }
    }

    private static String getRandomNumber() {
        List<Integer> randomNumbers = getRandomNumbers();
        String randomNumber = integerListToString(randomNumbers);
        return randomNumber;
    }

    private static List<Integer> getRandomNumbers() {
        List<Integer> randomNumbers = new ArrayList<>();
        while (randomNumbers.size() < randomNumberLength) {
            int randomNumber = Randoms.pickNumberInRange(1, 9);
            if (!randomNumbers.contains(randomNumber)) {
                randomNumbers.add(randomNumber);
            }
        }
        return randomNumbers;
    }

    private static String getUserInput() {
        String userInput = Console.readLine();
        validateUserInput(userInput);
        return userInput;
    }

    private static String getUserReplayInput() {
        String replay = Console.readLine();
        validateReplayInput(replay);
        return replay;
    }

    private static String integerListToString(List<Integer> numbers) {
        String result = "";
        for (int i = 0; i < numbers.size(); i++) {
            result += String.valueOf(numbers.get(i));
        }
        return result;
    }

    private static int countBall(String computer, String user) {
        int ball = 0;
        for (int i = 0; i < randomNumberLength; i++) {
            char computerNumber = computer.charAt(i);
            for (int j = 0; j < randomNumberLength; j++) {
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
        for (int i = 0; i < randomNumberLength; i++) {
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
        if (strike == randomNumberLength) {
            isThreeStrike = true;
        }
        System.out.println("3개의 숫자를 모두 맞히셨습니다! 게임 종료");
    }

    private static void checkReplay() {
        if (isThreeStrike == true) {
            System.out.println("게임을 새로 시작하려면 1, 종료하려면 2를 입력하세요.");
            String replay = getUserReplayInput();
            if (replay.equals("2")) {
                isFinish = true;
            }
        }
    }

    private static void validateUserInput(String userInput) {
        validateUserInputLength(userInput);
        validateUserInputType(userInput);
        validateUserInputDuplicate(userInput);
    }

    private static void validateUserInputLength(String userInput) {
        if (userInput.length() != randomNumberLength) {
            throw new IllegalArgumentException();
        }
    }

    private static void validateUserInputType(String userInput) {
        try {
            Integer.parseInt(userInput);
        } catch (Exception e) {
            throw new IllegalArgumentException();
        }
    }

    private static void validateUserInputDuplicate(String userInput) {
        List<Integer> randomNumbers = new ArrayList<>();
        String[] userNumbers = userInput.split("");
        for (int i = 0; i < userNumbers.length; i++) {
            Integer userNumber = Integer.parseInt(userNumbers[i]);
            if (randomNumbers.contains(userNumber)) {
                throw new IllegalArgumentException();
            }
            randomNumbers.add(userNumber);
        }
    }

    private static void validateReplayInput(String replay) {
        if (!replay.equals("1") && !replay.equals("2")) {
            throw new IllegalArgumentException();
        }
    }
}
