package efrei.com.specification;

/**
 * The patient
 */
public class Patient extends Person {
    private EmergencyRoom emergencyRoom;
    private ExaminingRoom examiningRoom;
    private Nurse nurse;
    private Physician physician;
    private Receptionist receptionist;
    private static int MAX_WAITING_TIME = 1;
    private boolean paper = false;
    private boolean nurseFilled = false;
    private boolean examined = false;

    public boolean isExamined() {
        return examined;
    }

    public void setExamined(boolean examined) {
        this.examined = examined;
    }

    public ExaminingRoom getExaminingRoom() {
        return examiningRoom;
    }

    public void setExaminingRoom(ExaminingRoom examiningRoom) {
        this.examiningRoom = examiningRoom;
    }

    public boolean isNurseFilled() {

        return nurseFilled;
    }

    public void setNurseFilled(boolean nurseFilled) {
        this.nurseFilled = nurseFilled;
    }

    public boolean isPaper() {
        return paper;
    }

    public void setPaper(boolean paper) {
        this.paper = paper;
    }

    public static int getCounter() {
        return counter;
    }

    private static int counter;

    public Receptionist getReceptionist() {
        return receptionist;
    }

    public void setReceptionist(Receptionist receptionist) {
        this.receptionist = receptionist;
    }

    private boolean checkedIn;

    public Nurse getNurse() {
        return nurse;
    }

    public boolean isCheckedIn() {
        return checkedIn;
    }

    public void setCheckedIn(boolean checkedIn) {
        this.checkedIn = checkedIn;
    }

    public void setNurse(Nurse nurse) {
        this.nurse = nurse;
    }

    public Physician getPhysician() {
        return physician;
    }

    public void setPhysician(Physician physician) {
        this.physician = physician;
    }

    public EmergencyRoom getEmergencyRoom() {
        return emergencyRoom;
    }

    public void enterEmergencyRoom(EmergencyRoom emergencyRoom) {
        this.emergencyRoom = emergencyRoom;
    }

    public void leaveEmergencyRoom() {
        this.emergencyRoom = null;
    }

    public void enterExaminingRoom(ExaminingRoom examiningRoom) {
        this.examiningRoom = examiningRoom;
    }

    public void leaveExaminingRoom() {
        this.examiningRoom = null;
    }

    public void checkIn() {
        // +1 patient in the hospital
        counter++;

        setCheckedIn(true);

        int waitingTime = receptionist.checkResources();

        // Waiting time too long: we won't enter the emergency room
        if (waitingTime > MAX_WAITING_TIME) {
            return;
        }

        // Enter the free room
        EmergencyRoom freeRoom = ResourceProvider.getEmergencyRoomAvailable();
        if (freeRoom != null) {
            enterEmergencyRoom(freeRoom);
        }
    }

    /**
     * Leave the hospital
     */
    public void checkOut() {
        receptionist = null;
        physician = null;
        nurse = null;
        emergencyRoom = null;
        examiningRoom = null;
        counter--;
    }

    /**
     * Fill the paper
     */
    public void fillPaper() {
        // Patient fills his paper
        setPaper(true);

        // Then the nurse fills the paper
        nurse.processPaperwork();

        // Enter the free room
        ExaminingRoom freeRoom = ResourceProvider.getExaminingRoomAvailable();
        if (freeRoom != null) {
            enterExaminingRoom(freeRoom);
        }
    }
}
