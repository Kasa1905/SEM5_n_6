public class MathCalculator {
    // Load native library
    static {
        System.loadLibrary("mathcalc");
    }
    
    // Declare native methods
    public native int add(int a, int b);
    public native int subtract(int a, int b);
    public native int multiply(int a, int b);
    public native double divide(double a, double b);
    
    public static void main(String[] args) {
        MathCalculator calc = new MathCalculator();
        
        System.out.println("=== Java Native Interface (JNI) Calculator ===");
        System.out.println("Addition: 15 + 25 = " + calc.add(15, 25));
        System.out.println("Subtraction: 50 - 20 = " + calc.subtract(50, 20));
        System.out.println("Multiplication: 8 * 7 = " + calc.multiply(8, 7));
        System.out.println("Division: 100.0 / 4.0 = " + calc.divide(100.0, 4.0));
        
        // Test with input data
        int[] nums1 = {10, 20, 30};
        int[] nums2 = {5, 15, 25};
        
        System.out.println("\n=== Testing with arrays ===");
        for (int i = 0; i < nums1.length; i++) {
            System.out.println(nums1[i] + " + " + nums2[i] + " = " + calc.add(nums1[i], nums2[i]));
            System.out.println(nums1[i] + " * " + nums2[i] + " = " + calc.multiply(nums1[i], nums2[i]));
        }
    }
}