import java.io.*;
import java.util.ArrayList;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.util.Iterator;
import java.util.Random;


public class Deanery {
    private ArrayList<Student> students = new ArrayList<Student>();
    private ArrayList<Group> groups = new ArrayList<Group>();


    public ArrayList<Student> getStudents()
    {
        return this.students;
    }

    public ArrayList<Group> getGroups()
    {
        return this.groups;
    }
    public void importStudents (String filename){
        int id = 1;
        JSONParser parser = new JSONParser();
        try{
            Object obj=parser.parse(new FileReader(filename));
            JSONObject j = (JSONObject) obj;
            JSONArray staff=(JSONArray)j.get("students");
            Iterator studentsItr=staff.iterator();
            while(studentsItr.hasNext()){
                JSONObject str=(JSONObject)studentsItr.next();
                String fio = str.get("fio").toString();
                Student.addStudent(id++, fio, this);
            }
        }
        catch(FileNotFoundException ex){
            System.out.println(ex.getMessage());
        }
        catch(IOException ex) {
            System.out.println(ex.getMessage());
        }
        catch (ParseException ex) {
            System.out.println(ex.getMessage());
        }
    }

    public void importGroup (String filename){
        int count = 0;
        int i = 0;
        int k = 0;
        JSONParser parser = new JSONParser();
        try{
            Object obj=parser.parse(new FileReader(filename));
            JSONObject j = (JSONObject) obj;
            JSONArray grp=(JSONArray)j.get("groups");
            Iterator groupIterator=grp.iterator();
            while(groupIterator.hasNext()){
                count+=10;
                JSONObject group=(JSONObject)groupIterator.next();
                String title = group.get("title").toString();
                Group.addNewGroup(title, this);
                for (;i<count;i++){
                    groups.get(k).addStudent(students.get(i));
                }
                k++;

            }
        }
        catch(FileNotFoundException ex){
            System.out.println(ex.getMessage());
        }
        catch(IOException ex) {
            System.out.println(ex.getMessage());
        }
        catch (ParseException ex) {
            System.out.println(ex.getMessage());
        }
    }

    public void addRandomMarks(){
        Random random = new Random();
        for(Student st: students){
            for(int i = 0; i<10; i++){
                int mark =random.nextInt(4)+2;
                st.addMark(mark);
            }
        }
    }

    public void addGroupHead(){
        for (Group g: groups){
            float maxAverage = 0;
            for(Student st: g.getStaff()){
                if (st.avrgGradeCalc()>maxAverage){
                    maxAverage = st.avrgGradeCalc();
                }
            }
            for(Student st: g.getStaff()){
                if(st.avrgGradeCalc() == maxAverage){
                    g.addHead(st);
                }
            }
        }
    }

    public void studentTransfer(int id, String title){
        for (Student s: students){
            if (s.getID()== id){
                for(Group gr: groups){
                    if(gr.getTitle().equals(title))
                        s.addToGroup(gr);
                }
            }
        }
        for(Group g: groups){
            if(g.searchStudent(id) !=null)
                for(Group gr: groups){
                    if(gr.getTitle().equals(title)){
                        gr.addStudent(g.searchStudent(id));
                        g.removeStudent(g.searchStudent(id));
                    }

                }

        }

    }

    public void academicFailure (){
        int id;
        for (int i = 0; i<students.size();i++){
            if (students.get(i).avrgGradeCalc()<3.0){
                id=students.get(i).getID();
                for (Group g: groups){
                    for (int j = 0; j<g.getStaff().size();j++){
                        if(g.getStaff().get(j).getID() == id){
                            g.getStaff().remove(j);
                        }
                    }
                }
                students.remove(i);
            }
        }
    }

    public void writeToFile (String filename){
        try {
            FileWriter writer = new FileWriter(filename);
            JSONArray gr = new JSONArray();
            for (Group g: groups){
                JSONObject obj1 = new JSONObject();
                obj1.put("title", g.getTitle());
                obj1.put("head", g.getHead().getFIO());
                obj1.put("Group average mark", g.avrgGroupMark());
                JSONArray st = new JSONArray();
                for (Student s: g.getStaff()){
                    JSONObject obj2 = new JSONObject();
                    obj2.put("id", s.getID());
                    obj2.put("fio", s.getFIO());
                    obj2.put("gruop", s.getGroup().getTitle());
                    JSONArray mr = new JSONArray();
                    for(Integer m: s.getMarks()){
                        mr.add(m);
                    }
                    obj2.put("marks", mr);
                    st.add(obj2);
                }
                obj1.put("stuff", st);
                gr.add(obj1);
            }
            writer.write(gr.toJSONString());
            writer.flush();
            writer.close();
        }
        catch(FileNotFoundException ex){
            System.out.println(ex.getMessage());
        }
        catch(IOException ex) {
            System.out.println(ex.getMessage());
        }

    }


    public void printInfo (){
        System.out.println("Students:");
        for (Student s: students){
            System.out.println("id:"+s.getID());
            System.out.println("fio: "+s.getFIO());
            System.out.println("group title: "+s.getGroup().getTitle());
            System.out.print("Marks: ");
            for (int i = 0; i<s.getMarks().size(); i++){
                System.out.print(s.getMarks().get(i)+" ");
            }
            System.out.println();
        }
        System.out.println("xxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx");
        for (Group g: groups){
            System.out.println("Group title:"+g.getTitle());
            System.out.println("Group "+g.getTitle() + " head: "+ g.getHead().getFIO());
            System.out.printf("Group %s average mark: %.2f\n", g.getTitle(),g.avrgGroupMark());
            for (Student s: g.getStaff()){
                System.out.println("id:"+s.getID());
                System.out.println("fio: "+s.getFIO());
                System.out.print("Marks: ");
                for (int i = 0; i<s.getMarks().size(); i++){
                    System.out.print(s.getMarks().get(i)+" ");
                }
                System.out.println();
            }
            System.out.println("__________________________________________________________");
        }
    }
}