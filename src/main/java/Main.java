import java.util.List;
import java.util.concurrent.TimeUnit;

public class Main {

    public static void main(String[] args) throws Exception {
        List<Horse> horses = List.of(
                new Horse("name", 2.4),
                new Horse("name1", 2.5),
                new Horse("name2", 2.6),
                new Horse("name3", 2.7),
                new Horse("name4", 2.8),
                new Horse("name5", 2.9),
                new Horse("name6", 3)
        );
        Hippodrome hippodrome = new Hippodrome(horses);

        for (int i = 0; i < 100; i++) {
            hippodrome.move();
            watch(hippodrome);
            TimeUnit.MILLISECONDS.sleep(200);
        }

        String winnerName = hippodrome.getWinner().getName();
        System.out.println(winnerName + " wins!");
    }

    private static void watch(Hippodrome hippodrome) throws Exception {
        hippodrome.getHorses().stream()
                .map(horse -> ".".repeat((int) horse.getDistance()) + horse.getName())
                .forEach(System.out::println);
        System.out.println("\n".repeat(10));
    }
}
