
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;


public class pass2
{
    static Obj[] symbol_table = new Obj[10];
    static Obj[] literal_table = new Obj[10];

    static int symb_found = 0;

    public static void main(String[] args) throws IOException {
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter the total number of symbols = ");
        int total_symb = sc.nextInt();
        
        int pos, num;

        for (int i = 0 ; i < total_symb ; i++)
        {
            symbol_table[i] = new Obj("",0);
            System.out.print("Enter Symbol name = ");
            symbol_table[i].name = sc.next();
            System.out.print("Enter Symbol Address = ");
            symbol_table[i].address = sc.nextInt();


        }

        System.out.print("Enter the total number of literals = ");
        int total_ltr =  sc.nextInt();

        for ( int i = 0 ; i < total_ltr ; i++)
        {
            literal_table[i] = new Obj("",0);
            System.out.println("Enter literal name = ");
            literal_table[i].name = sc.next();

            System.out.println("Enter literal address = ");
            literal_table[i].address = sc.nextInt();

            
        }
        System.out.println("\n--------------------------SYMBOL TABLE----------------------------------");
            System.out.println("\nSymbol\nAddress");

            for (int i = 0 ; i < total_symb ; i++)
            {
                System.out.println(symbol_table[i].name + "\t" + symbol_table[i].address);


            }

        System.out.println("\n--------------------------LITERAL TABLE--------------------------------");
            System.out.println("\nLiteral\nAddress");
            for (int i = 0 ; i < total_ltr ; i++)
            {
                System.out.println(literal_table[i].name + "\t" + literal_table[i].address);


            }

        // Use cross-platform file path - reads Output.txt from Pass1 assembler
        String inputFile = "Output.txt";  // Read from Output.txt in current directory

        BufferedReader br2;
        try {
            br2 = new BufferedReader(new FileReader(inputFile));
        } catch (FileNotFoundException e) {
            System.err.println("Error: " + inputFile + " not found in current directory.");
            System.err.println("Please ensure the " + inputFile + " file exists and try again.");
            return;
        }
String line;
boolean symbol_error = false, undef_mnemonic = false;

System.out.println("\n*********************OUTPUT FILE***************************\n\n");

lab: while ((line = br2.readLine()) != null) {
    String[] token_list = line.split("\\s+", 5);
    symbol_error = undef_mnemonic = false;

    lab1: for (String token : token_list) {
        if (token.length()> 0) {
            pos = -1;

            if (token.matches("==")) {
                System.out.print("==");
                undef_mnemonic = true;
            } 
            else if (token.matches("[0-9]+")) { // LOCATION COUNTER
                System.out.print("\n\n" + token);
            } 
            else {
                String letters = token.replaceAll("[^A-Za-z]+", "");
                num = Integer.parseInt(token.replaceAll("[^0-9]+", ""));

                 if (token.matches("\\([0-9]+\\)")) {
                    System.out.println("\t" + num);
                    
                 }
                 else{
                    switch (letters.toUpperCase())
                    {
                        case "S" : if (num > 0 && num <= total_symb && symbol_table[num-1] != null) {
                                    if (symbol_table[num-1].address == 0) {
                                        System.out.print("\t---");
                                        symbol_error = true;
                                    } else {
                                        System.out.print("\t" + symbol_table[num-1].address);
                                    }
                                } else {
                                    System.out.print("\t---");
                                    symbol_error = true;
                                }
                        break;

                        case "L" : if (num > 0 && num <= total_ltr && literal_table[num-1] != null) {
                                    System.out.print("\t" + literal_table[num-1].address);
                                } else {
                                    System.out.print("\t---");
                                    symbol_error = true;
                                }
                        break;

                        case "AD" : System.out.print("\n");
                                    continue lab;
                        
                        case "DL" :
                                switch(num)
                                {
                                    case 1 : System.out.print("\n");
                                    continue lab;

                                    case 2 : System.out.print("\t 00 \t 00");
                                        continue lab1;

                                    
                                }

                        case "C":
                                        System.out.print("\t " + num);
                                        break;

                        default: System.out.print("\t" +"00" + num);

                    }
                 }
            }

               
        }
    }
}
if (symbol_error) {
    System.out.print("\n\n**************************SYMBOL IS NOT DEFINED**************************");
}

if (undef_mnemonic) {
    System.out.print("\n\n**************************INVALID MNEMONIC ******************************");
}

// Duplicate symbol checking logic
int[] flag = new int[total_symb];
for (int i = 0; i < total_symb; i++) {
    int symb_found = 0;
    for (int j = 0; j < total_symb; j++) {
        if (symbol_table[i].name.equalsIgnoreCase(symbol_table[j].name) && flag[j] == 0) {
            symb_found++;
            flag[j] = 1;
        }
    }
    if (symb_found > 1) {
        System.out.print("\n\n************************** '" + symbol_table[i].name + " IS DUPLICATE SYMBOL**************************");
    }
}

br2.close();
sc.close();


    }

}