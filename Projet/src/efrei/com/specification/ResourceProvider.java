package efrei.com.specification;

import java.nio.Buffer;
import java.util.ArrayList;
import java.util.List;

/**
 * The resource provider
 * Buffer are not being used for now
 */
public class ResourceProvider extends HospitalStaff {
    private Buffer b0;
    private Buffer b1;

    // Create new physicians, emergency rooms
    public static void offerResource() {
        // Create a few things
        for (int i = 0; i < 3; i++) {
            PhysicianContainer.add(new Physician());
            NurseContainer.add(new Nurse());
            EmergencyRoomContainer.add(new EmergencyRoom());
            ExaminingRoomContainer.add(new ExaminingRoom());
        }
    }

    public static void takeResource() {
        // Remove all idle physicians
        List<Physician> physicianList = PhysicianContainer.getPhysicianList();
        List<Physician> physicianTrimmedList = new ArrayList<>();

        for (Physician p : physicianList) {
            if (!p.isAvailable()) {
                physicianTrimmedList.add(p);
            }
        }

        PhysicianContainer.setPhysicianList(physicianTrimmedList);

        // Remove all idle nurses
        List<Nurse> nurseList = NurseContainer.getNurseList();
        List<Nurse> nurseTrimmedList = new ArrayList<>();

        for (Nurse n : nurseList) {
            if (!n.isAvailable()) {
                nurseTrimmedList.add(n);
            } else {
                Nurse.decreaseCounter();
            }
        }

        NurseContainer.setNurseList(nurseTrimmedList);

        // Remove all idle rooms
        List<EmergencyRoom> emergencyRoomList = EmergencyRoomContainer.getEmergencyRoomList();
        List<EmergencyRoom> emergencyRoomTrimmedList = new ArrayList<>();

        for (EmergencyRoom e : emergencyRoomList) {
            if (!e.isAvailable()) {
                emergencyRoomTrimmedList.add(e);
            }
        }

        EmergencyRoomContainer.setEmergencyRoomList(emergencyRoomTrimmedList);

        // Remove all idle rooms
        List<ExaminingRoom> examiningRoomList = ExaminingRoomContainer.getExaminingRoomList();
        List<ExaminingRoom> examiningRoomTrimmedList = new ArrayList<>();

        for (ExaminingRoom e : examiningRoomList) {
            if (!e.isAvailable()) {
                examiningRoomTrimmedList.add(e);
            }
        }

        ExaminingRoomContainer.setExaminingRoomList(examiningRoomTrimmedList);
    }

    public static boolean emergencyRoomAvailable() {
        return getEmergencyRoomAvailable() != null;
    }

    public static EmergencyRoom getEmergencyRoomAvailable() {
        return EmergencyRoomContainer.getEmergencyRoomList()
                .stream()
                .filter(EmergencyRoom::isAvailable)
                .findFirst()
                .orElse(null);
    }

    public static boolean examiningRoomAvailable() {
        return getExaminingRoomAvailable() != null;
    }

    public static ExaminingRoom getExaminingRoomAvailable() {
        return ExaminingRoomContainer.getExaminingRoomList()
                .stream()
                .filter(ExaminingRoom::isAvailable)
                .findFirst()
                .orElse(null);
    }

    public static boolean physicianAvailable() {
        return getPhysicianAvailable() != null;
    }

    public static Physician getPhysicianAvailable() {
        return PhysicianContainer.getPhysicianList()
                .stream()
                .filter(Physician::isAvailable)
                .findFirst()
                .orElse(null);
    }

    public static boolean nurseAvailable() {
        return getNurseAvailable() != null;
    }

    public static Nurse getNurseAvailable() {
        return NurseContainer.getNurseList()
                .stream()
                .filter(Nurse::isAvailable)
                .findFirst()
                .orElse(null);
    }

    public Buffer getB0() {
        return b0;
    }

    public void setB0(Buffer b0) {
        this.b0 = b0;
    }

    public Buffer getB1() {
        return b1;
    }

    public void setB1(Buffer b1) {
        this.b1 = b1;
    }
}
