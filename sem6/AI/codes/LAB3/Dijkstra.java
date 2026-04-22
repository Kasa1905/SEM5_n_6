
public class Dijkstra {
    static final int INF = Integer.MAX_VALUE;
    
    static int minDistance(int[] dist, boolean[] visited, int n) {
        int min = INF;
        int minIndex = -1;
        
        for (int i = 0; i < n; i++) {
            if (!visited[i] && dist[i] <= min) {
                min = dist[i];
                minIndex = i;
            }
        }
        return minIndex;
    }
    
    static void dijkstra(int[][] graph, int src, int n) {
        int[] dist = new int[n];
        boolean[] visited = new boolean[n];
        
        for (int i = 0; i < n; i++) {
            dist[i] = INF;
            visited[i] = false;
        }
        
        dist[src] = 0;
        
        for (int i = 0; i < n - 1; i++) {
            int u = minDistance(dist, visited, n);
            
            if (u == -1) break;
            
            visited[u] = true;
            
            for (int v = 0; v < n; v++) {
                if (!visited[v] && graph[u][v] != 0 && 
                    dist[u] != INF && dist[u] + graph[u][v] < dist[v]) {
                    dist[v] = dist[u] + graph[u][v];
                }
            }
        }
        
        printSolution(dist, n);
    }
    
    static void printSolution(int[] dist, int n) {
        System.out.println("Vertex\tDistance");
        for (int i = 0; i < n; i++) {
            System.out.println(i + "\t" + dist[i]);
        }
    }
    
    public static void main(String[] args) {
        int[][] graph = {
            {0, 4, 0, 0, 0, 0, 0, 8, 0},
            {4, 0, 8, 0, 0, 0, 0, 11, 0},
            {0, 8, 0, 7, 0, 4, 0, 0, 2},
            {0, 0, 7, 0, 9, 14, 0, 0, 0},
            {0, 0, 0, 9, 0, 10, 0, 0, 0},
            {0, 0, 4, 14, 10, 0, 2, 0, 0},
            {0, 0, 0, 0, 0, 2, 0, 1, 6},
            {8, 11, 0, 0, 0, 0, 1, 0, 7},
            {0, 0, 2, 0, 0, 0, 6, 7, 0}
        };
        
        dijkstra(graph, 0, 9);
    }
}
