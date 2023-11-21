import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;

public class Main {

	public static void main(String[] args) {
		ShoppingList myShoppingList = new ShoppingList();
		Scanner scanner = new Scanner(System.in);
		boolean exit = false;

		while (!exit) {
			System.out.println("\n+------------------+-----------+");
			System.out.println("| MY SHOPPING LIST | MAIN MENU |");
			System.out.println("+------------------+-----------+");
			System.out.println("| [1] INSERT SHOPPING ITEM     |");
			System.out.println("| [2] BROWSE SHOPPING ITEM     |");
			System.out.println("| [3] REMOVE SHOPPING ITEM     |");
			System.out.println("| [4] MARK ITEM AS PURCHASED   |");
			System.out.println("| [5] DISPLAY SHOPPING LIST    |");
			System.out.println("| [6] MODIFY SHOPPING LIST     |");
			System.out.println("| [7] SAVE SHOPPING LIST       |");
			System.out.println("| [8] EXIT                     |");
			System.out.println("+------------------------------+");

			System.out.print("\nYour Choice: ");
			int choice = getValidChoice(scanner, 1, 8);

			switch (choice) {
			case 1:
				System.out.println();
				addItemMenu(myShoppingList, scanner);
				break;
			case 2:
				System.out.println();
				browseShoppingItem(myShoppingList, scanner);
				break;
			case 3:
				System.out.println();
				removeItemMenu(myShoppingList, scanner);
				break;
			case 4:
				System.out.println();
				markItemAsPurchasedMenu(myShoppingList, scanner);
				break;
			case 5:
				System.out.println();
				myShoppingList.displayList();
				break;
			case 6:
				System.out.println();
				modifyItemMenu(myShoppingList, scanner);
				break;
			case 7:
				System.out.println();
				myShoppingList.saveListToFile("shopping_list.txt");
				break;
			case 8:
				System.out.println();
				exit = confirmExit(scanner);
				break;
			}
		}
	}

	private static void addItemMenu(ShoppingList shoppingList, Scanner scanner) {
		System.out.println("INSERT SHOPPING ITEM\n");
		System.out.print("ITEM NAME: ");
		String itemName = scanner.nextLine();
		System.out.print("ITEM DESCRIPTION: ");
		String itemDescription = scanner.nextLine();

		double itemPrice = 0.00;
		boolean validPrice = false;

		while (!validPrice) {
			System.out.print("PRICE: ");
			String priceInput = scanner.nextLine();

			try {
				itemPrice = Double.parseDouble(priceInput);
				validPrice = true;
			} catch (NumberFormatException e) {
				System.out.println("[Invalid input] Price should be a valid number.");
			}
		}

		shoppingList.addItem(itemName, itemDescription, itemPrice, scanner);
	}

	private static void browseShoppingItem(ShoppingList shoppingList, Scanner scanner) {
		System.out.println("BROWSE SHOPPING ITEM\n");

		// Construct the file path in the Downloads folder
		String homeDirectory = System.getProperty("user.home");
		String downloadsFolderPath = homeDirectory + File.separator + "Downloads";
		String fileName = downloadsFolderPath + File.separator + "shopping_items.csv";

		try {
			BufferedReader reader = new BufferedReader(new FileReader(fileName));
			String line;
			int index = 0;

			System.out.println("+----------------------------------------------------------------------+");
			System.out.println("|                      MARLOU'S MARKET STOCKS                          |");
			System.out.println("+-----------------------+--------------------------------+-------------+");
			System.out.println("| ITEM NAME             | ITEM DESCRIPTION               | PRICE       |");
			System.out.println("+-----------------------+--------------------------------+-------------+");

			while ((line = reader.readLine()) != null) {
				if (index > 0) {
					String[] parts = line.split(",");
					String itemName = parts[0];
					String itemDescription = parts[1];
					String priceString = parts[2].replaceAll(",", ""); // Remove commas
					double itemPrice = Double.parseDouble(priceString);

					String indexString;
					if (index <= 9) {
						// Format index without leading space for single-digit numbers
						indexString = String.format("| [%d] ", index);
					} else {
						// Format index with leading space for double-digit numbers
						indexString = String.format("| [%2d]", index);
					}

					System.out.printf("%s %-16s | %-30s | %-11.2f |\n", indexString, itemName, itemDescription,
							itemPrice);
				}
				index++;
			}

			if (index > 0) {
				System.out.println("+-----------------------+--------------------------------+-------------+");
				System.out.print("Choose an item to add to the shopping list (or 0 to Cancel): ");
				int choice = getValidChoice(scanner, 0, index - 1);

				if (choice > 0) {
					reader.close(); // Close the reader to reset the file pointer

					// Re-open the file to read the selected item
					reader = new BufferedReader(new FileReader(fileName));

					// Skip the header line
					reader.readLine();

					index = 1; // Reset the index
					while ((line = reader.readLine()) != null) {
						if (index == choice) {
							String[] parts = line.split(",");
							String itemName = parts[0];
							String itemDescription = parts[1];
							double itemPrice = Double.parseDouble(parts[2]);
							shoppingList.addItem(itemName, itemDescription, itemPrice, scanner);
							break;
						}
						index++;
					}

					reader.close();
				}
			} else {
				System.out.println("No shopping items found in the Downloads folder.");
			}

		} catch (IOException e) {
			System.out.println("No shopping items found in the Downloads folder.");
		}
	}

