import java.io.*;
import java.util.ArrayList;

public class FileManager {

    // Save students to file
    public void saveStudents(ArrayList<Student> students) {

        try {

            PrintWriter writer = new PrintWriter("students.txt");

            for (Student s : students) {

                writer.println(
                    s.getPersonName() + "," +
                    s.getPersonAge() + "," +
                    s.getStudentID() + "," +
                    s.getStudentGPA() + "," +
                    s.getStudentDepartment().getDeptName()
                );
            }

            writer.close();

            System.out.println("Students saved successfully.");

        } catch (IOException e) {

            System.out.println("Error saving file.");
        }
    }

    // Load students from file
    public ArrayList<Student> loadStudents() {

        ArrayList<Student> students = new ArrayList<>();

        try {

            BufferedReader reader =
                    new BufferedReader(new FileReader("students.txt"));

            String line;

            while ((line = reader.readLine()) != null) {

                String[] data = line.split(",");

                String name = data[0];
                int age = Integer.parseInt(data[1]);
                int id = Integer.parseInt(data[2]);
                double gpa = Double.parseDouble(data[3]);
                String deptName = data[4];

                Department dept = new Department(deptName);

                Student student =
                        new Student(name, age, id, gpa, dept);

                students.add(student);
            }

            reader.close();

        } catch (Exception e) {

            System.out.println("Error loading file.");
        }

        return students;
    }
}
