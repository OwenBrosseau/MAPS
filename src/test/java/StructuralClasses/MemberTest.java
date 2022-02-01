package StructuralClasses;

import junit.framework.Assert;
import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import static org.junit.Assert.*;

public class MemberTest {
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
        String filePath = System.getProperty("user.dir") + "/Tests/MemberTest";
        Member testMember = new Member("TestMember", filePath);
        testMember.addNotification(new Notification("Notification message should not matter"));
        Assert.assertEquals("1", testMember.saveToFile());
    }
}