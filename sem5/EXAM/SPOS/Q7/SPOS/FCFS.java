import java.util.Scanner;

public class FCFS {
       public static void main(String[] args) {
       try (Scanner sc = new Scanner(System.in)) {
              System.out.println("Enter the number of processes:");
              int n = sc.nextInt();
              int[] bt = new int[n];
              int[] ct = new int[n];
              int[] wt = new int[n];
              int[] tat = new int[n];
              int[] at = new int[n];
              System.out.println("Enter number of processes:");
              for (int i = 0; i < n; i++) {
                     System.out.println("P" + (i + 1) + " Arrival time:");
                     at[i] = sc.nextInt();
                     System.out.println("P" + (i + 1) + " Burst time:");
                     bt[i] = sc.nextInt();
              }
              for (int i = 0; i < n; i++) {
                     if (i == 0) {
                            ct[i] = at[i] + bt[i];
                     } else {
                            if (at[i] > ct[i - 1]) {
                                   ct[i] = at[i] + bt[i];
                            } else {
                                   ct[i] = ct[i - 1] + bt[i];
                            }
                     }
                     tat[i] = ct[i] - at[i];
                     wt[i] = tat[i] - bt[i];
              }
              float totalwt = 0, totalat = 0;
              System.out.println("P\tBT\tAT\tCT\tTAT\tWT");
              for (int i = 0; i < n; i++) {
                     totalwt += wt[i];
                     totalat += tat[i];
                     System.out.println(
                                   "P" + (i + 1) + "\t" + bt[i] + "\t" + at[i] + "\t" + ct[i] + "\t" + tat[i]
                                                 + "\t" + wt[i]);
              }
              System.out.println("Average waiting time:" + (totalwt / n));
              System.out.println("Average turn around time:" + (totalat / n));
              }
       }
}
