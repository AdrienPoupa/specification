package efrei.com.specification.tests;

import com.sun.media.sound.EmergencySoundbank;
import efrei.com.specification.EmergencyRoom;
import efrei.com.specification.Patient;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

/**
 * Unit tests
 */
public class UnitTests {
    private Patient patient;
    private EmergencyRoom emergencyRoom;

    @Before
    public void setUp() throws Exception {
        patient = new Patient();
        emergencyRoom = new EmergencyRoom();
    }

    @After
    public void tearDown() throws Exception {
        patient = null;
        emergencyRoom = null;
    }

    @Test
    public void testName() {
        patient.setName("Test");
        Assert.assertEquals("Test", "Test", patient.getName());
    }

    @Test
    public void testEmergencyRoom() {
        patient.enterEmergencyRoom(emergencyRoom);
        Assert.assertEquals(patient.getEmergencyRoom(), emergencyRoom);
    }
}
