package efrei.com.specification;

import java.util.ArrayList;
import java.util.List;

/**
 * List of all physicians
 */
public class PhysicianContainer {
    private static List<Physician> physicianList  = new ArrayList<>();

    public static List<Physician> getPhysicianList() {
        return physicianList;
    }

    public static void setPhysicianList(List<Physician> physicianListArg) {
        physicianList = physicianListArg;
    }

    public static void add(Physician p) {
        physicianList.add(p);
    }
}
