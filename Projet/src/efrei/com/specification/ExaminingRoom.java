package efrei.com.specification;

/**
 * One Emergency room
 */
public class ExaminingRoom {
    private boolean isAvailable;

    private static int counter;

    private int id;

    public ExaminingRoom() {
        counter++;
        id = counter;
        setAvailable(true);
    }

    public static int getCounter() {
        return counter;
    }

    public int getId() {
        return id;
    }

    public boolean isAvailable() {
        return isAvailable;
    }

    public void setAvailable(boolean available) {
        isAvailable = available;
    }
}
