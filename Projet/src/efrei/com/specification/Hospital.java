package efrei.com.specification;

import java.util.Random;

/**
 * Created by Adrien on 28/03/2017.
 */
public class Hospital {
    public static void main(String[] args) {
        System.out.println("Welcome to the hospital");

        Patient p;
        Nurse nurse = new Nurse();
        Physician physician = new Physician();
        EmergencyRoom emergencyRoom = new EmergencyRoom();
        ExaminingRoom examiningRoom = new ExaminingRoom();

        emergencyRoom.setAvailable(true);
        examiningRoom.setAvailable(true);
        nurse.setAvailable(true);
        physician.setAvailable(true);

        NurseContainer.add(nurse);
        PhysicianContainer.add(physician);
        EmergencyRoomContainer.add(emergencyRoom);
        ExaminingRoomContainer.add(examiningRoom);

        // Create 10 patients randomly
        for(int i = 0; i < 10; i++) {
            p = new Patient();
            p.checkIn();

            // Fill paper
            if (randInt(1, 10) > 5) {
                p.fillPaper();
            }

            // Treat if paper filled
            if (randInt(1, 10) > 5 && p.isPaper() && !p.isWait()) {
                physician.examineTreatPatient();
            }

            // Check out if treated
            if (randInt(1, 10) > 5 && p.isPaper() && p.isExamined() && !p.isWait()) {
                p.checkOut();
            }

            PatientContainer.add(p);
        }



        // Let's draw the roster
        roster();
    }

    public static void roster() {
        String leftAlignFormat = "| %-10s | %-12s | %-15s | %-7s | %-7s | %-15s | %-10s | %-7s |%n";
        String emergencyRoom, examiningRoom, physician, nurse, paper, checkedIn, waiting;

        System.out.format("+------------+--------------+-----------------+---------+---------+-----------------+------------+---------+%n");
        System.out.format("| Patient ID | Receptionist | Emergency room  | Paper   | Nurse   | Examining room  | Physician  | Waiting |%n");
        System.out.format("+------------+--------------+-----------------+---------+---------+-----------------+------------+---------+%n");
        for (Patient p : PatientContainer.getPatientList()) {
            // Was he admitted in an emergency room?
            emergencyRoom = (!p.isEnteredEmergencyRoom()) ? "X" : "O";
            // Was he admitted in an emergency room?
            examiningRoom = (!p.isEnteredExaminingRoom()) ? "X" : "O";
            // Does he have a physician?
            physician = (!p.isExamined()) ? "X" : "O";
            // Does he have a paper filled by the nurse?
            nurse = (!p.isNurseFilled()) ? "X" : "O";
            // Does he have a paper filled by the himself?
            paper = (!p.isPaper()) ? "X" : "O";
            // Is he checked in?
            checkedIn = (!p.isCheckedIn()) ? "X" : "O";
            // Is he waiting?
            waiting = (!p.isWait()) ? "X" : "O";
            System.out.format(leftAlignFormat, p.getId(), checkedIn, emergencyRoom, paper, nurse, examiningRoom, physician, waiting);
        }
        System.out.format("+------------+--------------+-----------------+---------+---------+-----------------+------------+---------+%n");
    }

    /**
     * Generate a random number
     * @param min int
     * @param max int
     * @return int
     */
    public static int randInt(int min, int max) {

        // Usually this can be a field rather than a method variable
        Random rand = new Random();

        // nextInt is normally exclusive of the top value,
        // so add 1 to make it inclusive
        int randomNum = rand.nextInt((max - min) + 1) + min;

        return randomNum;
    }
}
