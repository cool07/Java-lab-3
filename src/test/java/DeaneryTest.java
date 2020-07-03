import org.junit.Test;

import static org.junit.Assert.*;

public class DeaneryTest {

    @Test
    public void studentTransfer() {
        Deanery deanery = new Deanery();
        Student st1 = new Student(1,"Иванов Иван");
        Student st2 = new Student(2,"Петров Петр");
        Group gr1 = new Group("g001");
        Group gr2 = new Group("g002");
        deanery.getGroups().add(gr1);
        deanery.getGroups().add(gr2);
        deanery.getGroups().get(deanery.getGroups().indexOf(gr1)).addStudent(st1);
        deanery.getGroups().get(deanery.getGroups().indexOf(gr2)).addStudent(st2);
        deanery.studentTransfer(1,"g002");
        String actual = deanery.getGroups().get(deanery.getGroups().indexOf(gr2)).getStaff().get(deanery.getGroups().get(deanery.getGroups().indexOf(gr2)).getStaff().indexOf(st1)).getFIO();
        String expected = "Иванов Иван";
        assertEquals(actual,expected);
    }
}