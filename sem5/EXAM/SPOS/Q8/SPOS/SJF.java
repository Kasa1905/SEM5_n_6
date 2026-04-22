import java.util.Scanner;
public class SJF{
       public static void main(String[] args){
       try (Scanner sc = new Scanner(System.in)) {
              System.out.println("Enter the number of processes:");
              int n=sc.nextInt();
              int[] bt = new int[n];
              int[] ct = new int[n];
              int[] wt = new int[n];
              int[] tat = new int[n];
              int[] at = new int[n];
              int[] rt = new int[n];
              for(int i=0;i<n;i++){
                     System.out.println("P"+(i+1)+" Arrival time:");
                     at[i]=sc.nextInt();
                     System.out.println("P"+(i+1)+" Burst time:");
                     bt[i]=sc.nextInt();
                     rt[i]=bt[i];
              }
              int complete=0,t=0;
              while(complete!=n){
                     int minm=Integer.MAX_VALUE;
                     boolean check=false;
                     int shortest=0;
                     for(int j=0;j<n;j++){
                            if(at[j]<=t && rt[j]<minm && rt[j]>0){
                                   minm=rt[j];
                                   shortest=j;
                                   check=true;
                            }
                     }
                     if(!check){
                            t++;
                            continue;
                     }
                     rt[shortest]--;
                     if(rt[shortest]==0){
                            complete++;
                            ct[shortest]=t+1;
              }
              t++;
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
