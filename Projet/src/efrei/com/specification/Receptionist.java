package efrei.com.specification;

/**
 * The receptionist
 */
public class Receptionist extends HospitalStaff {
    private int count;

    public void checkResources() {
        // Call the resource provider
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    private void computeWaitingTime() {

    }
}
