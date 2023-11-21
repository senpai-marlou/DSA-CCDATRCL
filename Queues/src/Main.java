import java.util.Scanner;

public class Main {
	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		ReceiptQueue receiptQueue = new ReceiptQueue();

		int choice = 0;
		while (choice != 4) {
			displayMenu();
			choice = getChoice(scanner);

			switch (choice) {
			case 1:
				addPurchase(scanner, receiptQueue);
				break;
			case 2:
				Purchase purchase = receiptQueue.generateReceipt();
				if (purchase != null) {
					receiptQueue.generateReceiptFormat(purchase);
				} else {
					System.out.println("\nReceipt Queue is empty.");
				}
				break;
			case 3:
				receiptQueue.displayReceiptQueue();
				break;
			case 4:
				System.out.println("Exiting the Receipt Generator. Goodbye!");
				break;
			}
		}
	}

	private static void displayMenu() {
		System.out.println("\n+------------------------------+");
		System.out.println("|      RECEIPT GENERATOR       |");
		System.out.println("+------------------------------+");
		System.out.println("| [1] ADD PURCHASE             |");
		System.out.println("| [2] GENERATE RECEIPT         |");
		System.out.println("| [3] DISPLAY RECEIPT QUEUE    |");
		System.out.println("| [4] EXIT                     |");
		System.out.println("+------------------------------+");
	}

	private static int getChoice(Scanner scanner) {
		int choice;
		while (true) {
			System.out.print("Enter your choice: ");
			String input = scanner.nextLine().trim();
			if (input.matches("[1-4]")) {
				choice = Integer.parseInt(input);
				break;
			} else {
				System.out.println("Invalid input: Enter a number (1-4).");
			}
		}
		return choice;
	}

	private static void addPurchase(Scanner scanner, ReceiptQueue receiptQueue) {
		System.out.print("\nEnter Item Name: ");
		String itemName = scanner.nextLine();
		while (itemName.length() > 15) {
			System.out.println("Invalid input: Max 15 characters [Try again]");
			System.out.print("Enter Item Name: ");
			itemName = scanner.nextLine();
		}

		double itemPrice = -1;
		while (itemPrice < 0) {
			System.out.print("Enter Item Price: ");
			if (scanner.hasNextDouble()) {
				itemPrice = scanner.nextDouble();
				if (itemPrice < 0) {
					System.out.println("Invalid input: Please enter a valid positive number [Try again]");
				}
			} else {
				System.out.println("Invalid input: It should be a positive number [Try again]");
			}
			scanner.nextLine(); // Read and discard the rest of the line
		}

		int quantity = -1;
		while (quantity <= 0) {
			System.out.print("Enter Quantity: ");
			if (scanner.hasNextInt()) {
				quantity = scanner.nextInt();
				if (quantity <= 0) {
					System.out.println("Invalid input: Please enter a valid positive integer [Try again]");
				}
			} else {
				System.out.println("Invalid input: It should be a positive integer [Try again]");
			}
			scanner.nextLine(); // Read and discard the rest of the line
		}

		Purchase purchase = new Purchase(itemName, itemPrice, quantity);
		receiptQueue.addPurchase(purchase);
		System.out.println("\nPurchase added to the queue...");
	}

}