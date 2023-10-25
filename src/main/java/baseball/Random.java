package baseball;

import camp.nextstep.edu.missionutils.Randoms;
import java.util.ArrayList;
import java.util.List;

public class Random {

    public Random() {
    }

    public static String pickRandomNumberInDigit(int size) {
        List<Integer> randomNumbers = pickRandomNumbers(size);
        String randomNumber = integerListToString(randomNumbers);
        return randomNumber;
    }

    private static List<Integer> pickRandomNumbers(int size) {
        List<Integer> randomNumbers = new ArrayList<>();
        while (randomNumbers.size() < size) {
            int randomNumber = Randoms.pickNumberInRange(1, 9);
            if (!randomNumbers.contains(randomNumber)) {
                randomNumbers.add(randomNumber);
            }
        }
        return randomNumbers;
    }

    private static String integerListToString(List<Integer> numbers) {
        String result = "";
        for (int i = 0; i < numbers.size(); i++) {
            result += String.valueOf(numbers.get(i));
        }
        return result;
    }
}
