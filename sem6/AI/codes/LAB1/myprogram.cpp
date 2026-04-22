#include <iostream>
#include <vector>
#include <queue>

void dfs_recursive(int node, const std::vector<std::vector<int>> &adj, std::vector<bool> &visited) {
    visited[node] = true;
    std::cout << node << ' ';
    for (int neighbor : adj[node]) {
        if (!visited[neighbor]) {
            dfs_recursive(neighbor, adj, visited);
        }
    }
}

void bfs(int start, const std::vector<std::vector<int>> &adj) {
    std::vector<bool> visited(adj.size(), false);
    std::queue<int> q;
    visited[start] = true;
    q.push(start);

    while (!q.empty()) {
        int node = q.front();
        q.pop();
        std::cout << node << ' ';
        for (int neighbor : adj[node]) {
            if (!visited[neighbor]) {
                visited[neighbor] = true;
                q.push(neighbor);
            }
        }
    }
}

int main() {
    int vertices = 0;
    int edges = 0;

    std::cout << "Enter number of vertices: ";
    if (!(std::cin >> vertices) || vertices <= 0) {
        std::cout << "Invalid number of vertices.\n";
        return 1;
    }

    std::cout << "Enter number of edges: ";
    if (!(std::cin >> edges) || edges < 0) {
        std::cout << "Invalid number of edges.\n";
        return 1;
    }

    std::vector<std::vector<int>> adj(vertices);
    std::cout << "Enter each edge as: u v (0-indexed)\n";
    for (int i = 0; i < edges; ++i) {
        int u = 0;
        int v = 0;
        std::cin >> u >> v;
        if (u < 0 || v < 0 || u >= vertices || v >= vertices) {
            std::cout << "Invalid edge input.\n";
            return 1;
        }
        adj[u].push_back(v);
        adj[v].push_back(u);
    }

    int start = 0;
    std::cout << "Enter start vertex: ";
    if (!(std::cin >> start) || start < 0 || start >= vertices) {
        std::cout << "Invalid start vertex.\n";
        return 1;
    }

    std::vector<bool> visited(vertices, false);
    std::cout << "DFS traversal: ";
    dfs_recursive(start, adj, visited);
    std::cout << "\n";

    std::cout << "BFS traversal: ";
    bfs(start, adj);
    std::cout << "\n";

    return 0;
}
