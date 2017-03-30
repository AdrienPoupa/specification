package efrei.com.specification;

/**
 * The responsible of the service
 * Singleton
 */
public class ServiceResponsable extends HospitalStaff {
    private static ServiceResponsable INSTANCE = null;

    /**
     * Private receptionist
     */
    private ServiceResponsable() {}

    /**
     * Get the instance shared among the code
     * @return ServiceResponsable current receptionist
     */
    public static ServiceResponsable getInstance()
    {
        if (INSTANCE == null) {
            INSTANCE = new ServiceResponsable();
        }
        return INSTANCE;
    }
    
    public void askForMoreResource() {
        ResourceProvider.offerResource();
    }

    public void releaseResource() {
        ResourceProvider.takeResource();
    }
}
