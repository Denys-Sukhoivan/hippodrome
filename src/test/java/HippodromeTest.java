import org.junit.jupiter.api.Test;
import org.mockito.MockedStatic;
import org.mockito.Mockito;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class HippodromeTest {

    @Test
    public void constructor_NullListParamPassed_ThrowIllegalArgumentException() {
        List<Horse> horses = null;
        Throwable exception = assertThrows(IllegalArgumentException.class, () -> new Hippodrome(horses));

        assertEquals("Horses cannot be null.", exception.getMessage());
    }

    @Test
    public void constructor_ListIsEmpty_ThrowIllegalArgumentException() {
        List<Horse> horses = new ArrayList<>();
        Throwable exception = assertThrows(IllegalArgumentException.class, () -> new Hippodrome(horses));

        assertEquals("Horses cannot be empty.", exception.getMessage());
    }

    @Test
    void getHorses_ReturnListWithSameParam() {
        List<Horse> horses = new ArrayList<>();
        for (int i = 0; i < 30; i++) {
            horses.add(new Horse("Horse" + i, i, i));
        }

        Hippodrome hippodrome = new Hippodrome(horses);

        assertNotNull(hippodrome.getHorses());
        assertEquals(30, hippodrome.getHorses().size());

        for (int i = 0; i < 30; i++) {
            assertEquals(hippodrome.getHorses().get(i).getName(), hippodrome.getHorses().get(i).getName());
        }
    }

    @Test
    void move_CommandMoveForAllHorsesCheckCorrectParam() {
        List<Horse> horses = new ArrayList<>();
        for (int i = 0; i < 50; i++) {
            horses.add(Mockito.mock(Horse.class));
        }

        Hippodrome hippodrome = new Hippodrome(horses);

        hippodrome.move();

        for (Horse hors : horses) {
            Mockito.verify(hors, Mockito.times(1)).move();
        }
    }

    @Test
    void getWinner_MethodCorrectReturnHorseWithLargestParamDistance() {
        Hippodrome hippodrome = new Hippodrome(List.of(
                new Horse("name1", 1, 1),
                new Horse("name2", 2, 2),
                new Horse("name3", 3, 3)
        ));

        assertEquals("name3", hippodrome.getWinner().getName());
    }
}