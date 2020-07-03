public class Task3 {
    public static void main (String[] args){
        Deanery deanery = new Deanery();
        deanery.importStudents("students.json");
        deanery.importGroup("groups.json");
        deanery.addRandomMarks();
        deanery.addGroupHead();
        deanery.studentTransfer(1, "g003");
        deanery.academicFailure ();
        deanery.printInfo();
        deanery.writeToFile("result.json");
    }
}