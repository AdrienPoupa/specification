package efrei.com.specification;

import java.util.ArrayList;
import java.util.List;

/**
 * List of patients
 */
public class PatientContainer {
    private static List<Patient> patientList = new ArrayList<>();

    public static List<Patient> getPatientList() {
        return patientList;
    }

    public static void setPatientList(List<Patient> patientList) {
        PatientContainer.patientList = patientList;
    }

    public static void add(Patient p) {
        patientList.add(p);
    }
}
