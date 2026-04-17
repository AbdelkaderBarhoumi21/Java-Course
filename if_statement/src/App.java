import java.util.Scanner;

public class App {
    public static void main(String[] args) throws Exception {
        Scanner scanner = new Scanner(System.in);
        // Example 1: Simple if statement
        // Checks a single condition and executes the block if true
        int age = 20;
        if (age >= 18) {
            System.out.println("Example 1: You are an adult.");
        }

        // Example 2: if-else statement
        // Executes one block if true, another if false
        int temperature = 15;
        if (temperature > 25) {
            System.out.println("Example 2: It's hot outside.");
        } else {
            System.out.println("Example 2: It's cool outside.");
        }

        // Example 3: if-else if-else ladder (grading system)
        // Evaluates multiple conditions in order until one matches
        int score = 85;
        char grade;
        if (score >= 90) {
            grade = 'A';
        } else if (score >= 80) {
            grade = 'B';
        } else if (score >= 70) {
            grade = 'C';
        } else if (score >= 60) {
            grade = 'D';
        } else {
            grade = 'F';
        }
        System.out.println("Example 3: Your grade is " + grade);

        // Example 4: Nested if statements
        // An if inside another if — inner runs only if outer is true
        int accountBalance = 500;
        boolean isVerified = true;
        if (isVerified) {
            if (accountBalance > 100) {
                System.out.println("Example 4: Withdrawal allowed.");
            } else {
                System.out.println("Example 4: Insufficient funds.");
            }
        } else {
            System.out.println("Example 4: Account not verified.");
        }

        // Example 5: Logical operators (&& AND, || OR, ! NOT)
        // Combine multiple conditions into one expression
        int hour = 14;
        boolean isWeekend = false;
        if (hour >= 9 && hour <= 17 && !isWeekend) {
            System.out.println("Example 5: Office is open.");
        } else {
            System.out.println("Example 5: Office is closed.");
        }

        // Example 6: Ternary operator (shorthand if-else)
        // condition ? valueIfTrue : valueIfFalse

        int number = 7;
        String parity = (number % 2 == 0) ? "even" : "odd";
        System.out.println("Example 6: " + number + " is " + parity);

        // Example 7: String comparison with .equals()
        // Never use == for strings — it compares references, not content
        String password = "java123";
        String input = "java123";
        if (input.equals(password)) {
            System.out.println("Example 7: Access granted.");
        } else {
            System.out.println("Example 7: Access denied.");
        }

        // Example 8: Leap year check (advanced logic)
        // A year is a leap year if divisible by 4, except century years
        // which must also be divisible by 400
        int year = 2024;
        boolean isLeap;
        if (year % 4 == 0) {
            if (year % 100 == 0) {
                isLeap = (year % 400 == 0);
            } else {
                isLeap = true;
            }
        } else {
            isLeap = false;
        }
        System.out.println("Example 8: " + year + (isLeap ? " is" : " is not") + " a leap year.");

        // Example 9: Interactive input with range validation
        // Demonstrates combining Scanner input with if-else logic
        System.out.print("Example 9: Enter your age: ");
        int userAge = scanner.nextInt();
        if (userAge < 0) {
            System.out.println("Invalid age entered.");
        } else if (userAge < 13) {
            System.out.println("You are a child.");
        } else if (userAge < 20) {
            System.out.println("You are a teenager.");
        } else if (userAge < 65) {
            System.out.println("You are an adult.");
        } else {
            System.out.println("You are a senior.");
        }

        // Example 10: BMI calculator (real-world advanced example)
        // Uses multiple conditions and formatted output
        System.out.print("Example 10: Enter your weight (kg): ");
        double weight = scanner.nextDouble();
        System.out.print("Enter your height (m): ");
        double height = scanner.nextDouble();
        double bmi = weight / (height * height);
        System.out.printf("Your BMI is: %.2f%n", bmi);

        if (bmi < 18.5) {
            System.out.println("Category: Underweight");
        } else if (bmi < 25) {
            System.out.println("Category: Normal weight");
        } else if (bmi < 30) {
            System.out.println("Category: Overweight");
        } else {
            System.out.println("Category: Obese");
        }


        scanner.close();
    }
}
