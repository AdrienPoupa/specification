package efrei.com.specification;

/**
 * A nurse
 */
public class Nurse extends HospitalStaff {
    private Patient patient;

    public Patient getPatient() {
        return patient;
    }

    public void setPatient(Patient patient) {
        this.patient = patient;
    }

    public void processPaperwork() {
        patient.setNurseFilled(true);
    }
}
