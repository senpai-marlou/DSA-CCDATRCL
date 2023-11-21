import java.util.Scanner;

public class Main {
	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);

		int num;
		while (true) {
			System.out.print("How many grades to input: ");
			String input = scanner.next();

			try {
				num = Integer.parseInt(input);
				if (num > 0) {
					break; // Valid input, exit the loop
				} else {
					System.out.println("Please enter a positive number.");
				}
			} catch (NumberFormatException e) {
				System.out.println("Please enter a valid integer.");
			}
		}

		int[] grades = new int[num];
		for (int i = 0; i < num; i++) {
			int grade = 0;
			boolean validInput = false;

			while (!validInput) {
				System.out.print("Enter grade: ");
				String input = scanner.next();

				try {
					grade = Integer.parseInt(input);
					if (grade >= 0 && grade <= 100) {
						validInput = true;
					} else {
						System.out.println("Please enter a grade between 0 and 100.");
					}
				} catch (NumberFormatException e) {
					System.out.println("Please enter a valid integer.");
				}
			}

			grades[i] = grade;
		}

		// Create an instance of GradeAnalyzer
		System.out.println("");
		Analyzer analyzer = new GradeAnalyzer();

		// Calculate and display statistics using the abstract method
		analyzer.calculate(grades);

		scanner.close();
	}
}