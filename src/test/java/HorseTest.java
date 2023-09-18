import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.function.Executable;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.mockito.MockedStatic;
import org.mockito.Mockito;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class HorseTest {

    @Test
    public void constructor_NullNameParamPassed_ThrowsIllegalArgumentException() {
       Throwable exception = assertThrows(IllegalArgumentException.class, () -> new Horse(null, 22, 3));

        assertEquals("Name cannot be null.", exception.getMessage());
    }

    @ParameterizedTest
    @ValueSource(strings = {" ", "  ", "\n", "\n\n", "\t", "\t\t", "\t \t"})
    public void constructor_EmptyNameParamPassedThrowsIllegalArgumentException(String string) {
        Throwable exception = assertThrows(IllegalArgumentException.class, () -> new Horse(string, 22, 3));

        assertEquals("Name cannot be blank.", exception.getMessage());
    }

    @Test
    public void constructor_NegativeSpeedParamPassedThrowsIllegalArgumentException() {
        String name = "test";
        double speed = -5;
        double distance = 2;

        Throwable exception = assertThrows(IllegalArgumentException.class, () -> new Horse(name, speed, distance));

        assertEquals("Speed cannot be negative.", exception.getMessage());
    }

    @Test
    public void constructor_NegativeDistanceParamPassedThrowsIllegalArgumentException() {
        String name = "test";
        double speed = 2;
        double distance = -5;

        Throwable exception = assertThrows(IllegalArgumentException.class, () -> new Horse(name, speed, distance));

        assertEquals("Distance cannot be negative.", exception.getMessage());
    }

    @Test
    void getName_ReturnCorrectName() {
        String name = "name";
        double speed = 2;
        double distance = 3;
        Horse horse = new Horse(name, speed, distance);

        String actual = horse.getName();

        assertEquals(name, actual);
    }

    @Test
    void getName_ReturnCorrectSpeed() {
        String name = "name";
        double speed = 2;
        double distance = 3;
        Horse horse = new Horse(name, speed, distance);

        double actual = horse.getSpeed();

        assertEquals(speed, actual);
    }

    @Test
    void getName_ReturnCorrectDistance() {
        String name = "name";
        double speed = 3;
        double distance = 4;
        Horse horse = new Horse(name, speed, distance);

        double actual = horse.getDistance();

        assertEquals(distance, actual);
    }

    @Test
    void move_CallsGetRandomDoubleParam() {
        try(MockedStatic<Horse> horseMockedStatic = Mockito.mockStatic(Horse.class)){
            Horse horse = new Horse("name", 2 , 3);

            horse.move();

            horseMockedStatic.verify(() -> Horse.getRandomDouble(0.2, 0.9));
        }
    }

    @ParameterizedTest
    @ValueSource(doubles = {0.2, 0.3, 0.4, 0.5, 0.6, 0.7, 0.8, 0.9, 0, 150})
    public void moveGetRandomDoubleCorrectOddParamsToCalculate(double fakeValue) {
        double min = 0.2;
        double max = 0.9;
        String name = "name";
        double speed = 2.5;
        double distance = 25;

        Horse horse = new Horse(name, speed, distance);

        double valueDistance = distance + speed * fakeValue;

        try (MockedStatic<Horse> horseMockedStatic = Mockito.mockStatic(Horse.class)){
                horseMockedStatic.when(() -> Horse.getRandomDouble(min, max)).thenReturn(fakeValue);
                horse.move();
        }
        assertEquals(valueDistance, horse.getDistance());
    }

}