
import java.util.Scanner;

public class PriorityNP {
       public static void main(String[] args) {
       try (Scanner sc = new Scanner(System.in)) {
              System.out.println("Enter the number of processes:");
              int n = sc.nextInt();
              int[] bt = new int[n];
              int[] ct = new int[n];
              int[] wt = new int[n];
              int[] tat = new int[n];
              int[] at = new int[n];
              int[] pr = new int[n];
              boolean[] isCompleted = new boolean[n];
              System.out.println("Enter number of processes:");
              for (int i = 0; i < n; i++) {
                     System.out.println("P" + (i + 1) + " Arrival time:");
                     at[i] = sc.nextInt();
                     System.out.println("P" + (i + 1) + " Burst time:");
                     bt[i] = sc.nextInt();
                     System.out.println("P" + (i + 1) + " Priority (lower is higher):");
                     pr[i] = sc.nextInt();
              }
              int t = 0;
              int complete = 0;
              while (complete < n) {          
                     int idx = -1;
                     int highestPr = Integer.MAX_VALUE;
                     for (int i = 0; i < n; i++) {
                            if (!isCompleted[i] && at[i] <= t && pr[i] < highestPr) {
                                   highestPr = pr[i];
                                   idx = i;
                            }
                     }
                     if(idx == -1){
                            t++;
                            continue;
                     }
                     wt[idx]=t-at[idx];
                     t+=bt[idx];
                     ct[idx]=t;
                     tat[idx]=ct[idx]-at[idx];
                     isCompleted[idx]=true;
                     complete++;
              }
       float totalwt=0,totalat=0;
       System.out.println("P\tBT\tAT\tPR\tCT\tTAT\tWT");
       for(int i=0;i<n;i++){
              totalwt+=wt[i];
              totalat+=tat[i];
              System.out.println("P"+(i+1)+"\t"+bt[i]+"\t"+at[i]+"\t"+pr[i]+"\t"+ct[i]+"\t"+tat[i]+"\t"+wt[i]);
       }
       System.out.println("Average WT: "+(totalwt/n));
       System.out.println("Average TAT: "+(totalat/n));
       }
}
}
