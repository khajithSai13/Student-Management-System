
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
    private static void updateStudent(){
        viewStudents();
        System.out.print("Enter the id of studnet u want to modify : ");
        int id = sc.nextInt();
        System.out.print("what would u like to modify 1.name 2. rollnumber 3.department 4.marks :" );
        int change = sc.nextInt();
        System.out.print("ENter the data : ");
        Object data;
        if(change == 4){
            data = sc.nextDouble();
        }else{
            data = sc.next();
        }
        String col ="";
        switch (change) {
            case 1:
                col = "name";
                break;
            case 2:
                col = "roll_number";
                break;
            case 3:
                col = "department";
                break;
            case 4:
                col = "marks";
                break;
            default:
                System.out.println("invalid choice ");
                return;
        }
        String sql = "UPDATE students SET " + col + " = ? WHERE id = ?";
        try (Connection conn = DBConnection.getConnection(); PreparedStatement ps = conn.prepareStatement(sql)){
            if(change == 4){
                ps.setDouble(1,(double) data);
            }else{
                ps.setString(1,(String) data);
            }
            ps.setInt(2,id);
            int rs = ps.executeUpdate();
            if(rs>0){
                System.out.println("Updated Successfully.. ");
            }else{
                System.out.println("Invalid ID");
            }
        } catch (SQLException e) {
            System.out.println("Error updating students data : " + e.getMessage());
        }

    }
    private static final Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {
        while (true) {
            System.out.println("\n--- Student Management System ---");
            System.out.println("1. Add Student");
            System.out.println("2. View Students");
            System.out.println("3. Delete Student");
            System.out.println("4. Exit");
            System.out.println("5. Update Student");
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
                    deleteStudent();
                    break;
                case 4:
                    System.out.println("Exiting...");
                    sc.close();
                    return;
                case 5:
                    updateStudent();
                    break;
                default:
                    System.out.println("Invalid choice.");
            }
        }
    }
}
