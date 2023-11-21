import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

class ShoppingList {
	private ShoppingListItem head;

	ShoppingList() {
		this.head = null;
	}

	void addItem(String itemName, String itemDescription, double itemPrice, Scanner scanner) {
		if (validateItem(itemName, itemDescription, itemPrice)) {
			if (isItemAlreadyInList(itemName)) {
				System.out.println("The item is already in the Shopping List.");

				System.out.print("Do you want to add it to the list? (Yes/No): ");
				String response = scanner.nextLine().trim().toLowerCase();
				if (response.equals("yes") || response.equals("y")) {
					// User confirmed to add the item, so proceed to add it
					addItemToList(itemName, itemDescription, itemPrice);
					System.out.println("Item added Successfully.");
				} else if (response.equals("no") || response.equals("n")) {
					System.out.println("Item not added to the list.");
				} else {
					System.out.println("Invalid response. Please enter 'Yes' or 'No'.");
				}
			} else {
				// Item is not in the list, add it
				addItemToList(itemName, itemDescription, itemPrice);
				System.out.println("Item added Successfully.");
			}
		}
	}

	private void addItemToList(String itemName, String itemDescription, double itemPrice) {
		ShoppingListItem newItem = new ShoppingListItem(itemName, itemDescription, itemPrice);
		if (head == null) {
			head = newItem;
		} else {
			ShoppingListItem current = head;
			while (current.next != null) {
				current = current.next;
			}
			current.next = newItem;
		}
	}

	private boolean isItemAlreadyInList(String itemName) {
		ShoppingListItem current = head;
		while (current != null) {
			if (current.itemName.equalsIgnoreCase(itemName)) {
				return true;
			}
			current = current.next;
		}
		return false;
	}

	private boolean validateItem(String itemName, String itemDescription, double itemPrice) {
		if (itemName.length() > 15 || itemDescription.length() > 30 || itemPrice < 0) {
			System.out.println("[Invalid input] Name (up to 15 chars), and description (up to 30 chars).");
			return false;
		}
		return true;
	}

	void displayList() {
		if (head == null) {
			System.out.println("Shopping List is empty.");
		} else {
			System.out.println("My Shopping List:");
			System.out.println(
					"+-----------------------+--------------------------------+-------------+---------------------+");
			System.out.println(
					"| ITEM NAME             | ITEM DESCRIPTION               | PRICE       | PURCHASE UPDATE     |");
			System.out.println(
					"+-----------------------+--------------------------------+-------------+---------------------+");

			ShoppingListItem current = head;
			int index = 1;
			while (current != null) {
				String indexString;
				if (index < 10) {
					// Format index with a leading space for single-digit numbers
					indexString = String.format("| [%d] ", index);
				} else {
					// Format index without a leading space for double-digit numbers
					indexString = String.format("| [%2d]", index);
				}

				System.out.printf("%s %-16s | %-30s | %-11.2f | %-19s |\n", indexString, current.itemName,
						current.itemDescription, current.itemPrice, current.purchaseDescription);
				current = current.next;
				index++;
			}
			System.out.println(
					"+-----------------------+--------------------------------+-------------+---------------------+");
		}
	}

	void removeItem(int index) {
		if (index < 1 || index > getItemCount()) {
			System.out.println("[Invalid index] Please choose a valid item index.");
			return;
		}

		if (index == 1) {
			head = head.next;
			System.out.println("Item has been removed from the list.");
		} else {
			int currentIndex = 1;
			ShoppingListItem current = head;
			while (currentIndex < index - 1) {
				current = current.next;
				currentIndex++;
			}
			current.next = current.next.next;
			System.out.println("Item has been removed from the list.");
		}
	}

	void markItemAsPurchased(int index) {
		if (index < 1 || index > getItemCount()) {
			System.out.println("Invalid index. Please choose a valid item index.");
			return;
		}

		int currentIndex = 1;
		ShoppingListItem current = head;
		while (currentIndex < index) {
			current = current.next;
			currentIndex++;
		}
		current.purchaseDescription = "ITEM PURCHASED";
		System.out.println("Item has been marked as purchased.");
	}

	int getItemCount() {
		int count = 0;
		ShoppingListItem current = head;
		while (current != null) {
			count++;
			current = current.next;
		}
		return count;
	}

	void modifyItem(int index, String newName, String newDescription, double newPrice) {
		if (validateItem(newName, newDescription, newPrice)) {
			int currentIndex = 1;
			ShoppingListItem current = head;
			while (currentIndex < index) {
				current = current.next;
				currentIndex++;
			}
			current.itemName = newName;
			current.itemDescription = newDescription;
			current.itemPrice = newPrice;
			System.out.println("Item has been modified.");
		}
	}

	void saveListToFile(String fileName) {
		// Construct the file path in the Downloads folder
		String homeDirectory = System.getProperty("user.home");
		String downloadsFolderPath = homeDirectory + File.separator + "Downloads";
		String filePath = downloadsFolderPath + File.separator + fileName;

		try (PrintWriter writer = new PrintWriter(new FileWriter(filePath))) {
			writer.println("My Shopping List:");
			writer.println(
					"+----------------------+--------------------------------+-------------+---------------------+");
			writer.println(
					"| ITEM NAME            | ITEM DESCRIPTION               | PRICE       | PURCHASE UPDATE     |");
			writer.println(
					"+----------------------+--------------------------------+-------------+---------------------+");

			ShoppingListItem current = head;
			int index = 1;
			while (current != null) {
				writer.printf("| [%d] %-16s | %-30s | %-11.2f | %-19s |\n", index, current.itemName,
						current.itemDescription, current.itemPrice, current.purchaseDescription);
				current = current.next;
				index++;
			}
			writer.println(
					"+----------------------+--------------------------------+-------------+---------------------+");

			SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy | hh:mm a");
			String currentDate = dateFormat.format(new Date());

			writer.println("Date of save: " + currentDate); // Add the date of save

			System.out.println("Shopping list has been saved to " + filePath);
		} catch (IOException e) {
			System.err.println("Error: Unable to save the shopping list to the file.");
		}
	}
}
