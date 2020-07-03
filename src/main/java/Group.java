import java.util.ArrayList;

public class Group {
    private String title;
    private ArrayList<Student> staff;
    private Student head;

    Group(String title){
        this.title=title;
        this.staff=new ArrayList<Student>();
    }

    public String getTitle(){

        return this.title;
    }

    public Student getHead(){

        return this.head;
    }

    public ArrayList<Student> getStaff(){

        return this.staff;
    }

    static void addNewGroup(String title, Deanery deanery){

        deanery.getGroups().add(new Group(title));
    }

    public void addStudent(Student st){
        staff.add(st);
        st.addToGroup(this);
    }
    public void addHead(Student st){

        this.head=st;
    }

    public Student searchStudent(int id){
        for(Student st: staff) {
            if (st.getID() == id) {
                return st;
            }
        }
        return null;
    }

    public float avrgGroupMark(){
        float sum = 0;
        for(Student st: staff){
            sum+=st.avrgGradeCalc();
        }
        return sum/staff.size();

    }
    public void removeStudent(Student st){
        for(int i = 0;i<staff.size(); i++){
            if(staff.get(i).getID() == st.getID())
                staff.remove(i);
        }

    }
}