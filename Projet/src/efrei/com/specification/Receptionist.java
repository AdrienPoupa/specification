package efrei.com.specification;

/**
 * The receptionist
 * Singleton
 */
public class Receptionist extends HospitalStaff {
    private static int NUMBER_OF_PATIENTS = 10;
    private static Receptionist INSTANCE = null;

    /**
     * Private receptionist
     */
    private Receptionist() {}

    public static Receptionist getInstance()
    {
        if (INSTANCE == null)
        { 	INSTANCE = new Receptionist();
        }
        return INSTANCE;
    }

    /**
     * Check the resources
     * @return int waiting time
     */
    public int checkResources() {
        // Call the resource provider: is there an available emergency room?
        boolean roomAvailable = ResourceProvider.emergencyRoomAvailable();

        // Compute waiting time
        return computeWaitingTime(roomAvailable);
    }

    /**
     * Compute waiting time
     * @param roomAvailable bool
     * @return int waiting time
     */
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
