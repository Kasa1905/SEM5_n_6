import java.io.*;

public class MPass2 {
    public static void main(String[] args) throws IOException {
        mdt[] MDT = new mdt[50];
        mnt[] MNT = new mnt[20];
        arglist[] formal_parameter = new arglist[50];
        arglist[] actual_parameter = new arglist[50];
        int mdt_cnt = 0, mnt_cnt = 0, formal_arglist_cnt = 0, actual_arglist_cnt = 0;
        int macro_call = -1, macro_addr = -1;
        String line;

        BufferedReader brl = new BufferedReader(new FileReader("MNT.txt"));
        while ((line = brl.readLine()) != null) {
            String[] parts = line.trim().split("\\s+");
            if (parts.length >= 2) {
                String name = parts[0];
                int addr = Integer.parseInt(parts[1]);
                MNT[mnt_cnt++] = new mnt(name, addr);
            }
        }
        brl.close();

        System.out.println("\n\t****** Macro Name Table ******");
        System.out.println("\n\tIndex\tName\tAddress");
        for (int i = 0; i < mnt_cnt; i++) {
            System.out.println("\t" + i + "\t" + MNT[i].name + "\t" + MNT[i].address);
        }

        brl = new BufferedReader(new FileReader("ARGLIST.txt"));
        while ((line = brl.readLine()) != null) {
            line = line.trim();
            if (line.length() == 0) continue;
            formal_parameter[formal_arglist_cnt++] = new arglist(line);
        }
        brl.close();

        System.out.println("\n\n\t****** Formal Argument List ******");
        System.out.println("\n\tIndex\tName");
        for (int i = 0; i < formal_arglist_cnt; i++) {
            System.out.println("\t" + i + "\t" + formal_parameter[i].argname);
        }

        brl = new BufferedReader(new FileReader("MDT.txt"));
        while ((line = brl.readLine()) != null) {
            MDT[mdt_cnt] = new mdt();
            MDT[mdt_cnt++].stm = line.trim();
        }
        brl.close();

        System.out.println("\n\t****** Macro Definition Table ******");
        System.out.println("\n\tIndex\t\tStatement");
        for (int i = 0; i < mdt_cnt; i++) {
            System.out.println("\t" + i + "\t" + MDT[i].stm);
        }

        brl = new BufferedReader(new FileReader("Intermediate.txt"));
        BufferedWriter bwl = new BufferedWriter(new FileWriter("Output.txt"));
        while ((line = brl.readLine()) != null) {
            line = line.trim();
            if (line.length() == 0) continue;
            line = line.replaceAll(",", " ");
            String[] tokens = line.split("\\s+");
            boolean isMacroCall = false;

            for (int i = 0; i < mnt_cnt; i++) {
                if (tokens[0].equalsIgnoreCase(MNT[i].name)) {
                    macro_call = i;
                    isMacroCall = true;
                    actual_arglist_cnt = 0;
                    for (int k = 1; k < tokens.length; k++) {
                        if (tokens[k].contains("=")) {
                            String[] kv = tokens[k].split("=");
                            actual_parameter[actual_arglist_cnt++] = new arglist(kv[1]);
                        } else {
                            actual_parameter[actual_arglist_cnt++] = new arglist(tokens[k]);
                        }
                    }
                    break;
                }
            }

            if (!isMacroCall) {
                bwl.write(line + "\n");
            } else {
                macro_addr = MNT[macro_call].address;
                while (macro_addr < mdt_cnt) {
                    String stmt = MDT[macro_addr].stm;
                    if (stmt.equalsIgnoreCase("MEND")) break;
                    String[] temp_tokens = stmt.split("\\s+");
                    for (int t = 0; t < temp_tokens.length; t++) {
                        String temp = temp_tokens[t];
                        if (temp.matches("#[0-9]+")) {
                            int num = Integer.parseInt(temp.substring(1));
                            if (num - 1 < actual_arglist_cnt) {
                                bwl.write(actual_parameter[num - 1].argname);
                            }
                        } else {
                            bwl.write(temp);
                        }
                        if (t != temp_tokens.length - 1) bwl.write(" ");
                    }
                    bwl.write("\n");
                    macro_addr++;
                }
            }
        }

        brl.close();
        bwl.close();

        System.out.println("\n\n\t****** Actual Argument List ******");
        System.out.println("\n\tIndex\tName");
        for (int i = 0; i < actual_arglist_cnt; i++) {
            System.out.println("\t" + i + "\t" + actual_parameter[i].argname);
        }
    }
}
