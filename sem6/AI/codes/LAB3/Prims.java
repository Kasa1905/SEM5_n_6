
public class Prims {
    static int minKey(int[] key, boolean[] inMST, int n) {
        int min = Integer.MAX_VALUE;
        int minIndex = -1;
        
        for (int i = 0; i < n; i++) {
            if (!inMST[i] && key[i] < min) {
                min = key[i];
                minIndex = i;
            }
        }
        return minIndex;
    }
    
    static void prims(int[][] graph, int n) {
        int[] parent = new int[n];
        int[] key = new int[n];
        boolean[] inMST = new boolean[n];
        
        for (int i = 0; i < n; i++) {
            key[i] = Integer.MAX_VALUE;
            inMST[i] = false;
        }
        
        key[0] = 0;
        parent[0] = -1;
        
        for (int i = 0; i < n - 1; i++) {
            int u = minKey(key, inMST, n);
            
            if (u == -1) break;
            
            inMST[u] = true;
            
            for (int v = 0; v < n; v++) {
                if (graph[u][v] != 0 && !inMST[v] && 
                    graph[u][v] < key[v]) {
                    key[v] = graph[u][v];
                    parent[v] = u;
                }
            }
        }
        
        printMST(parent, graph, n);
    }
    
    static void printMST(int[] parent, int[][] graph, int n) {
        System.out.println("Edge\tWeight");
        int totalWeight = 0;
        for (int i = 1; i < n; i++) {
            System.out.println(parent[i] + "-" + i + "\t" + graph[i][parent[i]]);
            totalWeight += graph[i][parent[i]];
        }
        System.out.println("\nTotal Weight: " + totalWeight);
    }
    
    public static void main(String[] args) {
        int[][] graph = {
            {0, 2, 0, 6, 0},
            {2, 0, 3, 8, 5},
            {0, 3, 0, 0, 7},
            {6, 8, 0, 0, 9},
            {0, 5, 7, 9, 0}
        };
        
        prims(graph, 5);
    }
}
