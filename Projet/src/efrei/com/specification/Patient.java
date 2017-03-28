package efrei.com.specification;

/**
 * The patient
 */
public class Patient extends Person {
    private EmergencyRoom emergencyRoom;
    private Nurse nurse;
    private Physician physician;

    public Nurse getNurse() {
        return nurse;
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

    public void checkIn() {

    }

    public void checkOut() {

    }
}
