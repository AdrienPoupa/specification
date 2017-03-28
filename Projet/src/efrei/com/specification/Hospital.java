package efrei.com.specification;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Adrien on 28/03/2017.
 */
public class Hospital {
    static List<Patient> patientList;

    public Hospital() {
        patientList = new ArrayList<>();
    }

    public static List<Patient> getPatientList() {
        return patientList;
    }

    public void setPatientList(List<Patient> patientList) {
        this.patientList = patientList;
    }

    public static void main(String[] args) {
        System.out.println("Welcome to the hospital");

        patientList = new ArrayList<>();

        // Let's draw the roster
        roster();
    }

    public static void roster() {
        System.out.println("Each line represents a patient");
        String leftAlignFormat = "| %-15s | %-7d | %-14s  | %-10s |%n";
        String emergencyRoom;

        System.out.format("+-----------------+---------+-----------------+------------+%n");
        System.out.format("| Receptionist    | Nurse   | Emergency room  | Physician  |%n");
        System.out.format("+-----------------+---------+-----------------+------------+%n");
        for (Patient p : patientList) {
            emergencyRoom = (p.getEmergencyRoom() == null) ? "X" : "✓";
            int i = 1;
            System.out.format(leftAlignFormat, "some data" + i, i * i, emergencyRoom, i*i*i*i);
        }
        System.out.format("+-----------------+---------+-----------------+------------+%n");
    }
}
