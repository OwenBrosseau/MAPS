package StructuralClasses;

import junit.framework.Assert;
import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import static org.junit.Assert.*;

public class MeetingTest {
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
    public void saveToFile() throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yy");
        Date testDay = sdf.parse("10/11/20");
        String testTitle = "Test Title";
        String testDescription = "Test Description";
        Meeting testMeeting = new Meeting(testTitle, testDescription, testDay);
        Assert.assertEquals("Test Description\r\n10/11/20\r\n0\r\n0\r\n", testMeeting.saveToFile());
    }
}