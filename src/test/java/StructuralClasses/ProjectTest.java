package StructuralClasses;

import org.junit.*;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import static org.junit.Assert.*;

public class ProjectTest {
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
    public void generateSaveString() throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yy");
        Date dueDate = sdf.parse("05/01/21");
        Date creationDate = sdf.parse(sdf.format(new Date()));
        String title = "TestTitle";
        String description = "Test Description";
        String pmName = "TestPMName";
        Project testProject = new Project(title, description, pmName, dueDate);
        testProject.addMember("TestMember");
        testProject.createTopic("TestTopic", testProject.getMember("TestMember"));
        testProject.createForumThread("TestThread", "message should not matter", testProject.getMember("TestMember"));
        testProject.addTask("TestTask", "description should not matter");
        Assert.assertEquals(("Test Description\r\nTestPMName\r\nfalse\r\n" + sdf.format(creationDate) + "\r\n05/01/21\r\n2\r\nTestPMName\r\nTestMember\r\n1\r\nTestTopic\r\n1\r\nTestThread\r\n1\r\nTestTask\r\n0\r\n0\r\n"), testProject.generateSaveString());
    }
}