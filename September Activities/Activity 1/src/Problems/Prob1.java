package Problems;

import java.util.Scanner;

public class Prob1 {

	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);

		while (true) {
			System.out.println("--Temperature Converter--");
			System.out.println("1. Fahrenheit to Celsius");
			System.out.println("2. Celsius to Fahrenheit");
			System.out.println("3. Exit");
			System.out.print("Enter your choice: ");

			int choice = scanner.nextInt();

			if (choice == 3) {
				System.out.println("Thank you for using Temperature Converter!");
				break;
			}

			double temperature;

			if (choice == 1) {
				System.out.print("Enter temperature in Fahrenheit: ");
				temperature = scanner.nextDouble();
				double celsius = (temperature - 32) * 5 / 9;
				System.out.println("Temperature in Celsius: " + celsius + "\n");
			} else if (choice == 2) {
				System.out.print("Enter temperature in Celsius: ");
				temperature = scanner.nextDouble();
				double fahrenheit = (temperature * 9 / 5) + 32;
				System.out.println("Temperature in Fahrenheit: " + fahrenheit + "\n");
			} else {
				System.out.println("Invalid choice. Please choose 1, 2, or 3.\n");
			}
		}

		scanner.close();
	}
}
