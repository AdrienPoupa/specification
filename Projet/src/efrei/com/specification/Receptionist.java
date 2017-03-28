package efrei.com.specification;

/**
 * The receptionist
 */
public class Receptionist extends HospitalStaff {
    private static int NUMBER_OF_PATIENTS = 10;

    public int checkResources() {
        // Call the resource provider: is there an available emergency room?
        boolean roomAvailable = ResourceProvider.emergencyRoomAvailable();

        // Compute waiting time
        return computeWaitingTime(roomAvailable);
    }

    private int computeWaitingTime(boolean roomAvailable) {
        int waitingTime = 0;

        if (Patient.getCounter() > NUMBER_OF_PATIENTS) {
            waitingTime += 1;
        }

        // if number of current patients > ... and room available
        // waiting
        if (roomAvailable){
            waitingTime += 1;
        }

        return waitingTime;
    }
}
