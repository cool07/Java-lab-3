import java.util.ArrayList;

public class Student {
    private int id;
    private String fio;
    private Group group ;
    private ArrayList<Integer> marks = new ArrayList<Integer>();


    Student(int id, String fio){
        this.id = id;
        this.fio= fio;
    }

    static void addStudent(int id, String fio, Deanery deanery){

        deanery.getStudents().add(new Student(id, fio));
    }

    public int getID(){

        return this.id;
    }

    public String getFIO()
    {
        return this.fio;
    }


    public Group getGroup()
    {
        return this.group;
    }

    public ArrayList<Integer> getMarks()
    {
        return this.marks;
    }

    public void addToGroup(Group group){

        this.group = group;
    }

    public void addMark(int mark){
        this.marks.add(mark);

    }
    public float avrgGradeCalc(){
        float sum = 0;
        for (Integer mark: marks){
            sum+=mark;
        }
        return sum/marks.size();
    }
}