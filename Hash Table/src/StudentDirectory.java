import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;

public class StudentDirectory {
	private HashMap<String, Student> students;
	private Scanner scanner;

	public StudentDirectory() {
		this.students = new HashMap<>();
		this.scanner = new Scanner(System.in);
	}

	public void addStudent() {
		System.out.println("\n[Running] Adding Student...\n");

		String name;
		do {
			System.out.print("Name: ");
			scanner.nextLine(); // Consume the newline character
			name = scanner.nextLine().toUpperCase();

			if (name.matches(".*\\d+.*")) {
				System.out.println("Invalid name. The name must not contain numbers.");
			}
		} while (name.matches(".*\\d+.*"));

		String idNumber;
		boolean isIdExists;
		do {
			System.out.print("ID Number: ");
			idNumber = scanner.next();
			scanner.nextLine(); // Consume the newline character

			isIdExists = students.containsKey(idNumber);

			if (isIdExists) {
				System.out.println("Student ID already exists");
			}
		} while (!isValidStudentID(idNumber) || isIdExists);

		System.out.print("Student Major: ");
		String major = scanner.nextLine().toUpperCase();

		Student student = new Student(name, idNumber, major);
		students.put(idNumber, student);

		System.out.println("\nStudent record added successfully.");
	}

	public void searchStudent() {
		System.out.println("\n[Running] Searching Student...\n");

		System.out.print("Enter Student's ID Number: ");
		String idNumber = scanner.next();

		if (students.containsKey(idNumber)) {
			System.out.println("+-------------------------------------------------------+");
			System.out.println("|                     STUDENT FOUND                     |");
			System.out.println("+--------------+--------------------------+-------------+");
			System.out.println("| STUDENT ID   | STUDENT NAME             | MAJOR       |");
			System.out.println("+--------------+--------------------------+-------------+");
			displayStudentTableEntry(idNumber, students.get(idNumber));
			System.out.println("+--------------+--------------------------+-------------+");
		} else {
			System.out.println("\n+-------------------------+");
			System.out.println("|    STUDENT NOT FOUND    |");
			System.out.println("+-------------------------+\n");
			System.out.println("[Returning] to the main menu...");
		}
	}

	public void displayStudents() {
		System.out.println("\n[Running] Displaying Students...\n");

		if (students.isEmpty()) {
			System.out.println("No students in the system: (Add students before displaying)\n");
			return;
		}

		System.out.println("+-------------------------------------------------------+");
		System.out.println("|              NATIONAL UNIVERSITY STUDENTS             |");
		System.out.println("+--------------+--------------------------+-------------+");
		System.out.println("| STUDENT ID   | STUDENT NAME             | MAJOR       |");
		System.out.println("+--------------+--------------------------+-------------+");

		for (Map.Entry<String, Student> entry : students.entrySet()) {
			System.out.printf("| %-12s | %-24s | %-11s |\n", entry.getKey(), entry.getValue().getName(),
					entry.getValue().getMajor());
		}

		System.out.println("+--------------+--------------------------+-------------+");
	}

	public void removeStudent() {
		System.out.println("\n[Running] Removing Student...\n");

		if (students.isEmpty()) {
			System.out.println("No students in the system. Add students before removing.");
			return;
		}

		System.out.println("+------------------+--------------------------+-------------+");
		System.out.println("| STUDENT ID       | STUDENT NAME             | MAJOR       |");
		System.out.println("+------------------+--------------------------+-------------+");
		int index = 1;

		Set<Map.Entry<String, Student>> entrySet = students.entrySet();
		Iterator<Map.Entry<String, Student>> iterator = entrySet.iterator();

		while (iterator.hasNext()) {
			Map.Entry<String, Student> entry = iterator.next();
			System.out.println(String.format("| [%d] %-12s | %-24s | %-11s |", index++, entry.getKey(),
					entry.getValue().getName(), entry.getValue().getMajor()));
		}
		System.out.println("+------------------+--------------------------+-------------+");

		System.out.print("\nEnter Student ID or [Index]: ");
		String input = scanner.next();

		if (input.matches("\\d+")) {
			int selectedIndex = Integer.parseInt(input);
			if (selectedIndex > 0 && selectedIndex <= students.size()) {
				iterator = entrySet.iterator();
				for (int i = 1; i < selectedIndex; i++) {
					iterator.next();
				}
				String selectedID = iterator.next().getKey();
				students.remove(selectedID);
				System.out.println("\nStudent record removed successfully.");
			} else {
				System.out.println("\n[Invalid input] Removal failed.");
			}
		} else if (students.containsKey(input)) {
			students.remove(input);
			System.out.println("\nStudent record removed successfully.");
		} else {
			System.out.println("\n[Invalid input] Removal failed.");
		}
	}

