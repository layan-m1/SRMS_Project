import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        // Create Student Manager
        StudentManager manager = new StudentManager();
        
        // Create Department
        Department dept = new Department("Computer Science");
        
        // Start Auto Save Thread in the background
        AutoSaveThread autoSave = new AutoSaveThread(manager);
        autoSave.setDaemon(true);
        autoSave.start();
        
        Scanner scanner = new Scanner(System.in);
        System.out.println("=== Welcome to Student Record Management System ===");
        
        // Loop to keep the program running so AutoSave can work
        while (true) {
            System.out.println("\nSelect an option:");
            System.out.println("1. Add a New Student");
            System.out.println("2. Generate Department Report");
            System.out.println("3. Exit System");
            System.out.print("Enter your choice: ");
            
            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline character
            
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
                    
                    // Create and Add Student from User Input
                    Student student = new Student(name, age, id, gpa, dept);
                    manager.addStudent(student);
                    
                } catch (InvalidGPAException e) {
                    System.out.println("Error: " + e.getMessage());
                } catch (Exception e) {
                    System.out.println("Invalid input! Please try again.");
                    scanner.nextLine(); // Clear scanner buffer
                }
                
            } else if (choice == 2) {
                // Start Report Thread
                ReportThread reportTask = new ReportThread(manager, "Computer Science");
                reportTask.start();
                try {
                    reportTask.join(); // Wait for report thread to finish printing
                } catch (InterruptedException e) {
                    System.out.println("Report interrupted.");
                }
                
            } else if (choice == 3) {
                System.out.println("Exiting system... Goodbye!");
                break; // Stop the program
                
            } else {
                System.out.println("Invalid choice. Please select 1, 2, or 3.");
            }
        }
        
        scanner.close();
    }
}
