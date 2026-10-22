
import java.util.ArrayList;
import java.util.Scanner;

class Student {
    int id;
    String name;
    double marks;

    Student(int id, String name, double marks) {
        this.id = id;
        this.name = name;
        this.marks = marks;
    }

    String getGrade() {
        if (marks >= 90)
            return "A+";
        if (marks >= 80)
            return "A";
        if (marks >= 70)
            return "B";
        if (marks >= 60)
            return "C";
        if (marks >= 35)
            return "D";
        return "Fail";
    }

    void display() {
        System.out.println("ID: " + id
                + " | Name: " + name
                + " | Marks: " + marks
                + " | Grade: " + getGrade());
    }
}

public class StudentManagementSystem {
    static ArrayList<Student> students = new ArrayList<>();
    static Scanner sc = new Scanner(System.in);

    static int readInt(String message) {
        while (true) {
            try {
                System.out.print(message);
                return Integer.parseInt(sc.nextLine().trim());
            } catch (NumberFormatException e) {
                System.out.println("Please enter a valid whole number.");
            }
        }
    }

    static double readMarks() {
        while (true) {
            try {
                System.out.print("Enter marks (0-100): ");
                double marks = Double.parseDouble(sc.nextLine().trim());
                if (marks >= 0 && marks <= 100) {
                    return marks;
                }
                System.out.println("Marks must be between 0 and 100.");
            } catch (NumberFormatException e) {
                System.out.println("Please enter valid marks.");
            }
        }
    }

    static void addStudent() {
        int id = readInt("Enter student ID: ");

        if (findStudent(id) != null) {
            System.out.println("This ID already exists.");
            return;
        }

        System.out.print("Enter student name: ");
        String name = sc.nextLine().trim();

        if (name.isEmpty()) {
            System.out.println("Name cannot be empty.");
            return;
        }

        double marks = readMarks();
        students.add(new Student(id, name, marks));
        System.out.println("Student added successfully!");
    }

    static Student findStudent(int id) {
        for (Student s : students) {
            if (s.id == id) {
                return s;
            }
        }
        return null;
    }

    static void viewStudents() {
        if (students.isEmpty()) {
            System.out.println("No student records found.");
            return;
        }

        System.out.println("\n--- Student Records ---");
        for (Student s : students) {
            s.display();
        }
    }

    static void searchStudent() {
        int id = readInt("Enter student ID to search: ");
        Student s = findStudent(id);

        if (s != null) {
            s.display();
        } else {
            System.out.println("Student not found.");
        }
    }

    static void deleteStudent() {
        int id = readInt("Enter student ID to delete: ");
        Student s = findStudent(id);

        if (s != null) {
            students.remove(s);
            System.out.println("Student deleted successfully!");
        } else {
            System.out.println("Student not found.");
        }
    }

    public static void main(String[] args) {
        while (true) {
            System.out.println("\n===== STUDENT RECORD MANAGEMENT =====");
            System.out.println("1. Add Student");
            System.out.println("2. View All Students");
            System.out.println("3. Search Student");
            System.out.println("4. Delete Student");
            System.out.println("5. Exit");

            int choice = readInt("Enter your choice: ");

            switch (choice) {
                case 1:
                    addStudent();
                    break;
                case 2:
                    viewStudents();
                    break;
                case 3:
                    searchStudent();
                    break;
                case 4:
                    deleteStudent();
                    break;
                case 5:
                    System.out.println("Thank you for using the system!");
                    sc.close();
                    return;
                default:
                    System.out.println("Invalid choice. Choose 1 to 5.");
            }
        }
    }
}
