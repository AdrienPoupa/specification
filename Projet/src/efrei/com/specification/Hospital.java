package efrei.com.specification;

/**
 * Created by Adrien on 28/03/2017.
 */
public class Hospital {
    public static void main(String[] args) {
        System.out.println("Welcome to the hospital");

        // Let's draw the roster
        roster();
    }

    public static void roster() {
        String leftAlignFormat = "| %-10s | %-12s | %-7s | %-7s | %-15s | %-14s  | %-10s |%n";
        String emergencyRoom, examiningRoom, physician, nurse, paper, checkedIn;

        System.out.format("+------------+--------------+---------+---------+-----------------+-----------------+------------+%n");
        System.out.format("| Patient ID | Receptionist | Paper   | Nurse   | Emergency room  | Examining room  | Physician  |%n");
        System.out.format("+------------+--------------+---------+---------+-----------------+-----------------+------------+%n");
        for (Patient p : PatientContainer.getPatientList()) {
            // Was he admitted in an emergency room?
            emergencyRoom = (p.getEmergencyRoom() == null) ? "X" : "✓";
            // Was he admitted in an emergency room?
            examiningRoom = (p.getExaminingRoom() == null) ? "X" : "✓";
            // Does he have a physician?
            physician = (p.getPhysician() == null) ? "X" : "✓";
            // Does he have a paper filled by the nurse?
            nurse = (!p.isNurseFilled()) ? "X" : "✓";
            // Does he have a paper filled by the himself?
            paper = (!p.isPaper()) ? "X" : "✓";
            // Is he checked in?
            checkedIn = (!p.isCheckedIn()) ? "X" : "✓";
            System.out.format(leftAlignFormat, p.getCounter(), checkedIn, paper, nurse, emergencyRoom, examiningRoom, physician);
        }
        System.out.format("+------------+--------------+---------+---------+-----------------+-----------------+------------+%n");
    }
}
