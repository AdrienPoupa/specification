package efrei.com.specification;

import java.util.ArrayList;
import java.util.List;

/**
 * List of all emergency rooms
 */
public class EmergencyRoomContainer {
    private static List<EmergencyRoom> emergencyRoomList  = new ArrayList<>();

    public static List<EmergencyRoom> getEmergencyRoomList() {
        return emergencyRoomList;
    }

    public static void setEmergencyRoomList(List<EmergencyRoom> emergencyRoomListArg) {
        emergencyRoomList = emergencyRoomListArg;
    }

    public static void add(EmergencyRoom e) {
        emergencyRoomList.add(e);
    }
}
