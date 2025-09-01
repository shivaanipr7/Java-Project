import java.util.*;

class Student {
    private int id;
    private String name;
    private int examScore;
    private boolean registered;
    private boolean feePaid;

    public Student(int id, String name, int examScore) {
        this.id = id;
        this.name = name;
        this.examScore = examScore;
        this.registered = false;
        this.feePaid = false;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public int getExamScore() {
        return examScore;
    }

    public boolean isRegistered() {
        return registered;
    }

    public boolean isFeePaid() {
        return feePaid;
    }

    public void register() {
        this.registered = true;
    }

    public void payFee() {
        this.feePaid = true;
    }

    @Override
    public String toString() {
        return "Student ID: " + id + ", Name: " + name + ", Exam Score: " + examScore +
               "/100, Registered: " + registered + ", Fee Paid: " + feePaid;
    }
}

public class UniversityAdmissionSystem {
    private Map<Integer, Student> students = new HashMap<>();
    private final int PASSING_SCORE = 50;
    private Scanner scanner = new Scanner(System.in);

    private int getIntInput(String prompt) {
        int number = -1;
        while (true) {
            System.out.print(prompt);
            String input = scanner.nextLine();
            try {
                number = Integer.parseInt(input);
                if (number < 0) {
                    System.out.println("Please enter a non-negative number.");
                    continue;
                }
                break;
            } catch(NumberFormatException e) {
                System.out.println("Invalid input. Please enter a valid number.");
            }
        }
        return number;
    }

    public void addStudent() {
        int id = getIntInput("Enter numeric Student ID: ");
        if (students.containsKey(id)) {
            System.out.println("Student ID already exists!");
            return;
        }
        System.out.print("Enter Student Name: ");
        String name = scanner.nextLine();

        int score = -1;
        while (true) {
            score = getIntInput("Enter Exam Score (0-100): ");
            if (score < 0 || score > 100) {
                System.out.println("Score must be between 0 and 100.");
            } else {
                break;
            }
        }

        Student student = new Student(id, name, score);
        students.put(id, student);
        System.out.println("Student added successfully.");
    }

    public void registerStudent() {
        int id = getIntInput("Enter Student ID to register: ");
        Student student = students.get(id);
        if(student == null) {
            System.out.println("Student not found.");
            return;
        }
        if(student.getExamScore() < PASSING_SCORE) {
            System.out.println("Student's exam score is below 50. Cannot register.");
            return;
        }
        if(student.isRegistered()) {
            System.out.println("Student already registered.");
            return;
        }
        student.register();
        System.out.println("Student registered successfully.");
    }

    public void payFee() {
        int id = getIntInput("Enter Student ID to pay fee: ");
        Student student = students.get(id);
        if(student == null) {
            System.out.println("Student not found.");
            return;
        }
        if(!student.isRegistered()) {
            System.out.println("Student is not registered. Cannot pay fee.");
            return;
        }
        if(student.isFeePaid()) {
            System.out.println("Fee already paid.");
            return;
        }
        student.payFee();
        System.out.println("Fee paid successfully.");
    }

    public void displayStudent() {
        int id = getIntInput("Enter Student ID to display info: ");
        Student student = students.get(id);
        if(student == null) {
            System.out.println("Student not found.");
            return;
        }
        System.out.println(student);
    }

    public void listAllStudents() {
        if(students.isEmpty()) {
            System.out.println("No students to display.");
            return;
        }
        System.out.println("All Students:");
        for(Student s : students.values()) {
            System.out.println(s);
        }
    }

    public void menu() {
        while(true) {
            System.out.println("\n--- University Admission System ---");
            System.out.println("1. Add Student");
            System.out.println("2. Register Student");
            System.out.println("3. Pay Fee");
            System.out.println("4. Display Student Info");
            System.out.println("5. List All Students");
            System.out.println("6. Exit");
            System.out.print("Select an option (1-6): ");
            String input = scanner.nextLine();

            switch(input) {
                case "1": addStudent(); break;
                case "2": registerStudent(); break;
                case "3": payFee(); break;
                case "4": displayStudent(); break;
                case "5": listAllStudents(); break;
                case "6":
                    System.out.println("Exiting system. Goodbye!");
                    return;
                default:
                    System.out.println("Invalid option. Please select again.");
            }
        }
    }

    public static void main(String[] args) {
        UniversityAdmissionSystem system = new UniversityAdmissionSystem();
        system.menu();
    }
}
