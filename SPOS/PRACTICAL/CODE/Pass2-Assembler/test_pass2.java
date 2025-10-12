import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class test_pass2 {
    static Obj[] symbol_table = new Obj[10];
    static Obj[] literal_table = new Obj[10];

    public static void main(String[] args) throws IOException {
        // Test data - same as what you entered manually
        symbol_table[0] = new Obj("up", 102);
        symbol_table[1] = new Obj("a", 109);
        symbol_table[2] = new Obj("b", 121);
        symbol_table[3] = new Obj("c", 122);
        symbol_table[4] = new Obj("next", 102);

        literal_table[0] = new Obj("5", 102);
        literal_table[1] = new Obj("8", 105);
        literal_table[2] = new Obj("8", 106);
        literal_table[3] = new Obj("7", 132);
        literal_table[4] = new Obj("8", 133);

        int total_symb = 5;
        int total_ltr = 5;

        System.out.println("\n--------------------------SYMBOL TABLE----------------------------------");
        System.out.println("\nSymbol\tAddress");
        for (int i = 0; i < total_symb; i++) {
            System.out.println(symbol_table[i].name + "\t" + symbol_table[i].address);
        }

        System.out.println("\n--------------------------LITERAL TABLE--------------------------------");
        System.out.println("\nLiteral\tAddress");
        for (int i = 0; i < total_ltr; i++) {
            System.out.println(literal_table[i].name + "\t" + literal_table[i].address);
        }

        // Test file reading with relative path
        String inputFile = "input.txt";
        
        System.out.println("\nTesting file reading from: " + inputFile);
        
        try {
            BufferedReader br2 = new BufferedReader(new FileReader(inputFile));
            String line;
            int lineCount = 0;
            
            System.out.println("\n*********************INPUT FILE CONTENT (first 5 lines)***************************");
            while ((line = br2.readLine()) != null && lineCount < 5) {
                System.out.println("Line " + (lineCount + 1) + ": " + line);
                lineCount++;
            }
            br2.close();
            System.out.println("\n✅ File reading successful! The path issue is fixed.");
            
        } catch (IOException e) {
            System.out.println("\n❌ File reading failed: " + e.getMessage());
        }
    }
}
