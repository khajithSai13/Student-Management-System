
public class Student {

    private int id;
    private String name;
    private String rollNumber;
    private String department;
    private double marks;

    public Student(String name, String rollNumber,
                   String department, double marks) {
        this.name = name;
        this.rollNumber = rollNumber;
        this.department = department;
        this.marks = marks;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getRollNumber() {
        return rollNumber;
    }

    public String getDepartment() {
        return department;
    }

    public double getMarks() {
        return marks;
    }
}
