
abstract class Analyzer {
	// Abstract method to calculate statistics
	public abstract void calculate(int[] grades);
}

//Concrete implementation of Analyzer
class GradeAnalyzer extends Analyzer {
	public void calculate(int[] grades) {
		int sum = 0;
		int max = grades[0];
		int min = grades[0];

		for (int i = 0; i < grades.length; i++) {
			int grade = grades[i];
			sum += grade;
			if (grade > max) {
				max = grade;
			}
			if (grade < min) {
				min = grade;
			}
		}

		double avg = (double) sum / grades.length;
		System.out.println("Average Grade: " + avg);
		System.out.println("Maximum Grade: " + max);
		System.out.print("Minimum Grade: " + min);
	}
}