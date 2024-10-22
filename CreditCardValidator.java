import java.util.Scanner;

public class CreditCardValidator {

    private String ccNumber;  // Credit card number

    // Constructor to read the credit card number
    public CreditCardValidator(String ccNumber) {
        this.ccNumber = ccNumber;
        validateCard();
    }

    // Method to validate the credit card number
    public void validateCard() {
        int length = ccNumber.length();

        // Step 1: Check if the credit card number is within the valid range
        if (length < 8 || length > 9) {
            System.out.println("Invalid credit card number");
            return;
        }

        // Using a switch-case to manage each step from a to f
        int lastDigit = 0;
        String remainingDigits = "";
        String reversedDigits = "";
        StringBuilder processedDigits = new StringBuilder();
        int sum = 0;
        int checkDigit = 0;

        for (int step = 0; step <= 5; step++) {
            switch (step) {
                case 0:  // Step a: Remove the last digit of the ccNumber
                    lastDigit = Character.getNumericValue(ccNumber.charAt(length - 1));
                    remainingDigits = ccNumber.substring(0, length - 1);
                    System.out.println("Step a: Last digit = " + lastDigit + ", Remaining number = " + remainingDigits);
                    break;

                case 1:  // Step b: Reverse the remaining digits
                    reversedDigits = new StringBuilder(remainingDigits).reverse().toString();
                    System.out.println("Step b: Reversed number = " + reversedDigits);
                    break;

                case 2:  // Step c: Double the digits in odd-numbered positions
                    for (int i = 0; i < reversedDigits.length(); i++) {
                        int digit = Character.getNumericValue(reversedDigits.charAt(i));
                        if (i % 2 == 0) {  // odd-numbered positions (1st, 3rd, 5th, etc.)
                            digit *= 2;
                            if (digit > 9) {  // Add the digits of the result if it's a double-digit number
                                digit = digit / 10 + digit % 10;
                            }
                        }
                        processedDigits.append(digit);
                    }
                    System.out.println("Step c: After doubling odd-positioned digits = " + processedDigits);
                    break;

                case 3:  // Step d: Add up all the digits
                    for (int i = 0; i < processedDigits.length(); i++) {
                        sum += Character.getNumericValue(processedDigits.charAt(i));
                    }
                    System.out.println("Step d: Sum of all digits = " + sum);
                    break;

                case 4:  // Step e: Subtract the last digit obtained in step a from 10
                    checkDigit = 10 - (sum % 10);
                    System.out.println("Step e: 10 - (Sum % 10) = " + checkDigit);
                    break;

                case 5:  // Step f: Compare the result of step e with the last digit from step a
                    if (checkDigit == lastDigit) {
                        System.out.println("Step f: Valid credit card number");
                    } else {
                        System.out.println("Step f: Invalid credit card number");
                    }
                    break;
            }
        }
    }

    // Main method to test the functionality
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter the credit card number: ");
        String ccNumber = scanner.nextLine();

        // Create an instance of CreditCardValidator and validate the credit card
        new CreditCardValidator(ccNumber);

        scanner.close();
    }
}