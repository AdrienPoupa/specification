package efrei.com.specification.tests;

import com.sun.org.apache.regexp.internal.RE;
import efrei.com.specification.*;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

/**
 * Validation tests
 * We test the whole system: when is a patient cured?
 * We test a whole scenario
 */
public class ValidationTests {
    private Receptionist receptionist;
    private Patient patient;
    private Physician physician;
    private PhysicianContainer physicianContainer;
    private Nurse nurse;
    private NurseContainer nurseContainer;
    private EmergencyRoom emergencyRoom;
    private EmergencyRoomContainer emergencyRoomContainer;
    private ExaminingRoom examiningRoom;
    private ExaminingRoomContainer examiningRoomContainer;

    @Before
    public void setUp() throws Exception {
        receptionist = Receptionist.getInstance();
        patient = new Patient();
        physician = new Physician();
        nurse = new Nurse();
        emergencyRoom = new EmergencyRoom();
        emergencyRoomContainer = new EmergencyRoomContainer();
        examiningRoom = new ExaminingRoom();
        examiningRoomContainer = new ExaminingRoomContainer();

        emergencyRoom.setAvailable(true);
        examiningRoom.setAvailable(true);
        nurse.setAvailable(true);
        physician.setAvailable(true);

        EmergencyRoomContainer.add(emergencyRoom);
        ExaminingRoomContainer.add(examiningRoom);
        NurseContainer.add(nurse);
        PhysicianContainer.add(physician);
    }

    @After
    public void tearDown() throws Exception {
        receptionist = null;
        patient = null;
        physician = null;
        emergencyRoom = null;
        emergencyRoomContainer = null;
        examiningRoom = null;
        examiningRoomContainer = null;
    }

    @Test
    public void testCheckIn() {
        // The patient checks in
        patient.checkIn();

        // There must be 1 patient in the hospital
        Assert.assertEquals(Patient.getCounter(), 1);

        // We must have a receptionist
        Assert.assertEquals(patient.getReceptionist(), receptionist);

        // Everything is empty, make sure he is in the first emergency room
        Assert.assertEquals(patient.getEmergencyRoom().getId(), 1);
    }

    @Test
    public void testFillPaper() {
        // The patient fills the paper in
        patient.fillPaper();

        // We have the first nurse who is available
        Assert.assertEquals(patient.getNurse().getId(), 1);

        // Patient has filled the paper
        Assert.assertTrue(patient.isPaper());

        // Nurse has filled patient's paper
        Assert.assertTrue(patient.isNurseFilled());

        // Everything is empty, make sure he is in the first exam room
        Assert.assertEquals(patient.getExaminingRoom().getId(), 1);
    }
}
