package efrei.com.specification.tests;

import efrei.com.specification.EmergencyRoom;
import efrei.com.specification.Patient;
import efrei.com.specification.Physician;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

/**
 * We test the integration between 2 classes
 */
public class IntegrationTests {
    private Patient patient;
    private Physician physician;
    private EmergencyRoom emergencyRoom;

    @Before
    public void setUp() throws Exception {
        patient = new Patient();
        physician = new Physician();
        emergencyRoom = new EmergencyRoom();
    }

    @After
    public void tearDown() throws Exception {
        patient = null;
        physician = null;
        emergencyRoom = null;
    }

    @Test
    public void testPhysician() {
        patient.setPhysician(physician);
        Assert.assertEquals(patient.getPhysician(), physician);
    }


    @Test
    public void testEmergencyRoom() {
        patient.enterEmergencyRoom(emergencyRoom);
        Assert.assertEquals(patient.getEmergencyRoom(), emergencyRoom);
    }
}
