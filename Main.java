public class Main {
    public static void main(String[] args) {
        
        // Create Department
        Department dept = new Department("Computer Science");
        try {

            // Create Student
            Student student = new Student(
                    "manar",
                    20,
                    12345,
                    3.8,
                    dept
            );

            // Student Information Display
            student.displayInfo();

        }
        catch(InvalidGPAException e){

            System.out.println(e.getMessage());
        }
    }
}
