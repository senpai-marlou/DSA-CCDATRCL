import java.util.Scanner;

public class LabActivity {

	static Scanner scan = new Scanner(System.in);
	public static int maxLength;
	public static boolean isDuplicate = false;

	public static void main(String[] args) {

		while (true) {
			System.out.print("Enter array size (Max: 50) :: ");
			maxLength = scan.nextInt();

			if (maxLength > 50) {
				System.out.println("\nInvalid: Maximum input is only [50]\n");
			} else if (maxLength < 1) {
				System.out.println("\nInvalid: Input value between 1-50\n");
			} else {
				break; // Break the loop if valid input is provided
			}
		}
		System.out.print("\nEnter array elements :: \n");
		runProgram();
	}

	public static void runProgram() {
		int[] arr = new int[maxLength];
		// Input elements into the array
		for (int i = 0; i < maxLength; i++) {
			System.out.print("\nEnter arr[" + i + "] Element :: ");
			arr[i] = scan.nextInt();
		}
		System.out.println("\nStored Data in Array :: \n");
		for (int i = 0; i < maxLength; i++) {
			System.out.print(arr[i] + " ");
		}

		// Find and print duplicate elements
		System.out.print("\n\nDuplicate Values in Given Array are :: \n\n");

		boolean[] alreadyPrinted = new boolean[maxLength];
		for (int i = 0; i < maxLength; i++) {
			boolean isDuplicate = false; // Reset isDuplicate flag for each element
			for (int j = i + 1; j < maxLength; j++) {
				if (arr[i] == arr[j]) {
					isDuplicate = true;
					break;
				}
			}

			if (isDuplicate && !alreadyPrinted[i]) { // Check for duplicates and if not already printed
				System.out.print(arr[i] + " ");
				alreadyPrinted[i] = true; // Mark as printed
			}
		}

		boolean hasDuplicates = false; // Initialize a flag to check if there are any duplicates

		for (int i = 0; i < maxLength; i++) {
			if (alreadyPrinted[i]) {
				hasDuplicates = true;
				break;
			}
		}

		if (!hasDuplicates) {
			System.out.println("There are no duplicate values");
		}

		scan.close();

	}

}
