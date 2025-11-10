package SPOS.EXAM.SPOS.Q18.SPOS;
import java.util.Scanner;
class bankers
{
    public static void main(String [] args)
    {  
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter the number of processes = ");
        int n = sc.nextInt();
        System.out.print("Enter the number of resources = ");
        int m = sc.nextInt();
        int [][] MAX = new int[n][m];
        int [][] Allocation = new int[n][m];
        int [][] Need = new int[n][m];
        int [][] Available = new int[1][m];
        int [] sequence = new int[n];

        for (int i = 0 ; i < n ; i++)
        {
            System.out.println("Enter the processes details of process" + (i + 1) + " => ");
            System.out.println("Enter the MAX limit resources => ");
            for (int j = 0 ; j < m ; j++)
            {
                MAX[i][j] = sc.nextInt();
            }
            System.out.println("Enter the Allocation resources => ");
            for (int j = 0 ; j < m ; j++)
            {
                Allocation[i][j] = sc.nextInt();
            }
            for (int j = 0 ; j < m ;j++)
            {
                Need[i][j] = MAX[i][j] - Allocation[i][j];
            }

        }

        System.out.println("Enter the Available Resources => ");
        for (int i = 0 ; i < m ; i++)
            {
                Available[0][i] = sc.nextInt();
            }

        System.out.println(" the Need resources => ");
            for (int i = 0 ; i < n ; i++)
            {
                for (int j = 0 ; j < m ;j++)
                {
                    System.out.print(Need[i][j] + " ");
                }
                System.out.println();

            }

        int avail_flag = 1;
        int done_flag[] = new int[n];
        int cnt1 = 0 , cnt2 = 0; 
         int k = 0;
        for( int i = 0 ; i < n ;i++)
        {
            avail_flag = 1;
           for ( int j = 0 ; j < m ; j++)
           {
                if (Need[i][j] > Available[0][j])
                {
                    avail_flag = 0;
                    break;
                }

           }

           if (avail_flag == 1)
           {
              for (int j = 0 ; j < m ;j++)
                {
                    Available[0][j] = Available[0][j] + Allocation[i][j];
                }
                done_flag[i] = 1;
                cnt1++;
                sequence[k++] = i + 1;
           }
           
        }
    
        for (int a = 0 ; a < n ;a++)
        {
            if (done_flag[a] != 1)
            {
                        for ( int j = 0 ; j < m ; j++)
                        {
                                if (Need[a][j] > Available[0][j])
                                {
                                    avail_flag = 0;
                                    break;
                                }

                        }

                        if (avail_flag == 1)
                        {
                            for (int j = 0 ; j < m ;j++)
                                {
                                    Available[0][j] = Available[0][j] + Allocation[a][j];
                                }
                                done_flag[a] = 1;
                                cnt2 = cnt1 + 1;
                                sequence[k++] = a + 1;
                        }
                        
            }
                    

        }
        for (int i = 0 ; i < n ; i++)
        {
            if (sequence[i] == 0)
            {
            System.out.println("Unsafe State ");
            return;
            }
        }
        System.out.println("Safe Sequence is = ");
        for ( int i = 0 ; i < n ; i++)
        {
            System.out.print(sequence[i]+" ");
        }
        


        

    }
}