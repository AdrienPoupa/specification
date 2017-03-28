package efrei.com.specification;

/**
 * Created by Adrien on 28/03/2017.
 */
public class Physician extends HospitalStaff {
    private boolean isAvailable;
    private Patient patient;

    public boolean isAvailable() {
        return isAvailable;
    }

    public void setAvailable(boolean available) {
        isAvailable = available;
    }

    public Patient getPatient() {
        return patient;
    }

    public void setPatient(Patient patient) {
        this.patient = patient;
    }

    public void examinePatient() {

    }
}
