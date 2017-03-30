package efrei.com.specification;

/**
 * Hospital class which all hospital staff inherit from
 */
public class HospitalStaff extends Person {
    protected boolean isAvailable;

    protected Patient patient;

    public Patient getPatient() {
        return patient;
    }

    public void setPatient(Patient patient) {
        setAvailable(false);
        this.patient = patient;
    }

    public boolean isAvailable() {
        return isAvailable;
    }

    public void setAvailable(boolean available) {
        isAvailable = available;
    }
}
