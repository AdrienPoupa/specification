package efrei.com.specification;

/**
 * Created by Adrien on 28/03/2017.
 */
public class ServiceResponsable extends HospitalStaff {
    public void askForMoreResource() {
        ResourceProvider.offerResource();
    }

    public void releaseResource() {
        ResourceProvider.takeResource();
    }
}
