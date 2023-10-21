package baseball;

import camp.nextstep.edu.missionutils.Console;
import camp.nextstep.edu.missionutils.Randoms;

import java.util.ArrayList;
import java.util.List;

public class Application {
    private static final int randomNumberLength = 3;

    public static void main(String[] args) {
        // TODO: 프로그램 구현

        boolean isFinish = false;

        System.out.println("숫자 야구 게임을 시작합니다.");

        while(!isFinish) {
            List<Integer> randomNumbers = getRandomNumbers();
            String computer = integerListToString(randomNumbers);

            boolean isStrike = false;
            while(!isStrike){
                String user = Console.readLine();

                validateUserInput(user);

                int ball = countBall(computer, user);
                int strike = countStrike(computer, user);

                if (strike == randomNumberLength) {
                    isStrike = true;
                    System.out.println("3스트라이크\n" +
                            "3개의 숫자를 모두 맞히셨습니다! 게임 종료\n" +
                            "게임을 새로 시작하려면 1, 종료하려면 2를 입력하세요.");
                    String replay = Console.readLine();
                    validateReplayRange(replay);
                    if(replay.equals("2")) {
                        isFinish = true;
                    }
                }
                else if (ball > 0 && strike > 0) {
                    System.out.println(ball + "볼 " + strike + "스트라이크");
                }
                else if (ball > 0) {
                    System.out.println(ball + "볼");
                }
                else if (strike > 0) {
                    System.out.println(strike + "스트라이크");
                }
                else if (ball == 0 && strike == 0) {
                    System.out.println("낫싱");
                }
            }
        }

        Console.close();
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

    private static String integerListToString(List<Integer> numbers) {
        String result = "";
        for(int i = 0; i < numbers.size(); i++) {
            result += String.valueOf(numbers.get(i));
        }
        return result;
    }

    private static int countBall(String computer, String user) {
        int ball = 0;
        for(int i = 0; i < randomNumberLength; i++) {
            char computerNumber = computer.charAt(i);
            for(int j = 0; j < randomNumberLength; j++) {
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
        for(int i = 0; i < randomNumberLength; i++) {
            char computerNumber = computer.charAt(i);
            char userNumber = user.charAt(i);
            if (computerNumber == userNumber) {
                strike++;
            }
        }
        return strike;
    }

    private static void validateUserInputRange(String userInput) {
        if (userInput.length() != 3) {
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

    private static void validateUserInputDistinct(String userInput) {
        List<Integer> randomNumbers = new ArrayList<>();
        String[] userNumbers = userInput.split("");
        for(int i = 0; i < userNumbers.length; i++) {
            Integer userNumber = Integer.parseInt(userNumbers[i]);
            if(randomNumbers.contains(userNumber)) {
                throw new IllegalArgumentException();
            }
            randomNumbers.add(userNumber);
        }
    }

    private static void validateUserInput(String userInput) {
        validateUserInputRange(userInput);
        validateUserInputType(userInput);
        validateUserInputDistinct(userInput);
    }

    private static void validateReplayRange(String replay) {
        if (!replay.equals("1") && !replay.equals("2")) {
            throw new IllegalArgumentException();
        }
    }

}
