package efrei.com.specification.tests;

import efrei.com.specification.Patient;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

/**
 * Unit tests
 * We isolate a single class
 */
public class UnitTests {
    private Patient patient;

    @Before
    public void setUp() throws Exception {
        patient = new Patient();
    }

    @After
    public void tearDown() throws Exception {
        patient = null;
    }

    @Test
    public void testName() {
        patient.setName("Test");
        Assert.assertEquals("Test", "Test", patient.getName());
    }

    @Test
    public void testBirthDate() {
        patient.setBirthDate("01/01/1985");
        Assert.assertEquals("01/01/1985", "01/01/1985", patient.getBirthDate());
    }

    @Test
    public void testLastName() {
        patient.setLastName("Test");
        Assert.assertEquals("Test", "Test", patient.getLastName());
    }
}
