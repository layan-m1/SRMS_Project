public class Person {

    private String personName;
    private int personAge;

    // Constructor
    public Person(String newName, int newAge) {
        personName = newName;
        personAge = newAge;
    }

    // Getters
    public String getPersonName() {
        return personName;
    }

    public int getPersonAge() {
        return personAge;
    }

    // Display method
    public void displayInfo() {
        System.out.println("Name: " + personName);
        System.out.println("Age: " + personAge);
    }
}