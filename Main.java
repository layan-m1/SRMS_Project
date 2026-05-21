import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        // 1. Initialize the Student Manager
        StudentManager manager = new StudentManager();
        
        // 2. Load previous student data from the file at startup
        manager.loadFromFile(); 
        
        // 3. Start the AutoSave background thread as a daemon
        AutoSaveThread autoSave = new AutoSaveThread(manager);
        autoSave.setDaemon(true);
        autoSave.start();
        
        Scanner scanner = new Scanner(System.in);
        System.out.println("=== Welcome to Student Record Management System ===");
        
        // Main loop for the system menu
        while (true) {
            System.out.println("\nSelect an option:");
            System.out.println("1. Add a New Student");
            System.out.println("2. Update Student GPA");
            System.out.println("3. Delete a Student");
            System.out.println("4. Generate Department Report");
            System.out.println("5. View Top Students (GPA > 4.0)");
            System.out.println("6. Exit System");
            System.out.print("Enter your choice: ");
            
            int choice = scanner.nextInt();
            scanner.nextLine(); // Clear the scanner buffer
            
            if (choice == 1) {
                try {
                    System.out.print("Enter Student Name: ");
                    String name = scanner.nextLine();
                    
                    System.out.print("Enter Student Age: ");
                    int age = scanner.nextInt();
                    
                    System.out.print("Enter Student ID: ");
                    int id = scanner.nextInt();
                    
                    System.out.print("Enter Student GPA: ");
                    double gpa = scanner.nextDouble();
                    scanner.nextLine(); // Consume newline
                    
                    System.out.print("Enter Department Name (e.g., CS, IT, IS): ");
                    String deptName = scanner.nextLine();
                    
                    Department dept = new Department(deptName);
                    Student student = new Student(name, age, id, gpa, dept);
                    manager.addStudent(student);
                    
                } catch (InvalidGPAException e) {
                    System.out.println("Error: " + e.getMessage());
                } catch (Exception e) {
                    System.out.println("Invalid input! Please try again.");
                    scanner.nextLine();
                }
                
            } else if (choice == 2) {
                try {
                    System.out.print("Enter Student ID to update: ");
                    int id = scanner.nextInt();
                    System.out.print("Enter New GPA: ");
                    double newGpa = scanner.nextDouble();
                    
                    manager.updateStudentGPA(id, newGpa);
                } catch (InvalidGPAException e) {
                    System.out.println("Error: " + e.getMessage());
                }
                
            } else if (choice == 3) {
                System.out.print("Enter Student ID to delete: ");
                int id = scanner.nextInt();
                manager.deleteStudent(id);
                
            } else if (choice == 4) {
                System.out.print("Enter Department Name for the report (or type 'All'): ");
                String targetDept = scanner.nextLine();
                
                ReportThread reportTask = new ReportThread(manager, targetDept);
                reportTask.start();
                try {
                    reportTask.join(); // Wait for the thread to finish printing
                } catch (InterruptedException e) {
                    System.out.println("Report interrupted.");
                }
                
            } else if (choice == 5) {
                // Start ReportThread with "Top" criteria for high achievers
                ReportThread reportTask = new ReportThread(manager, "Top");
                reportTask.start();
                try {
                    reportTask.join();
                } catch (InterruptedException e) {
                    System.out.println("Report interrupted.");
                }
                
            } else if (choice == 6) {
                System.out.println("Exiting system... Goodbye!");
                break;
                
            } else {
                System.out.println("Invalid choice. Please select from 1 to 6.");
            }
        }
        
        scanner.close();
    }
}