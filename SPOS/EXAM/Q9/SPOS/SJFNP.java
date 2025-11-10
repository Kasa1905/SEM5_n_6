import java.util.Scanner;
public class SJFNP{
       public static void main(String[] args){
       try (Scanner sc = new Scanner(System.in)) {
              System.out.println("Enter the number of processes:");
              int n=sc.nextInt();
              int[] bt = new int[n];
              int[] ct = new int[n];
              int[] wt = new int[n];
              int[] tat = new int[n];
              int[] at = new int[n];
              boolean[] done = new boolean[n];
              for(int i=0;i<n;i++){
                     System.out.println("P"+(i+1)+" Arrival time:");
                     at[i]=sc.nextInt();
                     System.out.println("P"+(i+1)+" Burst time:");
                     bt[i]=sc.nextInt();
              }
              int complete=0;
              int time=0;
              while(complete != n){
                     int idx = -1;
                     int minBT = Integer.MAX_VALUE;
                     for(int j=0;j<n;j++){
                            if(!done[j] && at[j] <= time && bt[j] < minBT){
                                   minBT = bt[j];
                                   idx = j;
                            }
                     }
                     if(idx == -1){
                            time++;
                            continue;
                     }
                     time += bt[idx];
                     ct[idx] = time;
                     done[idx] = true;
                     complete++;
              }
       float totalwt=0,totalat=0;
       for(int i=0;i<n;i++){
              tat[i]=ct[i]-at[i];
              wt[i]=tat[i]-bt[i];
              totalwt+=wt[i];
              totalat+=tat[i];
       }
       System.out.println("P\tBT\tAT\tCT\tTAT\tWT");
       for(int i=0;i<n;i++){
              System.out.println("P"+(i+1)+"\t"+bt[i]+"\t"+at[i]+"\t"+ct[i]+"\t"+tat[i]+"\t"+wt[i]);
       }
       System.out.println("Average waiting time:"+(totalwt/n));
       System.out.println("Average turn around time:"+(totalat/n));
       }
}
}
