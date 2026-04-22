import java.util.*;

class Edge implements Comparable<Edge> {
    int src, dest, weight;
    
    public Edge(int src, int dest, int weight) {
        this.src = src;
        this.dest = dest;
        this.weight = weight;
    }
    
    public int compareTo(Edge other) {
        return this.weight - other.weight;
    }
}

class UnionFind {
    int[] parent, rank;
    
    UnionFind(int n) {
        parent = new int[n];
        rank = new int[n];
        for (int i = 0; i < n; i++) {
            parent[i] = i;
            rank[i] = 0;
        }
    }
    
    int find(int x) {
        if (parent[x] != x) {
            parent[x] = find(parent[x]);
        }
        return parent[x];
    }
    
    boolean union(int x, int y) {
        int rootX = find(x);
        int rootY = find(y);
        
        if (rootX == rootY) {
            return false;
        }
        
        if (rank[rootX] < rank[rootY]) {
            parent[rootX] = rootY;
        } else if (rank[rootX] > rank[rootY]) {
            parent[rootY] = rootX;
        } else {
            parent[rootY] = rootX;
            rank[rootX]++;
        }
        return true;
    }
}

public class Kruskal {
    
    static void kruskal(int n, List<Edge> edges) {
        Collections.sort(edges);
        UnionFind uf = new UnionFind(n);
        
        int totalWeight = 0;
        System.out.println("Edge\tWeight");
        
        for (Edge edge : edges) {
            if (uf.union(edge.src, edge.dest)) {
                System.out.println(edge.src + "-" + edge.dest + "\t" + edge.weight);
                totalWeight += edge.weight;
            }
        }
        
        System.out.println("\nTotal Weight: " + totalWeight);
    }
    
    public static void main(String[] args) {
        int n = 6;
        List<Edge> edges = new ArrayList<>();
        
        edges.add(new Edge(0, 1, 4));
        edges.add(new Edge(0, 2, 2));
        edges.add(new Edge(1, 2, 1));
        edges.add(new Edge(1, 3, 5));
        edges.add(new Edge(2, 3, 8));
        edges.add(new Edge(2, 4, 10));
        edges.add(new Edge(3, 4, 2));
        edges.add(new Edge(3, 5, 6));
        edges.add(new Edge(4, 5, 3));
        
        kruskal(n, edges);
    }
}
