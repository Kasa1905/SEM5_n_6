import java.io.IOException;

/**
 * MathOperations - Java class for DMAS operations using JNI
 * Supports Division, Multiplication, Addition, and Subtraction
 * Auto-generates JNI header file on each run
 */
public class MathOperations {
    
    // Load the native library
    static {
        try {
            // Auto-generate JNI header file
            generateJNIHeader();
            
            System.loadLibrary("mathops"); // This will load libmathops.dylib (macOS), libmathops.so (Linux) or mathops.dll (Windows)
        } catch (UnsatisfiedLinkError e) {
            System.err.println("Failed to load native library 'mathops': " + e.getMessage());
            System.err.println("Make sure the library is in your PATH or java.library.path");
            System.exit(1);
        } catch (Exception e) {
            System.err.println("Error during initialization: " + e.getMessage());
        }
    }
    
    // DMAS Operations - Division, Multiplication, Addition, Subtraction
    public native double add(double a, double b);
    public native double subtract(double a, double b);
    public native double multiply(double a, double b);
    public native double divide(double a, double b);
    
    // Method to get library version
    public native String getLibraryVersion();
    
    /**
     * Auto-generate JNI header file
     */
    private static void generateJNIHeader() {
        try {
            System.out.println("Generating JNI header file...");
            
            // Get the current directory
            String currentDir = System.getProperty("user.dir");
            
            // Execute javac -h command to generate header
            ProcessBuilder pb = new ProcessBuilder(
                "javac", "-h", currentDir, 
                currentDir + "/MathOperations.java"
            );
            
            Process process = pb.start();
            int exitCode = process.waitFor();
            
            if (exitCode == 0) {
                System.out.println("✓ JNI header file generated successfully!");
            } else {
                System.out.println("Warning: Header generation may have issues (exit code: " + exitCode + ")");
            }
            
        } catch (IOException | InterruptedException e) {
            System.out.println("Note: Could not auto-generate header file: " + e.getMessage());
            System.out.println("You may need to run: javac -h . MathOperations.java");
        }
    }
    
    /**
     * Main method to test library loading and header generation
     */
    public static void main(String[] args) {
        System.out.println("MathOperations DMAS Library");
        System.out.println("Supports: Division, Multiplication, Addition, Subtraction");
        System.out.println("Library loaded successfully!");
    }
}