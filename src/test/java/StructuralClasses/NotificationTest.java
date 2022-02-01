package StructuralClasses;

import org.junit.*;

import static org.junit.Assert.*;

public class NotificationTest {
    //We get this error whenever we try to run the test
    //Exception in thread "main" java.lang.NoSuchMethodError: 'org.junit.runner.Description org.junit.runner.Description.createSuiteDescription(java.lang.String, java.lang.annotation.Annotation[])'

    @BeforeClass
    public static void beforeClass() {
        System.out.println("Before class");
    }

    @Before
    public void before() {
        System.out.println("Before test");
    }

    @After
    public void after() {
        System.out.println("After test");
    }

    @Test
    public void saveToFile() {
        Notification testNotification = new Notification("Test Message");
        Assert.assertEquals("TestMessage\r\n0", testNotification.saveToFile());
    }
}