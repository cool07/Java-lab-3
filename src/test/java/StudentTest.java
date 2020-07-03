import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.*;

public class StudentTest {

    @Test
    public void addToGroup() {
        Student st = new Student(1,"Иванов Иван");
        Group gr = new Group("g001");
        st.addToGroup(gr);
        String actual = st.getGroup().getTitle();
        String expected = gr.getTitle();
        assertEquals(actual, expected);
    }

    @Test
    public void addMark() {
        Student st = new Student(1,"Иванов Иван");
        st.addMark(3);
        st.addMark(4);
        st.addMark(5);
        Integer[] actual = st.getMarks().toArray(new Integer[st.getMarks().size()]);
        Integer[] expected = {3,4,5};
        assertArrayEquals(actual, expected );
    }

    @Test
    public void avrgGradeCalc() {
        Student st = new Student(1,"Иванов Иван");
        st.addMark(3);
        st.addMark(4);
        st.addMark(5);
        double actual = (double) st.avrgGradeCalc();
        double expected = 4.0;
        assertEquals(actual,expected,0.1);
    }
}