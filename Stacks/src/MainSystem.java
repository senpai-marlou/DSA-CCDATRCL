import java.util.Scanner;
import java.util.Stack;

public class MainSystem {
	private Stack<Book> borrowedBooks = new Stack<>();
	private Stack<Book> availableBooks = new Stack<Book>();

	public void addBook(Book book) {
		availableBooks.push(book);
	}

	public void borrowBook() {
		if (!availableBooks.isEmpty()) {
			Book borrowed = availableBooks.pop();
			borrowedBooks.push(borrowed);
			System.out.println("Borrowed: " + borrowed);
		} else {
			System.out.println("No available books to borrow.");
		}
	}

	public void returnBook() {
		if (!borrowedBooks.isEmpty()) {
			Book returned = borrowedBooks.pop();
			availableBooks.push(returned);
			System.out.println("Returned: " + returned);
		} else {
			System.out.println("No books to return.");
		}
	}

	public void displayBorrowedBooks() {
		if (borrowedBooks.isEmpty()) {
			System.out.println("\n+---------------------------------------------------------------------------+"
					+ "\n|                            NO BORROWED BOOKS                              |"
					+ "\n+---------------------------------------------------------------------------+");
		} else {
			System.out.println("\n+---------------------------------------------------------------------------+"
					+ "\n|                             BORROWED BOOKS                                |"
					+ "\n+-----------------------------+--------------------------+------------------+"
					+ "\n| BOOK NAME                   | AUTHOR                   | YEAR PUBLISH     |"
					+ "\n+-----------------------------+--------------------------+------------------+");
			for (int i = 0; i < borrowedBooks.size(); i++) {
				System.out.println(borrowedBooks.get(i).formattedString(i + 1));
			}
			System.out.println("+---------------------------------------------------------------------------+");
		}
	}

	public void displayAvailableBooks() {
		if (availableBooks.isEmpty()) {
			System.out.println("\n+---------------------------------------------------------------------------+"
					+ "\n|                          NO AVAILABLE BOOKS                               |"
					+ "\n+---------------------------------------------------------------------------+");
		} else {
			System.out.println("\n+---------------------------------------------------------------------------+"
					+ "\n|                            AVAILABLE BOOKS                                |"
					+ "\n+-----------------------------+--------------------------+------------------+"
					+ "\n| BOOK NAME                   | AUTHOR                   | YEAR PUBLISH     |"
					+ "\n+-----------------------------+--------------------------+------------------+");
			for (int i = 0; i < availableBooks.size(); i++) {
				System.out.println(availableBooks.get(i).formattedString(i + 1));
			}
			System.out.println("+---------------------------------------------------------------------------+");
		}
	}

	public static void main(String[] args) {
		MainSystem library = new MainSystem();

		// Pre-defined books
		library.addBook(new Book("To Kill a Mockingbird", "Harper Lee", 1960));
		library.addBook(new Book("The Lord of the Rings", "J.R.R. Tolkien", 1954));
		library.addBook(new Book("Brave New World", "Aldous Huxley", 1932));
		library.addBook(new Book("The Hobbit", "J.R.R. Tolkien", 1937));
		library.addBook(new Book("Pride and Prejudice", "Jane Austen", 1813));

		Scanner scanner = new Scanner(System.in);

		while (true) {
			System.out.println("\n+------------------------------+");
			System.out.println("|     BOOK BORROWING SYSTEM    |");
			System.out.println("|          MAIN MENU           |");
			System.out.println("+------------------------------+");
			System.out.println("| [1] BORROW A BOOK            |");
			System.out.println("| [2] RETURN A BOOK            |");
			System.out.println("| [3] DISPLAY BORROWED BOOKS   |");
			System.out.println("| [4] DISPLAY AVAILABLE BOOKS  |");
			System.out.println("| [5] ADMIN MENU               |");
			System.out.println("| [6] EXIT                     |");
			System.out.println("+------------------------------+\n");

			System.out.print("Enter your choice: ");

			int choice = scanner.nextInt();
			scanner.nextLine();

			switch (choice) {
			case 1:
				library.borrowBook();
				break;
			case 2:
				library.returnBook();
				break;
			case 3:
				library.displayBorrowedBooks();
				break;
			case 4:
				library.displayAvailableBooks();
				break;
			case 5:
				adminMenu(library, scanner);
				break;
			case 6:
				System.out.println("Exiting the Borrowing Book System. Goodbye!");
				scanner.close();
				System.exit(0);
			default:
				System.out.println("Invalid choice. Please try again.");
			}
		}
	}

	private static void adminMenu(MainSystem library, Scanner scanner) {
		while (true) {
			System.out.println("\n+----------------------------+");
			System.out.println("|        ADMIN MENU          |");
			System.out.println("+----------------------------+");
			System.out.println("| [1] ADD BOOK               |");
			System.out.println("| [2] MODIFY BOOK            |");
			System.out.println("| [3] DELETE BOOK            |");
			System.out.println("| [4] BACK TO MAIN MENU      |");
			System.out.println("+----------------------------+\n");

			System.out.print("Enter your choice: ");

			int adminChoice = scanner.nextInt();
			scanner.nextLine();

			switch (adminChoice) {
			case 1:
				addBook(library, scanner);
				break;
			case 2:
				editBook(library, scanner);
				break;
			case 3:
				deleteBook(library, scanner);
				break;
			case 4:
				return;
			default:
				System.out.println("Invalid choice. Please try again.");
			}
		}
	}

	private static void addBook(MainSystem library, Scanner scanner) {
		System.out.println("\nEnter book details...\n");
		System.out.print("Title: ");
		String title = scanner.nextLine();
		System.out.print("Author: ");
		String author = scanner.nextLine();
		System.out.print("Year of Publication: ");
		int year = scanner.nextInt();
		scanner.nextLine();

		Book newBook = new Book(title, author, year);
		library.addBook(newBook);
		System.out.println("Book added: " + newBook);
	}

	private static void editBook(MainSystem library, Scanner scanner) {
		library.displayAvailableBooks();
		System.out.print("Enter the index of the book to edit: ");
		int index = scanner.nextInt() - 1;
		scanner.nextLine();

		if (index >= 0 && index < library.availableBooks.size()) {
			System.out.println("Enter new book details...\n");
			System.out.print("Title: ");
			String title = scanner.nextLine();
			System.out.print("Author: ");
			String author = scanner.nextLine();
			System.out.print("Year of Publication: ");
			int year = scanner.nextInt();
			scanner.nextLine();

			Book editedBook = new Book(title, author, year);
			library.availableBooks.set(index, editedBook);
			System.out.println("Book edited: " + editedBook);
		} else {
			System.out.println("Invalid index. Please try again.");
		}
	}

	private static void deleteBook(MainSystem library, Scanner scanner) {
		library.displayAvailableBooks();
		System.out.print("Enter the index of the book to delete: ");
		int index = scanner.nextInt() - 1;
		scanner.nextLine();

		if (index >= 0 && index < library.availableBooks.size()) {
			Book deletedBook = library.availableBooks.remove(index);
			System.out.println("Book deleted: " + deletedBook);
		} else {
			System.out.println("Invalid index. Please try again.");
		}
	}
}