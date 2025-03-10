
public class StudentApp {

    public static void main(String[] args) {
        while (true) {
            StudentView.showMenu();
            int choice = StudentView.getInputInt();
            switch (choice) {
                case 1: 
                    Student student = StudentView.getStudentData();
                    StudentController.addStudent(student);
                    break;
                case 2: // View All Students
                    List<Student> students = StudentController.getAllStudents();
                    StudentView.displayStudents(students);
                    break;
                case 3: // Update Student
                    Student updatedStudent = StudentView.getStudentData();
                    StudentController.updateStudent(updatedStudent);
                    break;
                case 4: // Delete Student
                    System.out.print("Enter StudentID to delete: ");
                    int studentID = StudentView.getInputInt();
                    StudentController.deleteStudent(studentID);
                    break;
                case 5: // Exit
                    System.out.println("Exiting...");
                    return;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }
}

