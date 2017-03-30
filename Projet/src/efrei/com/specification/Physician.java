package efrei.com.specification;

/**
 * Created by Adrien on 28/03/2017.
 */
public class Physician extends HospitalStaff {
    private static int counter;

    private int id;

    public Physician() {
        counter++;
        id = counter;
    }

    public static int getCounter() {
        return counter;
    }

    public int getId() {
        return id;
    }

    public void examinePatient() {
        patient.setExamined(true);
    }
}
