public class Student extends Person {

    private int studentID;
    private double studentGPA;
    private Department studentDepartment;

    // Constructor
    public Student(String newName,  int newAge,   int newStudentID,  double newStudentGPA,  Department newDepartment) {

        super(newName, newAge);

        studentID = newStudentID;
        studentGPA = newStudentGPA;
        studentDepartment = newDepartment;
    }

    // Getters
    public int getStudentID() {
        return studentID;
    }

    public double getStudentGPA() {
        return studentGPA;
    }

    public Department getStudentDepartment() {
        return studentDepartment;
    }

    // Polymorphism (Method Overriding)
    @Override
    public void displayInfo() {

        System.out.println("Student Name: " + getPersonName());
        System.out.println("Age: " + getPersonAge());
        System.out.println("Student ID: " + studentID);
        System.out.println("GPA: " + studentGPA);
        System.out.println("Department: "
                           + studentDepartment.getDeptName());
    }
}
