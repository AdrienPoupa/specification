package efrei.com.specification;

/**
 * A nurse
 */
public class Nurse extends HospitalStaff {
    private static int counter;

    private int id;

    public Nurse() {
        counter++;
        id = counter;
        setAvailable(true);
    }

    public static int getCounter() {
        return counter;
    }

    public static void decreaseCounter() {
        counter--;
    }

    public int getId() {
        return id;
    }

    public void processPaperwork() {
        patient.setNurseFilled(true);
        setAvailable(true);
    }
}
