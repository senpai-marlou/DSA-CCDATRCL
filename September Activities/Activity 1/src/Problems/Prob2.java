package Problems;

import java.util.Scanner;

public class Prob2 {

	public static void main(String[] args) {

		// Step 1: Input the student's score
		Scanner scanner = new Scanner(System.in);

		while (true) {
			System.out.print("Enter the student's score: ");
			double score = scanner.nextDouble();

			// Step 2: Check for valid input range
			if (score < 0 || score > 100) {
				System.out.println("Error: Invalid score. Score must be between 0 and 100.");
				break;
			}

			// Step 3: Determine the grade point and grade description
			double gradePoint;
			String gradeDescription;

			if (score >= 96) {
				gradePoint = 4.00;
				gradeDescription = "Excellent";
			} else if (score >= 90) {
				gradePoint = 3.50;
				gradeDescription = "Very Good";
			} else if (score >= 84) {
				gradePoint = 3.00;
				gradeDescription = "Good";
			} else if (score >= 78) {
				gradePoint = 2.50;
				gradeDescription = "Above Satisfactory";
			} else if (score >= 72) {
				gradePoint = 2.00;
				gradeDescription = "Satisfactory";
			} else if (score >= 66) {
				gradePoint = 1.50;
				gradeDescription = "Fair";
			} else if (score >= 60) {
				gradePoint = 1.00;
				gradeDescription = "Passed";
			} else {
				gradePoint = 0.00;
				gradeDescription = "Repeat/Failed";
			}

			// Step 4: Display the result
			System.out.println("Grade Point: " + String.format("%.2f", gradePoint));
			System.out.println("Grade Description: " + gradeDescription + "\n");
		}
	}

}
