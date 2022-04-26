package Model;

public class IdGenerator {
    private static int counter = 1;

    public static synchronized int getNextId() {
        return counter++;
    }

}
