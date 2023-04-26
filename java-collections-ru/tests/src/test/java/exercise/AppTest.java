package exercise;

import static org.assertj.core.api.Assertions.assertThat;
import java.util.List;
import java.util.Arrays;
import java.util.ArrayList;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class AppTest {
    List<Integer> numbers1 = new ArrayList<>(Arrays.asList(1, 2, 3, 4));
    List<Integer> numbers2 = new ArrayList<>(Arrays.asList(7, 3, 10));
    @Test
    void testTake() {
        // BEGIN
        List<Integer> actual1 = App.take(numbers1, 2);
        assertThat(actual1.size()).isEqualTo(2);
        List<Integer> actual2 = App.take(numbers2, 1);
        assertThat(actual2.size()).isEqualTo(1);
        // END
    }
}
