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
    private static int MAX_WAITING_TIME = 2;
    private boolean paper = false;
    private boolean nurseFilled = false;
    private boolean examined = false;
    private boolean enteredEmergencyRoom = false;
    private boolean enteredExaminingRoom = false;
    private boolean wait = false;
    private static int counter;
    private int id;

    public boolean isWait() {
        return wait;
    }

    public void setWait(boolean wait) {
        this.wait = wait;
    }

    public boolean isEnteredEmergencyRoom() {
        return enteredEmergencyRoom;
    }

    public void setEnteredEmergencyRoom(boolean enteredEmergencyRoom) {
        this.enteredEmergencyRoom = enteredEmergencyRoom;
    }

    public boolean isEnteredExaminingRoom() {
        return enteredExaminingRoom;
    }

    public void setEnteredExaminingRoom(boolean enteredExaminingRoom) {
        this.enteredExaminingRoom = enteredExaminingRoom;
    }

    public boolean isExamined() {
        return examined;
    }

    public void setExamined(boolean examined) {
        this.examined = examined;
    }

    public ExaminingRoom getExaminingRoom() {
        return examiningRoom;
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
        nurse.setPatient(this);
        this.nurse = nurse;
    }

    public Physician getPhysician() {
        return physician;
    }

    public void setPhysician(Physician physician) {
        physician.setPatient(this);
        this.physician = physician;
    }

    public EmergencyRoom getEmergencyRoom() {
        return emergencyRoom;
    }

    public void enterEmergencyRoom(EmergencyRoom emergencyRoom) {
        // Emergency room  not available if number of patients exceeded
        if (Patient.getCounter() > Receptionist.NUMBER_OF_PATIENTS) {
            emergencyRoom.setAvailable(false);
        }

        setEnteredEmergencyRoom(true);
        this.emergencyRoom = emergencyRoom;
    }

    public void leaveEmergencyRoom() {
        // Emergency room  not available if number of patients exceeded
        if (Patient.getCounter() < Receptionist.NUMBER_OF_PATIENTS) {
            emergencyRoom.setAvailable(true);
        }
        this.emergencyRoom = null;
    }

    public void enterExaminingRoom(ExaminingRoom examiningRoom) {
        examiningRoom.setAvailable(false);
        setEnteredExaminingRoom(true);
        this.examiningRoom = examiningRoom;
    }

    public void leaveExaminingRoom() {
        this.examiningRoom.setAvailable(true);
        this.examiningRoom = null;
    }

    public int getId() {
        return id;
    }

    /**
     * Check in the hospital
     */
    public void checkIn() {
        // Set receptionist
        setReceptionist(Receptionist.getInstance());

        // +1 patient in the hospital
        counter++;
        id = counter;

        setCheckedIn(true);

        // Get the waiting time
        int waitingTime = receptionist.checkResources();

        // Waiting time too long: we won't enter the emergency room
        if (waitingTime > MAX_WAITING_TIME) {
            setWait(true);
            return;
        }

        // Enter the free room
        if (ResourceProvider.emergencyRoomAvailable()) {
            enterEmergencyRoom(ResourceProvider.getEmergencyRoomAvailable());
        }
        else {
            setWait(true);
        }
    }

    /**
     * Leave the hospital
     */
    public void checkOut() {
        leaveEmergencyRoom();
        leaveExaminingRoom();
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

        // Get an available nurse
        if (ResourceProvider.nurseAvailable()) {
            setNurse(ResourceProvider.getNurseAvailable());
        }

        // Then the nurse fills the paper
        nurse.processPaperwork();

        // Enter the free room
        if (ResourceProvider.examiningRoomAvailable()) {
            enterExaminingRoom(ResourceProvider.getExaminingRoomAvailable());

            // Get a doctor
            if (ResourceProvider.physicianAvailable()) {
                setPhysician(ResourceProvider.getPhysicianAvailable());
            }
            else {
                setWait(true);
            }
        }
        else {
            setWait(true);
        }
    }
}
