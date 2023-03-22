package techcourse.fp.mission;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.Arrays;
import java.util.List;
import org.junit.jupiter.api.Test;

class CalculatorTest {
    
    private final List<Integer> numbers = Arrays.asList(1, 2, 3, 4, 5, 6);
    
    @Test
    public void sumAll() {
        int sum = Calculator.sumAll(this.numbers);
        assertThat(sum).isEqualTo(21);
    }
    
    @Test
    public void sumAllEven() {
        int sum = Calculator.sumAllEven(this.numbers);
        assertThat(sum).isEqualTo(12);
    }
    
    @Test
    public void sumAllOverThree() {
        int sum = Calculator.sumAllOverThree(this.numbers, (number) -> number > 3);
        assertThat(sum).isEqualTo(15);
    }
}
