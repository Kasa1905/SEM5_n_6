import java.util.Scanner;

/**
 * Simple Math Calculator using JNI
 * Easy to compile and run with basic Java commands
 */
public class SimpleMathCalculator {
    
    // Load native library with cross-platform support
    static {
        try {
            String osName = System.getProperty("os.name").toLowerCase();
            System.out.println("Running on: " + osName);
            
            if (osName.contains("windows")) {
                System.loadLibrary("mathops"); // mathops.dll
            } else if (osName.contains("mac")) {
                System.loadLibrary("mathops"); // libmathops.dylib
            } else {
                System.loadLibrary("mathops"); // libmathops.so
            }
            
            System.out.println("✅ Native library loaded successfully!");
        } catch (UnsatisfiedLinkError e) {
            System.err.println("❌ Could not load native library: " + e.getMessage());
            System.err.println("Run: java -Djava.library.path=. SimpleMathCalculator");
            System.exit(1);
        }
    }
    
    // Native method declarations (implemented in C)
    public native double add(double a, double b);
    public native double subtract(double a, double b);
    public native double multiply(double a, double b);
    public native double divide(double a, double b);
    
    public static void main(String[] args) {
        SimpleMathCalculator calc = new SimpleMathCalculator();
        Scanner input = new Scanner(System.in);
        
        System.out.println("🧮 Simple Math Calculator (JNI Demo)");
        System.out.println("=====================================");
        
        while (true) {
            try {
                // Display menu
                System.out.println("\nChoose an operation:");
                System.out.println("1. Addition (+)");
                System.out.println("2. Subtraction (-)");
                System.out.println("3. Multiplication (×)");
                System.out.println("4. Division (÷)");
                System.out.println("5. Exit");
                System.out.print("Enter your choice (1-5): ");
                
                int choice = input.nextInt();
                
                if (choice == 5) {
                    System.out.println("👋 Goodbye!");
                    break;
                }
                
                if (choice < 1 || choice > 4) {
                    System.out.println("❌ Invalid choice. Please try again.");
                    continue;
                }
                
                // Get numbers
                System.out.print("Enter first number: ");
                double num1 = input.nextDouble();
                System.out.print("Enter second number: ");
                double num2 = input.nextDouble();
                
                double result = 0;
                String operation = "";
                
                // Perform calculation using native methods
                switch (choice) {
                    case 1:
                        result = calc.add(num1, num2);
                        operation = "Addition";
                        break;
                    case 2:
                        result = calc.subtract(num1, num2);
                        operation = "Subtraction";
                        break;
                    case 3:
                        result = calc.multiply(num1, num2);
                        operation = "Multiplication";
                        break;
                    case 4:
                        if (num2 == 0) {
                            System.out.println("❌ Error: Cannot divide by zero!");
                            continue;
                        }
                        result = calc.divide(num1, num2);
                        operation = "Division";
                        break;
                }
                
                // Display result
                System.out.println("─".repeat(40));
                System.out.println("🔢 " + operation + " Result:");
                System.out.printf("%.2f %s %.2f = %.6f%n", num1, getOperatorSymbol(choice), num2, result);
                System.out.println("─".repeat(40));
                
            } catch (Exception e) {
                System.out.println("❌ Invalid input. Please enter valid numbers.");
                input.nextLine(); // Clear invalid input
            }
        }
        
        input.close();
    }
    
    private static String getOperatorSymbol(int choice) {
        switch (choice) {
            case 1: return "+";
            case 2: return "-";
            case 3: return "×";
            case 4: return "÷";
            default: return "?";
        }
    }
}