	private static void removeItemMenu(ShoppingList shoppingList, Scanner scanner) {
		System.out.println("REMOVE SHOPPING ITEM\n");
		shoppingList.displayList();

		if (shoppingList.getItemCount() > 0) {
			System.out.print("Choose an item to remove (or 0 to cancel): ");
			int choice = getValidChoice(scanner, 0, shoppingList.getItemCount());

			if (choice > 0) {
				shoppingList.removeItem(choice);
			}
		}
	}

	private static void markItemAsPurchasedMenu(ShoppingList shoppingList, Scanner scanner) {
		System.out.println("MARK ITEM AS PURCHASED\n");
		shoppingList.displayList();

		if (shoppingList.getItemCount() > 0) {
			System.out.print("Choose an item to mark as purchased (or 0 to cancel): ");
			int choice = getValidChoice(scanner, 0, shoppingList.getItemCount());

			if (choice > 0) {
				shoppingList.markItemAsPurchased(choice);
			}
		}
	}

	private static void modifyItemMenu(ShoppingList shoppingList, Scanner scanner) {
		System.out.println("MODIFY SHOPPING LIST\n");
		shoppingList.displayList();

		if (shoppingList.getItemCount() > 0) {
			System.out.print("Choose an item to modify (or 0 to cancel): ");
			int choice = getValidChoice(scanner, 0, shoppingList.getItemCount());

			if (choice > 0) {
				System.out.print("Enter NEW ITEM NAME: ");
				String newName = scanner.nextLine();
				System.out.print("Enter NEW ITEM DESCRIPTION: ");
				String newDescription = scanner.nextLine();

				double newPrice = 0.00;
				boolean validPrice = false;

				while (!validPrice) {
					System.out.print("Enter NEW PRICE: ");
					String priceInput = scanner.nextLine();

					try {
						newPrice = Double.parseDouble(priceInput);
						validPrice = true;
					} catch (NumberFormatException e) {
						System.out.println("[Invalid input] Price should be a valid number.");
					}
				}

				shoppingList.modifyItem(choice, newName, newDescription, newPrice);
			}
		}
	}

	private static int getValidChoice(Scanner scanner, int min, int max) {
		int choice;
		while (true) {
			if (scanner.hasNextInt()) {
				choice = scanner.nextInt();
				if (choice >= min && choice <= max) {
					scanner.nextLine(); // Consume the newline
					return choice;
				} else {
					System.out.print("Invalid input. Please enter a valid number between " + min + " - " + max + ": ");
				}
			} else {
				System.out.print("Invalid input. Please enter a valid number: ");
				scanner.nextLine(); // Consume the invalid input
			}
		}
	}

	private static boolean confirmExit(Scanner scanner) {
		System.out.print("Do you want to exit the program? [YES/NO]: ");
		String response = scanner.nextLine().trim().toLowerCase();
		return response.equals("yes") || response.equals("y");
	}
}