	private void displayStudentTableEntry(String id, Student student) {
		System.out.println(String.format("| %-12s | %-24s | %-11s |", id, student.getName(), student.getMajor()));
	}

	private boolean isValidStudentID(String idNumber) {
		// Validate the format of student ID (YYYY-NNNNNN)
		String[] parts = idNumber.split("-");
		if (parts.length != 2) {
			System.out.println("[Invalid student ID format] Please use the format: [YEAR-######]");
			return false;
		}

		try {
			int year = Integer.parseInt(parts[0]);
			int number = Integer.parseInt(parts[1]);

			if (parts[0].length() != 4 || year < 1900 || year > 2023 || parts[1].length() != 6) {
				System.out.println("[Invalid student ID format] Please use the format: [YEAR-######]");
				return false;
			}
		} catch (NumberFormatException e) {
			System.out.println("[Invalid student ID format] Please use the format: [YEAR-######]");
			return false;
		}

		return true;
	}

	public static void main(String[] args) {
		StudentDirectory studentDirectory = new StudentDirectory();

		int choice;
		do {
			System.out.println("+-------------------------+");
			System.out.println("|    STUDENT DIRECTORY    |");
			System.out.println("|        MAIN MENU        |");
			System.out.println("+=========================+");
			System.out.println("| 1. ADD STUDENT          |");
			System.out.println("| 2. SEARCH STUDENT       |");
			System.out.println("| 3. DISPLAY STUDENTS     |");
			System.out.println("| 4. REMOVE STUDENT       |");
			System.out.println("| 5. EXIT                 |");
			System.out.println("+-------------------------+");
			System.out.print("Your Choice: ");

			while (!studentDirectory.scanner.hasNextInt()) {
				System.out.print("[Invalid input] Please enter a number: ");
				studentDirectory.scanner.next(); // consume the invalid input
			}

			choice = studentDirectory.scanner.nextInt();

			switch (choice) {
			case 1:
				studentDirectory.addStudent();
				break;
			case 2:
				studentDirectory.searchStudent();
				break;
			case 3:
				studentDirectory.displayStudents();
				break;
			case 4:
				studentDirectory.removeStudent();
				break;
			case 5:
				// Ask for confirmation before exiting
				System.out.print("Are you sure you want to exit? (yes/no): ");
				String exitConfirmation = studentDirectory.scanner.next();
				if (exitConfirmation.equalsIgnoreCase("y") || exitConfirmation.equalsIgnoreCase("yes")) {
					System.out.println("\nExiting...");
					System.out.println("Thank you for using Student Directory System");
				} else {
					choice = 0; // Reset choice to continue the loop
				}
				break;
			default:
				System.out.println("Invalid choice");
			}
		} while (choice != 5);

		// Close the scanner to avoid resource leaks
		studentDirectory.scanner.close();
	}
}

class Student {
	private String name;
	private String idNumber;
	private String major;

	public Student(String name, String idNumber, String major) {
		this.name = name;
		this.idNumber = idNumber;
		this.major = major;
	}

	public String getName() {
		return name;
	}

	public String getIdNumber() {
		return idNumber;
	}

	public String getMajor() {
		return major;
	}
}
