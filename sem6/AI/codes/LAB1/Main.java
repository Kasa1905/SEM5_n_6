import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class Main {
    private static void dfsRecursive(int node, ArrayList<ArrayList<Integer>> adj, boolean[] visited) {
        visited[node] = true;
        System.out.print(node + " ");
        for (int neighbor : adj.get(node)) {
            if (!visited[neighbor]) {
                dfsRecursive(neighbor, adj, visited);
            }
        }
    }

    private static void bfs(int start, ArrayList<ArrayList<Integer>> adj) {
        boolean[] visited = new boolean[adj.size()];
        Queue<Integer> q = new LinkedList<>();
        visited[start] = true;
        q.add(start);

        while (!q.isEmpty()) {
            int node = q.poll();
            System.out.print(node + " ");
            for (int neighbor : adj.get(node)) {
                if (!visited[neighbor]) {
                    visited[neighbor] = true;
                    q.add(neighbor);
                }
            }
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.print("Enter number of vertices: ");
        if (!sc.hasNextInt()) {
            System.out.println("Invalid number of vertices.");
            return;
        }
        int vertices = sc.nextInt();
        if (vertices <= 0) {
            System.out.println("Invalid number of vertices.");
            return;
        }

        System.out.print("Enter number of edges: ");
        if (!sc.hasNextInt()) {
            System.out.println("Invalid number of edges.");
            return;
        }
        int edges = sc.nextInt();
        if (edges < 0) {
            System.out.println("Invalid number of edges.");
            return;
        }

        ArrayList<ArrayList<Integer>> adj = new ArrayList<>();
        for (int i = 0; i < vertices; i++) {
            adj.add(new ArrayList<>());
        }

        System.out.println("Enter each edge as: u v (0-indexed)");
        for (int i = 0; i < edges; i++) {
            if (!sc.hasNextInt()) {
                System.out.println("Invalid edge input.");
                return;
            }
            int u = sc.nextInt();
            if (!sc.hasNextInt()) {
                System.out.println("Invalid edge input.");
                return;
            }
            int v = sc.nextInt();

            if (u < 0 || v < 0 || u >= vertices || v >= vertices) {
                System.out.println("Invalid edge input.");
                return;
            }

            adj.get(u).add(v);
            adj.get(v).add(u);
        }

        System.out.print("Enter start vertex: ");
        if (!sc.hasNextInt()) {
            System.out.println("Invalid start vertex.");
            return;
        }
        int start = sc.nextInt();
        if (start < 0 || start >= vertices) {
            System.out.println("Invalid start vertex.");
            return;
        }

        boolean[] visited = new boolean[vertices];
        System.out.print("DFS traversal: ");
        dfsRecursive(start, adj, visited);
        System.out.println();

        System.out.print("BFS traversal: ");
        bfs(start, adj);
        System.out.println();
    }
}