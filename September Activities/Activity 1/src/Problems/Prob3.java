package Problems;

import java.util.ArrayList;
import java.util.Scanner;

public class Prob3 {

	public static void main(String[] args) {
		ArrayList<String> tasks = new ArrayList<>();
		Scanner scanner = new Scanner(System.in);

		while (true) {
			System.out.println("To-Do List Menu:");
			System.out.println("1. Add Task");
			System.out.println("2. View Tasks");
			System.out.println("3. Mark Task as Complete");
			System.out.println("4. Remove Task");
			System.out.println("5. Exit");
			System.out.print("Enter your choice: ");

			int choice = scanner.nextInt();
			scanner.nextLine(); // Consume newline character

			switch (choice) {
			case 1:
				System.out.print("Enter task description: ");
				String taskDescription = scanner.nextLine();
				tasks.add(taskDescription);
				System.out.println("\nTask added!\n");
				break;

			case 2:
				if (tasks.isEmpty()) {
					System.out.println("\nNo tasks to view.\n");
				} else {
					System.out.println("\nTasks:");
					for (int i = 0; i < tasks.size(); i++) {
						System.out.println((i + 1) + ". " + tasks.get(i));
					}
					System.out.println("");
				}
				break;

			case 3:
				if (tasks.isEmpty()) {
					System.out.println("\nNo tasks to mark as complete.\n");
				} else {
					System.out.println("Tasks:");
					for (int i = 0; i < tasks.size(); i++) {
						System.out.println((i + 1) + ". " + tasks.get(i));
					}
					System.out.print("\nEnter the task number to mark as complete: \n");
					int taskIndex = scanner.nextInt();
					scanner.nextLine(); // Consume newline character
					if (taskIndex >= 1 && taskIndex <= tasks.size()) {
						System.out.println("\nMarking task as complete: " + tasks.get(taskIndex - 1) + "\n");
						tasks.remove(taskIndex - 1);
					} else {
						System.out.println("\nInvalid task number.\n");
					}
				}
				break;

			case 4:
				if (tasks.isEmpty()) {
					System.out.println("\nNo tasks to remove.\n");
				} else {
					System.out.println("\nTasks:");
					for (int i = 0; i < tasks.size(); i++) {
						System.out.println((i + 1) + ". " + tasks.get(i) + "\n");
					}
					System.out.print("\nEnter the task number to remove: ");
					int taskIndex = scanner.nextInt();
					scanner.nextLine(); // Consume newline character
					if (taskIndex >= 1 && taskIndex <= tasks.size()) {
						System.out.println("\nRemoving task: " + tasks.get(taskIndex - 1) + "\n");
						tasks.remove(taskIndex - 1);
					} else {
						System.out.println("\nInvalid task number.\n");
					}
				}
				break;

			case 5:
				System.out.println("\nExiting the program. Goodbye!");
				scanner.close();
				System.exit(0);

			default:
				System.out.println("\nInvalid choice. Please choose a valid option.");
			}
		}
	}

}
