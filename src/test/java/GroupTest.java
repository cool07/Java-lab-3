import org.junit.Test;

import static org.junit.Assert.*;

public class GroupTest {

    @Test
    public void addStudent() {
        Student st = new Student(1,"Петров Петр");
        Group gr = new Group("g002");
        gr.addStudent(st);
        String actual = gr.getTitle();
        String expected = st.getGroup().getTitle();
        assertEquals(actual, expected);
    }

    @Test
    public void addHead() {
        Student st = new Student(1,"Иванов Иван");
        Group gr = new Group("g001");
        gr.addHead(st);
        String actual = gr.getHead().getFIO();
        String expected = st.getFIO();
        assertEquals(actual, expected);
    }

    @Test
    public void searchStudent() {
        Student st = new Student(1,"Иванов Иван");
        Group gr = new Group("g001");
        gr.addStudent(st);
        String actual = gr.searchStudent(1).getFIO();
        String expected = st.getFIO();
        assertEquals(actual, expected);
    }

    @Test
    public void avrgGroupMark() {
        Student st1 = new Student(1,"Иванов Иван");
        Student st2 = new Student(2,"Петров Петр");
        Group gr = new Group("g001");
        st1.addMark(3);
        st1.addMark(4);
        st1.addMark(5);

        st2.addMark(3);
        st2.addMark(4);
        st2.addMark(5);

        gr.addStudent(st1);
        gr.addStudent(st2);

        double actual = (double) gr.avrgGroupMark();
        double expected = 4.0;
        assertEquals(actual,expected,0.1);
    }

    @Test
    public void removeStudent() {
    }
}