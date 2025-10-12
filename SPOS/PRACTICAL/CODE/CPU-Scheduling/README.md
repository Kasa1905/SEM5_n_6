# CPU Scheduling Algorithms

This folder contains implementations of various CPU scheduling algorithms.

## 📋 Programs Included

### 1. **FCFS.java** - First Come First Served
- **Description**: Non-preemptive scheduling based on arrival time
- **Algorithm**: Processes are executed in the order they arrive
- **Advantages**: Simple to implement, no starvation
- **Disadvantages**: High average waiting time, convoy effect

### 2. **SJF.java** - Shortest Job First (Preemptive)
- **Description**: Preemptive scheduling based on remaining burst time
- **Algorithm**: Always execute the process with shortest remaining time
- **Advantages**: Minimum average waiting time
- **Disadvantages**: Starvation for long processes, requires burst time prediction

### 3. **SJFNP.java** - Shortest Job First (Non-Preemptive)
- **Description**: Non-preemptive scheduling based on burst time
- **Algorithm**: Select shortest process when CPU becomes free
- **Advantages**: Better average waiting time than FCFS
- **Disadvantages**: Starvation possible for long processes

### 4. **PriorityP.java** - Priority Scheduling (Preemptive)
- **Description**: Preemptive scheduling based on process priority
- **Algorithm**: Higher priority process preempts lower priority
- **Advantages**: Important processes get preference
- **Disadvantages**: Starvation for low priority processes

### 5. **PriorityNP.java** - Priority Scheduling (Non-Preemptive)
- **Description**: Non-preemptive scheduling based on process priority
- **Algorithm**: Select highest priority process when CPU is free
- **Advantages**: Important processes get preference, no preemption overhead
- **Disadvantages**: Starvation possible

### 6. **RoundRobin.java** - Round Robin Scheduling
- **Description**: Preemptive scheduling with fixed time quantum
- **Algorithm**: Each process gets equal time slice in circular order
- **Advantages**: Fair allocation, good response time
- **Disadvantages**: Performance depends on time quantum size

## 🚀 How to Run

### Prerequisites
- Java JDK 8 or higher
- Terminal/Command Prompt

### Compilation
```bash
# Compile individual program
javac FCFS.java

# Or compile all programs
javac *.java
```

### Execution
```bash
# Run any scheduling algorithm
java FCFS
java SJF
java RoundRobin
# etc.
```

## 📊 Sample Input/Output

### FCFS Example:
```
Input:
Enter number of processes: 3
P1 Arrival time: 0
P1 Burst time: 5
P2 Arrival time: 1
P2 Burst time: 3
P3 Arrival time: 2
P3 Burst time: 8

Output:
P   AT  BT  CT  TAT WT
P1  0   5   5   5   0
P2  1   3   8   7   4
P3  2   8   16  14  6
Average waiting time: 3.33
Average turnaround time: 8.67
```

### Round Robin Example:
```
Input:
Enter number of processes: 3
Enter time quantum: 2
P1 Arrival time: 0, Burst time: 5
P2 Arrival time: 1, Burst time: 3
P3 Arrival time: 2, Burst time: 8

Output:
Gantt Chart: P1(0-2) P2(2-4) P3(4-6) P1(6-8) P2(8-9) P3(9-15) P1(15-16)
```

## 📈 Performance Metrics

Each algorithm calculates:
- **Completion Time (CT)**: When process finishes execution
- **Turnaround Time (TAT)**: CT - Arrival Time
- **Waiting Time (WT)**: TAT - Burst Time
- **Response Time**: First CPU allocation - Arrival Time

## 🎯 Algorithm Comparison

| Algorithm | Time Complexity | Space Complexity | Preemptive | Starvation |
|-----------|----------------|------------------|------------|------------|
| FCFS      | O(n)           | O(1)             | No         | No         |
| SJF       | O(n²)          | O(1)             | Yes/No     | Yes        |
| Priority  | O(n²)          | O(1)             | Yes/No     | Yes        |
| Round Robin| O(n)          | O(1)             | Yes        | No         |

## 🔧 Troubleshooting

### Common Issues:
1. **Input Format**: Enter integers only for times and priorities
2. **Negative Values**: Use only positive values for arrival and burst times
3. **Time Quantum**: For Round Robin, use appropriate quantum (typically 1-4)

### Best Practices:
- Use realistic burst time values
- Consider arrival time sequence
- Choose appropriate time quantum for Round Robin
- Test with various input sizes

## 📚 Learning Objectives

- Understand different CPU scheduling strategies
- Analyze performance trade-offs
- Compare preemptive vs non-preemptive scheduling
- Learn about convoy effect and starvation
- Practice algorithm implementation in Java