import java.io.*;
import java.util.ArrayList;

public class StudentManager {
    
    // List to store students
    private final ArrayList<Student> studentList;
    // Name of the database text file
    private final String FILE_PATH = "students.txt"; 

    // Constructor
    public StudentManager() {
        studentList = new ArrayList<>();
    }

    // Add new student
    public void addStudent(Student student) {
        studentList.add(student);
        System.out.println("Student added successfully!");
    }

    // Update student GPA by ID
    public void updateStudentGPA(int id, double newGPA) throws InvalidGPAException {
        if (newGPA < 0 || newGPA > 5) {
            throw new InvalidGPAException("Invalid GPA! GPA must be between 0 and 5.");
        }
        
        for (Student s : studentList) {
            if (s.getStudentID() == id) {
                System.out.println("Student found! Updating GPA...");
                
                String name = s.getPersonName();
                int age = s.getPersonAge();
                Department dept = s.getStudentDepartment();
                
                studentList.remove(s);
                studentList.add(new Student(name, age, id, newGPA, dept));
                System.out.println("Student GPA updated successfully!");
                return;
            }
        }
        System.out.println("Student with ID " + id + " not found.");
    }

    // Delete student by ID
    public void deleteStudent(int id) {
        for (Student s : studentList) {
            if (s.getStudentID() == id) {
                studentList.remove(s);
                System.out.println("Student with ID " + id + " deleted successfully!");
                return;
            }
        }
        System.out.println("Student with ID " + id + " not found.");
    }

    // Save all student records to file (Called by AutoSaveThread)
    public synchronized void saveToFile() {
        try (PrintWriter writer = new PrintWriter(new FileWriter(FILE_PATH))) {
            for (Student s : studentList) {
                writer.println(s.getPersonName() + "," + 
                               s.getPersonAge() + "," + 
                               s.getStudentID() + "," + 
                               s.getStudentGPA() + "," + 
                               s.getStudentDepartment().getDeptName());
            }
            System.out.println("\n[AutoSave] All student records saved safely to " + FILE_PATH);
        } catch (IOException e) {
            System.out.println("[AutoSave Error] Could not save data: " + e.getMessage());
        }
    }

    // Load previous student data from file at startup
    public void loadFromFile() {
        File file = new File(FILE_PATH);
        if (!file.exists()) {
            return; 
        }
        
        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] data = line.split(",");
                if (data.length == 5) {
                    String name = data[0];
                    int age = Integer.parseInt(data[1]);
                    int id = Integer.parseInt(data[2]);
                    double gpa = Double.parseDouble(data[3]);
                    Department dept = new Department(data[4]);
                    
                    studentList.add(new Student(name, age, id, gpa, dept));
                }
            }
            System.out.println("Previous student records loaded successfully!");
        } catch (Exception e) {
            System.out.println("Error loading previous data: " + e.getMessage());
        }
    }

    // Generate student report
    public void generateReport(String criteria) {
        if (studentList.isEmpty()) {
            System.out.println("No students found.");
            return;
        }
        
        if (criteria.equalsIgnoreCase("Top")) {
            System.out.println("\n=== Report for Top Students (GPA > 4.0) ===");
            boolean found = false;
            for (Student s : studentList) {
                if (s.getStudentGPA() > 4.0) {
                    s.displayInfo();
                    System.out.println("----------------------");
                    found = true;
                }
            }
            if (!found) {
                System.out.println("No students found with GPA > 4.0");
            }
        } else {
            System.out.println("\n Report for Department/Criteria: " + criteria);
            for (Student s : studentList) {
                if (criteria.equalsIgnoreCase("All") || s.getStudentDepartment().getDeptName().equalsIgnoreCase(criteria)) {
                    s.displayInfo();
                    System.out.println("----------------------");
                }
            }
        }
    }
}