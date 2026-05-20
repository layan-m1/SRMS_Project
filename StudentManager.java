import java.util.ArrayList;

public class StudentManager {
    
    // List to store students
    private final ArrayList<Student> studentList;

    // Constructor
    public StudentManager() {
        studentList = new ArrayList<>();
    }

    // Add new student
    public void addStudent(Student student) {
        studentList.add(student);
        System.out.println("Student added successfully!");
    }

    // Save student data to file
    public void saveToFile() {
        System.out.println("Saving data to file...");
    }

    // Generate student report
    public void generateReport(String criteria) {
        System.out.println("\n Report for: " + criteria );
        if (studentList.isEmpty()) {
            System.out.println("No students found.");
            return;
        }
        
        for (Student s : studentList) {
            if (criteria.equalsIgnoreCase("All") || s.getStudentDepartment().getDeptName().equalsIgnoreCase(criteria)) {
                s.displayInfo();
                System.out.println("----------------------");
            }
        }
    }
}