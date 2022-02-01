package StructuralClasses;

import junit.framework.Assert;
import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import static org.junit.Assert.*;

public class ForumThreadTest {
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
        String filePath = System.getProperty("user.dir") + "/Tests/ForumThreadTest";
        Member testMember = new Member("TestMember", filePath);
        ForumThread testForumThread = new ForumThread("TestTitle", "Test Message", testMember);
        Assert.assertEquals("TestMember\r\n0\r\n1\r\nTest Message\r\nTestMember", testForumThread.saveToFile());
    }
}