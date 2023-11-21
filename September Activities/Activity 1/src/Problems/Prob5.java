package Problems;

import java.util.Scanner;

public class Prob5 {

	public static void main(String[] args) {
		// Create a Scanner object to read user input
		Scanner scanner = new Scanner(System.in);

		// Prompt the user for input
		System.out.print("Enter a sentence or text: ");
		String inputText = scanner.nextLine();
		scanner.close();

		// Split the input text into words using spaces as separators
		String[] words = inputText.split(" ");

		// Count the number of words
		int wordCount = words.length;

		// Display the result
		System.out.println("Word count: " + wordCount);
	}
}
