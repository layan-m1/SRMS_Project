public class Main {
    public static void main(String[] args) {
        // Create Student Manager
        StudentManager manager = new StudentManager();
        
        // Create Department
        Department dept = new Department("Computer Science");
        // Start Auto Save
        AutoSaveThread autoSave = new AutoSaveThread(manager);
        autoSave.setDaemon(true);
        autoSave.start();
        try {

            // Create Student
            Student student = new Student(
                    "manar",
                    20,
                    12345,
                    3.8,
                    dept
            );

            // Add Student.
            manager.addStudent(student);

            // Student Information Display
            student.displayInfo();
            // Start Report Thread
            ReportThread reportTask = new ReportThread(manager, "Computer Science");
            reportTask.start();

        }
        catch (InvalidGPAException e){

            System.out.println(e.getMessage());
        }
    }
}
