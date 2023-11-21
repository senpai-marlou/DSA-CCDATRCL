package Problems;

import java.util.Scanner;

public class Prob4 {
	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);

		System.out.print("Enter the number of Fibonacci numbers to generate: ");
		int n = scanner.nextInt();

		// Check for valid input
		if (n <= 0) {
			System.out.println("Please enter a positive integer.");
		} else {
			// Create an array to store the Fibonacci sequence
			long[] fib = new long[n];

			// Initialize the first two elements
			fib[0] = 0;
			if (n > 1) {
				fib[1] = 1;
			}

			// Generate the Fibonacci sequence
			for (int i = 2; i < n; i++) {
				fib[i] = fib[i - 1] + fib[i - 2];
			}

			// Print the Fibonacci sequence
			System.out.println("Fibonacci sequence of " + n + " numbers:");
			for (int i = 0; i < n; i++) {
				System.out.print(fib[i] + " ");
			}
		}
		scanner.close();
	}
}
