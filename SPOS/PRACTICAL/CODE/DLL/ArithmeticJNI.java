package PRACTICAL.CODE.DLL;

import java.util.*;
public class ArithmeticJNI {
    // Declare native methods
    public native int add(int a, int b);
    public native int subtract(int a, int b);
    public native int multiply(int a, int b);
    public native double divide(int a, int b);

    // Load the native library (cross-platform)
    static {
        try {
            String os = System.getProperty("os.name").toLowerCase();
            String arch = System.getProperty("os.arch").toLowerCase();
            
            System.out.println("Detected OS: " + os + " (" + arch + ")");
            
            if (os.contains("win")) {
                System.out.println("Loading Windows DLL...");
                System.loadLibrary("ArithmeticLib"); // Windows: ArithmeticLib.dll
            } else if (os.contains("mac")) {
                System.out.println("Loading macOS dylib...");
                System.loadLibrary("ArithmeticLib"); // macOS: libArithmeticLib.dylib
            } else if (os.contains("nix") || os.contains("nux") || os.contains("aix")) {
                System.out.println("Loading Linux shared library...");
                System.loadLibrary("ArithmeticLib"); // Linux: libArithmeticLib.so
            } else {
                throw new UnsupportedOperationException("Unsupported operating system: " + os);
            }
            
            System.out.println("✅ Native library loaded successfully!");
            
        } catch (UnsatisfiedLinkError e) {
            System.err.println("❌ Failed to load native library: " + e.getMessage());
            System.err.println("Make sure the appropriate library file exists:");
            System.err.println("  Windows: ArithmeticLib.dll");
            System.err.println("  macOS: libArithmeticLib.dylib");
            System.err.println("  Linux: libArithmeticLib.so");
            throw e;
        }
    }

    public static void main(String[] args) {
        ArithmeticJNI obj = new ArithmeticJNI();
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter Any two no.:");
        int x=sc.nextInt();
        int y=sc.nextInt();

        System.out.println("Addition: " + obj.add(x, y));
        System.out.println("Subtraction: " + obj.subtract(x, y));
        System.out.println("Multiplication: " + obj.multiply(x, y));
        System.out.println("Division: " + obj.divide(x, y));
        sc.close();
    }
}