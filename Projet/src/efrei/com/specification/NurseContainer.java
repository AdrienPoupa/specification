package efrei.com.specification;

import java.util.ArrayList;
import java.util.List;

/**
 * List of nurses
 */
public class NurseContainer {
    private static List<Nurse> nurseList = new ArrayList<>();

    public static List<Nurse> getNurseList() {
        return nurseList;
    }

    public static void setNurseList(List<Nurse> nurseList) {
        NurseContainer.nurseList = nurseList;
    }

    public static void add(Nurse p) {
        nurseList.add(p);
    }
}
