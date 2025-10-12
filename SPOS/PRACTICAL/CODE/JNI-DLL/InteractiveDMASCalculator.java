import java.util.InputMismatchException;
import java.util.Scanner;

/**
 * Interactive DMAS Calculator using MathOperations JNI Library
 * Supports Division, Multiplication, Addition, and Subtraction with user input
 */
public class InteractiveDMASCalculator {
    
    private static MathOperations math;
    private static Scanner scanner;
    
    public static void main(String[] args) {
        try {
            // Initialize the math operations library
            math = new MathOperations();
            scanner = new Scanner(System.in);
            
            // Display welcome message
            displayWelcome();
            
            // Main program loop
            boolean continueCalculating = true;
            while (continueCalculating) {
                try {
                    // Show menu and get user choice
                    int choice = displayMenuAndGetChoice();
                    
                    if (choice == 5) {
                        continueCalculating = false;
                        System.out.println("\nThank you for using the DMAS Calculator!");
                        break;
                    }
                    
                    // Get numbers from user
                    double[] numbers = getTwoNumbers();
                    double num1 = numbers[0];
                    double num2 = numbers[1];
                    
                    // Perform the selected operation
                    performOperation(choice, num1, num2);
                    
                    // Ask if user wants to continue
                    continueCalculating = askToContinue();
                    
                } catch (Exception e) {
                    System.err.println("Error: " + e.getMessage());
                    System.out.println("Please try again.\n");
                }
            }
            
        } catch (Exception e) {
            System.err.println("Failed to initialize calculator: " + e.getMessage());
            System.err.println("Make sure the mathops library is properly installed.");
        } finally {
            if (scanner != null) {
                scanner.close();
            }
        }
    }
    
    /**
     * Display welcome message and library information
     */
    private static void displayWelcome() {
        System.out.println("=" .repeat(60));
        System.out.println("        INTERACTIVE DMAS CALCULATOR");
        System.out.println("    Using MathOperations JNI Library v" + math.getLibraryVersion());
        System.out.println("=" .repeat(60));
        System.out.println("This calculator supports:");
        System.out.println("  D - Division");
        System.out.println("  M - Multiplication"); 
        System.out.println("  A - Addition");
        System.out.println("  S - Subtraction");
        System.out.println("=" .repeat(60));
    }
    
    /**
     * Display operation menu and get user's choice
     */
    private static int displayMenuAndGetChoice() {
        System.out.println("\nSelect an operation:");
        System.out.println("1. Addition (+)");
        System.out.println("2. Subtraction (-)");
        System.out.println("3. Multiplication (×)");
        System.out.println("4. Division (÷)");
        System.out.println("5. Exit");
        
        int choice = 0;
        boolean validInput = false;
        
        while (!validInput) {
            System.out.print("\nEnter your choice (1-5): ");
            try {
                choice = scanner.nextInt();
                if (choice >= 1 && choice <= 5) {
                    validInput = true;
                } else {
                    System.out.println("Invalid choice! Please enter a number between 1 and 5.");
                }
            } catch (InputMismatchException e) {
                System.out.println("Invalid input! Please enter a valid number.");
                scanner.next(); // Clear the invalid input
            }
        }
        
        return choice;
    }
    
    /**
     * Get two numbers from the user
     */
    private static double[] getTwoNumbers() {
        double[] numbers = new double[2];
        
        System.out.println("\nEnter the numbers:");
        
        // Get first number
        boolean validInput = false;
        while (!validInput) {
            System.out.print("First number: ");
            try {
                numbers[0] = scanner.nextDouble();
                validInput = true;
            } catch (InputMismatchException e) {
                System.out.println("Invalid input! Please enter a valid number.");
                scanner.next(); // Clear the invalid input
            }
        }
        
        // Get second number
        validInput = false;
        while (!validInput) {
            System.out.print("Second number: ");
            try {
                numbers[1] = scanner.nextDouble();
                validInput = true;
            } catch (InputMismatchException e) {
                System.out.println("Invalid input! Please enter a valid number.");
                scanner.next(); // Clear the invalid input
            }
        }
        
        return numbers;
    }
    
    /**
     * Perform the selected mathematical operation
     */
    private static void performOperation(int choice, double num1, double num2) {
        double result = 0;
        String operation = "";
        String symbol = "";
        
        try {
            switch (choice) {
                case 1: // Addition
                    result = math.add(num1, num2);
                    operation = "Addition";
                    symbol = "+";
                    break;
                    
                case 2: // Subtraction
                    result = math.subtract(num1, num2);
                    operation = "Subtraction";
                    symbol = "-";
                    break;
                    
                case 3: // Multiplication
                    result = math.multiply(num1, num2);
                    operation = "Multiplication";
                    symbol = "×";
                    break;
                    
                case 4: // Division
                    result = math.divide(num1, num2);
                    operation = "Division";
                    symbol = "÷";
                    break;
                    
                default:
                    System.out.println("Invalid operation!");
                    return;
            }
            
            // Display the result
            System.out.println("\n" + "-".repeat(40));
            System.out.println("Operation: " + operation);
            System.out.printf("Calculation: %.6f %s %.6f = %.6f%n", num1, symbol, num2, result);
            System.out.println("-".repeat(40));
            
            // Display formatted result
            if (result == (long) result) {
                System.out.printf("Result: %.0f%n", result);
            } else {
                System.out.printf("Result: %.6f%n", result);
            }
            
        } catch (ArithmeticException e) {
            System.out.println("\n" + "!".repeat(40));
            System.out.println("ARITHMETIC ERROR: " + e.getMessage());
            System.out.println("!".repeat(40));
            
            if (choice == 4 && num2 == 0) {
                System.out.println("Tip: Division by zero is undefined in mathematics.");
                System.out.println("Please try again with a non-zero divisor.");
            }
            
        } catch (Exception e) {
            System.out.println("\n" + "!".repeat(40));
            System.out.println("ERROR: " + e.getMessage());
            System.out.println("!".repeat(40));
        }
    }
    
    /**
     * Ask user if they want to continue calculating
     */
    private static boolean askToContinue() {
        System.out.print("\nDo you want to perform another calculation? (y/n): ");
        
        while (true) {
            String response = scanner.next().toLowerCase().trim();
            
            if (response.equals("y") || response.equals("yes")) {
                return true;
            } else if (response.equals("n") || response.equals("no")) {
                return false;
            } else {
                System.out.print("Please enter 'y' for yes or 'n' for no: ");
            }
        }
    }
}