
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class StudentManagement {

private static void deleteStudent(){
    System.out.print("Enter the rollNumber:");
    String id = sc.nextLine().toLowerCase();
    String sql = "DELETE from students where roll_number = ?";
    try(Connection con = DBConnection.getConnection();
        PreparedStatement ps = con.prepareStatement(sql)){
        ps.setString(1,id);
        int rows = ps.executeUpdate();
        if (rows > 0) {
            System.out.println("The student deleted Succesfuly !!");
        }else{
            System.out.println("Student doesnt exist !!");
        }

    }catch (SQLException e) {
        System.out.println("Error deleting student: " + e.getMessage());
    }

}    
private static void addStudent() {
    //sc.nextLine();

    System.out.print("Enter student name: ");
    String name = sc.nextLine();

    System.out.print("Enter roll number: ");
    String rollNumber = sc.nextLine();

    System.out.print("Enter department: ");
    String department = sc.nextLine();

    System.out.print("Enter marks: ");
    double marks = sc.nextDouble();

    String sql = "INSERT INTO students (name, roll_number, department, marks) VALUES (?, ?, ?, ?)";

    try (Connection con = DBConnection.getConnection();
         PreparedStatement ps = con.prepareStatement(sql)) {

        ps.setString(1, name);
        ps.setString(2, rollNumber);
        ps.setString(3, department);
        ps.setDouble(4, marks);

        int rows = ps.executeUpdate();

        if (rows > 0) {
            System.out.println("Student added successfully!");
        }

    } catch (SQLException e) {
        System.out.println("Error adding student: " + e.getMessage());
    }
}

    private static final Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {
        while (true) {
            System.out.println("\n--- Student Management System ---");
            System.out.println("1. Add Student");
            System.out.println("2. View Students");
            System.out.println("3. Exit");
            System.out.println("4.Delete Student");
            System.out.print("Enter your choice: ");

            int choice = sc.nextInt();
            sc.nextLine();

            switch (choice) {
                case 1:
                    addStudent();
                    break;
                case 2:
                    viewStudents();
                    break;
                case 3:
                    System.out.println("Exiting...");
                    sc.close();
                    return;
                case 4:
                    deleteStudent();
                    break;
                default:
                    System.out.println("Invalid choice.");
            }
        }
    }

    // private static void addStudent() {
    //     sc.nextLineIfNeeded(); // Replace this line with nothing if your IDE flags it.
    // }

    private static void viewStudents() {
        String sql = "SELECT * FROM students";

        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                System.out.println(
                    "ID: " + rs.getInt("id")
                    + ", Name: " + rs.getString("name")
                    + ", Roll Number: " + rs.getString("roll_number")
                    + ", Department: " + rs.getString("department")
                    + ", Marks: " + rs.getDouble("marks")
                );
            }

        } catch (SQLException e) {
            System.out.println("Error retrieving students: " + e.getMessage());
        }
    }
}
