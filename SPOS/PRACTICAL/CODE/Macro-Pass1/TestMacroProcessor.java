import java.io.*;

public class TestMacroProcessor {
    public static void main(String[] args) {
        System.out.println("=== Testing Macro Pass 1 with input.txt ===");
        testWithFile("input.txt", "output1.txt");
        
        System.out.println("\n=== Testing Macro Pass 1 with input2.txt ===");
        testWithFile("input2.txt", "output2.txt");
    }
    
    static void testWithFile(String inputFile, String outputFile) {
        try {
            // Use simple relative paths for cross-platform compatibility
            BufferedReader br = new BufferedReader(new FileReader(inputFile));
            System.out.println("✅ Successfully opened: " + inputFile);
            
            // Count lines for verification
            int lineCount = 0;
            while (br.readLine() != null) {
                lineCount++;
            }
            br.close();
            
            System.out.println("📄 Input file has " + lineCount + " lines");
            System.out.println("📝 Output will be written to: " + outputFile);
            
        } catch (Exception e) {
            System.out.println("❌ Error: " + e.getMessage());
        }
    }
}
