// Jhon Marlou Tobello | INF 226

#include <iostream>
using namespace std;

int main() {

	int choice;

	double value;
	double valid = true;

	cout << "------------ Unit Converter ------------" << endl << endl;
	cout << "[1] Temperature Converter (Celsius to Fahrenheit)" << endl
		 << "[2] Length Converter (Meters to Feet)" << endl
		 << "[3] Weight Converter (Kilograms to Pounds)" << endl
		 << "[4] Exit" << endl << endl << "Choose Converter: ";
	cin >> choice;

	while(valid) {

		if (choice == 1) {
			cout << "Enter temperature in Celsius: ";
			cin >> value;
			// Perform the Celsius to Fahrenheit conversion
			cout << "Equivalent temperature in Fahrenheit: " << (value * 9/5) + 32 << "°F" << endl << endl;
		} else if (choice == 2) {
			cout << "Enter length in meters: ";
			cin >> value;
			// Perform the Length to Meters conversion
			cout << "Equivalent length in feet: " << value * 3.28084 << " ft" << endl << endl;
		} else if (choice == 3) {
			cout << "Enter weight in kilograms: ";
			cin >> value;
			// Perform the Kilograms to Pounds conversion
			cout << "Equivalent weight in Pounds: " << value * 2.20462 << " lbs" << endl << endl;
		} else if (choice == 4) {
			cout << "Thank you for using Unit Converter";
			break;
		} else {
			cout << "Invalid choice." << endl << endl;

		}
		cout << "Choose Converter: ";
		cin >> choice;
	}
	return 0;
}
