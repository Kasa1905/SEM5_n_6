# Deadlock Detection & Prevention - Banker's Algorithm

This folder contains the implementation of the Banker's Algorithm for deadlock detection and prevention.

## 📋 Program Overview

### **bankers.java** - Banker's Algorithm Implementation
- **Purpose**: Deadlock detection and prevention using resource allocation graph
- **Algorithm**: Banker's Algorithm (Safety Algorithm)
- **Type**: Deadlock Prevention (Conservative approach)
- **Input**: Allocation matrix, Max matrix, Available resources
- **Output**: Safe sequence or deadlock detection

## 🎯 Algorithm Concept

### Banker's Algorithm
The Banker's Algorithm is a resource allocation and deadlock avoidance algorithm developed by Edsger Dijkstra. It tests for safety by simulating the allocation of predetermined maximum possible amounts of all resources, and then makes an "s-state" check to test for possible deadlock conditions.

### Key Concepts:
1. **Safe State**: A state where all processes can complete execution
2. **Unsafe State**: A state that may lead to deadlock
3. **Need Matrix**: Max - Allocation matrix
4. **Safety Sequence**: Order in which processes can complete

## 🔢 Data Structures

### Input Matrices:
- **Allocation[n][m]**: Currently allocated resources to each process
- **Max[n][m]**: Maximum resources each process may need
- **Available[m]**: Available instances of each resource type
- **Need[n][m]**: Remaining resource needs (Max - Allocation)

Where:
- n = number of processes
- m = number of resource types

## 🚀 How to Run

### Prerequisites
- Java JDK 8 or higher
- Terminal/Command Prompt

### Compilation & Execution
```bash
# Navigate to the directory
cd "Deadlock (Bankers)"

# Compile the program
javac bankers.java

# Run the program
java bankers
```

## 📊 Sample Input/Output

### Example 1: Safe State
```
Input:
Enter number of processes: 5
Enter number of resources: 3

Allocation Matrix:
P0: 0 1 0
P1: 2 0 0
P2: 3 0 2
P3: 2 1 1
P4: 0 0 2

Max Matrix:
P0: 7 5 3
P1: 3 2 2
P2: 9 0 2
P3: 2 2 2
P4: 4 3 3

Available Resources: 3 3 2

Output:
Need Matrix:
P0: 7 4 3
P1: 1 2 2
P2: 6 0 0
P3: 0 1 1
P4: 4 3 1

System is in Safe State
Safe Sequence: P1 → P3 → P4 → P0 → P2
```

### Example 2: Deadlock Detection
```
Input:
Available Resources: 0 0 0
All processes have remaining needs...

Output:
System is in Unsafe State
Deadlock Detected!
Processes in deadlock: P0, P2, P4
```

## 🔄 Algorithm Steps

### Safety Algorithm:
1. **Initialize**: Work = Available, Finish[i] = false for all i
2. **Find Process**: Find i such that Finish[i] = false and Need[i] ≤ Work
3. **Update**: Work = Work + Allocation[i], Finish[i] = true
4. **Repeat**: Go to step 2
5. **Check**: If Finish[i] = true for all i, then safe sequence exists

### Resource Request Algorithm:
1. **Check**: Request[i] ≤ Need[i]
2. **Check**: Request[i] ≤ Available
3. **Simulate**: Temporarily allocate resources
4. **Safety Check**: Run safety algorithm
5. **Decision**: Grant if safe, otherwise wait

## 📈 Complexity Analysis

- **Time Complexity**: O(n² × m)
  - n = number of processes
  - m = number of resource types
- **Space Complexity**: O(n × m)
- **Best Case**: O(n × m) when processes are already in order
- **Worst Case**: O(n² × m) when multiple iterations needed

## 🛡️ Advantages & Disadvantages

### Advantages:
- **Deadlock Prevention**: Guarantees no deadlock
- **Resource Utilization**: Efficient resource allocation
- **Predictable**: Deterministic algorithm
- **Safety**: Always maintains system in safe state

### Disadvantages:
- **Conservative**: May deny safe requests
- **Overhead**: Computational complexity
- **Prior Knowledge**: Requires maximum resource needs
- **Static**: Doesn't handle dynamic process creation well

## 🔧 Common Issues & Solutions

### Input Validation:
```java
// Check if request exceeds need
if (request[i] > need[processId][i]) {
    System.out.println("Error: Request exceeds declared need");
    return false;
}

// Check if request exceeds available
if (request[i] > available[i]) {
    System.out.println("Error: Request exceeds available resources");
    return false;
}
```

### Debugging Tips:
1. **Matrix Verification**: Ensure Need = Max - Allocation
2. **Resource Conservation**: Total allocation ≤ Total resources
3. **Boundary Checks**: Validate array indices
4. **Input Format**: Use space-separated integers

## 🎓 Learning Objectives

- Understand deadlock conditions and prevention
- Learn resource allocation strategies
- Practice matrix operations in Java
- Analyze algorithm complexity
- Implement safety checking mechanisms

## 📚 Related Concepts

- **Deadlock Detection**: Using resource allocation graphs
- **Deadlock Prevention**: Ostrich algorithm, resource ordering
- **Deadlock Recovery**: Process termination, resource preemption
- **Concurrency Control**: Semaphores, monitors, locks

## 🧪 Test Cases

### Test Case 1: Normal Operation
```
Processes: 3, Resources: 2
Allocation: [[0,1],[2,0],[3,0]]
Max: [[7,5],[3,2],[9,0]]
Available: [3,3]
Expected: Safe sequence found
```

### Test Case 2: Deadlock Scenario
```
Processes: 3, Resources: 2
Allocation: [[2,2],[1,0],[1,1]]
Max: [[3,3],[2,1],[2,2]]
Available: [0,0]
Expected: Deadlock detected
```

## 🔗 Additional Resources

- Operating System Concepts (Silberschatz)
- "The Banker's Algorithm" - Dijkstra (1965)
- Deadlock handling in modern operating systems