import java.io.*;

public class MacroPass1 {
    
    static int search(String key, String[] list) {
        for (int i = 0; i < list.length; i++) {
            if (list[i] != null && list[i].equalsIgnoreCase(key)) return i;
        }
        return -1;
    }
    
    static int searchMacro(String key, MacroNameTable[] mnt, int count) {
        for (int i = 0; i < count; i++) {
            if (mnt[i].name.equalsIgnoreCase(key)) return i;
        }
        return -1;
    }

    public static void main(String[] args) {
        String[] IS = {"stop", "add", "sub", "mult", "mover", "movem", "comp", "bc", "div", "read", "print"};
        String[] DL = {"ds", "dc"};
        String[] AD = {"start", "end", "origin", "equ", "ltorg"};
        
        MacroNameTable[] mnt = new MacroNameTable[10];
        MacroDefTable[] mdt = new MacroDefTable[50];
        
        int mntIndex = 0, mdtIndex = 0;
        boolean insideMacro = false;
        int currentMacroParams = 0;
        
        try {
            // Use relative path for cross-platform compatibility
            String inputFile = "input.txt";
            String outputFile = "output.txt";
            
            BufferedReader br = new BufferedReader(new FileReader(inputFile));
            BufferedWriter bw = new BufferedWriter(new FileWriter(outputFile));
            String line;
            
            System.out.println("Starting Macro Pass 1...");
            
            while ((line = br.readLine()) != null) {
                line = line.trim();
                if (line.isEmpty()) continue;
                
                String[] tokens = line.split("\\s+");
                
                // Check for MACRO directive
                if (tokens[0].equalsIgnoreCase("MACRO")) {
                    insideMacro = true;
                    continue;
                }
                
                // Process macro definition
                if (insideMacro) {
                    // Check for MEND first
                    if (tokens[0].equalsIgnoreCase("MEND")) {
                        mdt[mdtIndex++] = new MacroDefTable("MEND");
                        insideMacro = false;
                        currentMacroParams = 0;
                        continue;
                    }
                    
                    // First line after MACRO is macro name and parameters
                    if (currentMacroParams == 0 && tokens.length > 0) {
                        // This is macro name line
                        currentMacroParams = tokens.length - 1; // Count parameters
                        mnt[mntIndex] = new MacroNameTable(tokens[0], mdtIndex, currentMacroParams);
                        mntIndex++;
                    }
                    
                    // Store all macro definition lines (including name line)
                    mdt[mdtIndex++] = new MacroDefTable(line);
                    continue;
                }
                
                // Process macro calls and regular instructions
                int macroIndex = searchMacro(tokens[0], mnt, mntIndex);
                if (macroIndex != -1) {
                    // This is a macro call - expand it
                    bw.write("+ " + line + "\n"); // Mark as expanded line
                    
                    // Get macro definition
                    MacroNameTable macro = mnt[macroIndex];
                    String[] actualParams = new String[tokens.length - 1];
                    for (int i = 1; i < tokens.length; i++) {
                        actualParams[i - 1] = tokens[i];
                    }
                    
                    // Get formal parameters from macro definition line
                    String[] macroDefTokens = mdt[macro.startIndex].instruction.split("\\s+");
                    String[] formalParams = new String[macroDefTokens.length - 1];
                    for (int i = 1; i < macroDefTokens.length; i++) {
                        formalParams[i - 1] = macroDefTokens[i];
                    }
                    
                    // Expand macro body
                    for (int i = macro.startIndex + 1; i < mdtIndex; i++) {
                        String mdtLine = mdt[i].instruction;
                        if (mdtLine.equals("MEND")) break;
                        
                        // Parameter substitution
                        String expandedLine = mdtLine;
                        for (int j = 0; j < formalParams.length && j < actualParams.length; j++) {
                            String formalParam = formalParams[j];
                            String actualParam = actualParams[j];
                            // Remove & from formal parameter if present and do exact replacement
                            if (formalParam.startsWith("&")) {
                                expandedLine = expandedLine.replace(formalParam, actualParam);
                            } else {
                                expandedLine = expandedLine.replaceAll("\\b" + formalParam + "\\b", actualParam);
                            }
                        }
                        
                        bw.write("+ " + expandedLine + "\n");
                    }
                } else {
                    // Regular instruction - copy as is
                    bw.write("  " + line + "\n");
                }
            }
            
            // Write Macro Name Table
            bw.write("\n\nMACRO NAME TABLE (MNT):\n");
            bw.write("INDEX\tNAME\tMDT_START\tPARAMS\n");
            for (int i = 0; i < mntIndex; i++) {
                bw.write((i + 1) + "\t" + mnt[i].name + "\t" + (mnt[i].startIndex + 1) + "\t\t" + mnt[i].numParams + "\n");
            }
            
            // Write Macro Definition Table
            bw.write("\nMACRO DEFINITION TABLE (MDT):\n");
            bw.write("INDEX\tINSTRUCTION\n");
            for (int i = 0; i < mdtIndex; i++) {
                bw.write((i + 1) + "\t" + mdt[i].instruction + "\n");
            }
            
            br.close();
            bw.close();
            
            System.out.println("✅ Macro Pass 1 complete. Check output.txt");
            System.out.println("Macros processed: " + mntIndex);
            System.out.println("MDT entries: " + mdtIndex);
            
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
            e.printStackTrace();
        }
    }
}
