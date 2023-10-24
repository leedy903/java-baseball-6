package baseball;

import static camp.nextstep.edu.missionutils.test.Assertions.assertRandomNumberInRangeTest;
import static camp.nextstep.edu.missionutils.test.Assertions.assertSimpleTest;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import camp.nextstep.edu.missionutils.test.NsTest;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class ApplicationTest extends NsTest {
    @Test
    @DisplayName("게임종료 후 재시작")
    void 게임종료_후_재시작() {
        assertRandomNumberInRangeTest(
                () -> {
                    run("246", "135", "1", "597", "589", "2");
                    assertThat(output()).contains("낫싱", "3스트라이크", "1볼 1스트라이크", "3스트라이크", "게임 종료");
                },
                1, 3, 5, 5, 8, 9
        );
    }

    @Test
    @DisplayName("유저가 4자리 이상의 숫자를 입력한 경우 예외 발생")
    void moreThanTwoInputTest() {
        assertSimpleTest(() ->
                assertThatThrownBy(() -> runException("1234"))
                        .isInstanceOf(IllegalArgumentException.class)
        );
    }

    @Test
    @DisplayName("유저가 2자리 이하 숫자를 입력한 경우 예외 발생")
    void lessThanThreeInputTest() {
        assertSimpleTest(() ->
                assertThatThrownBy(() -> runException("12"))
                        .isInstanceOf(IllegalArgumentException.class)
        );
    }

    @Test
    @DisplayName("유저가 숫자가 아닌 문자를 입력한 경우 예외 발생")
    void inputAlphabetTest() {
        assertSimpleTest(() ->
                assertThatThrownBy(() -> runException("1a3"))
                        .isInstanceOf(IllegalArgumentException.class)
        );
    }

    @Test
    @DisplayName("유저가 중복된 숫자를 입력한 경우 예외 발생")
    void inputDuplicateTest() {
        assertSimpleTest(() ->
                assertThatThrownBy(() -> runException("121"))
                        .isInstanceOf(IllegalArgumentException.class)
        );
    }

    @Test
    @DisplayName("유저가 재시작시 1, 2가 아닌 문자열 입력한 경우 예외 발생")
    void replayInputTest() {
        assertSimpleTest(() ->
                assertThatThrownBy(() -> assertRandomNumberInRangeTest(
                        () -> {
                            run("135", "3");
                            assertThat(output()).contains("3스트라이크", "게임 종료");
                        }, 1, 3, 5
                ))
                        .isInstanceOf(IllegalArgumentException.class)
        );
    }

    @Override
    public void runMain() {
        Application.main(new String[]{});
    }
}
