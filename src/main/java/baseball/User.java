package baseball;

import camp.nextstep.edu.missionutils.Console;
import java.util.ArrayList;
import java.util.List;

public class User {
    private static int gameNumberLength = 3;

    public User() {
    }

    public static String guessInput() {
        String userInput = Console.readLine();
        validateUserInput(userInput);
        return userInput;
    }

    public static String replayInput() {
        String replay = Console.readLine();
        validateReplayInput(replay);
        return replay;
    }

    private static void validateUserInput(String userInput) {
        validateUserInputLength(userInput);
        validateUserInputType(userInput);
        validateUserInputDuplicate(userInput);
    }

    private static void validateUserInputLength(String userInput) {
        if (userInput.length() != gameNumberLength) {
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
