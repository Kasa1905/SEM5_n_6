# 🎓 Semester 5 - Computer Science Lab Implementations

Computer Science laboratory programs for **Semester 5** - SPOS and DBMS implementations.

## 📚 Subjects Covered

### 🖥️ **SPOS (System Programming and Operating System)**
- **CPU Scheduling** - FCFS, SJF, Priority, Round Robin
- **Memory Management** - First Fit, Best Fit, Worst Fit
- **Page Replacement** - FIFO, LRU, Optimal
- **Assembler** - Two-Pass Assembler Implementation
- **Macro Processor** - Two-Pass Macro Processing
- **Deadlock Prevention** - Banker's Algorithm
- **Synchronization** - Reader-Writer Problem
- **JNI Integration** - Dynamic Link Library

### 🗄️ **DBMS (Database Management System)**
- **SQL Fundamentals** - DDL, DML, Joins
- **PL/SQL Programming** - Procedures, Functions, Triggers
- **Database Connectivity** - Application Integration
- **MongoDB Operations** - CRUD, Aggregation, Map-Reduce
- **Advanced Queries** - Complex SQL Operations

## 🗂️ Repository Structure

```
SEM5/
├── SPOS/
│   └── PRACTICAL/CODE/     # Reference implementations
├── EXAM/                   # Organized by subject
│   ├── SPOS/              # SPOS exam questions
│   │   ├── Q1-Q18/        # Individual question folders
│   │   └── LP-I_SPOS_Writeup.pdf
│   └── DBMS/              # DBMS lab implementations
│       ├── SQL files      # Database scripts
│       ├── MongoDB files  # NoSQL operations
│       └── DBMSL lab Manual.pdf
├── LICENSE
└── README.md
```

## 🚀 Quick Start (Ubuntu)

### Prerequisites
```bash
sudo apt update
sudo apt install openjdk-11-jdk gcc git mysql-server mongodb
```

### Clone and Run

#### SPOS Programs
```bash
git clone https://github.com/Kasa1905/SEM5.git
cd SEM5/EXAM/SPOS/Q1
javac Pass1.java
java pass1
```

#### DBMS Scripts
```bash
cd SEM5/EXAM/DBMS
# Run SQL scripts with MySQL
mysql -u root -p < "SQL DDL & DML.txt"
# Run MongoDB operations
mongosh < "MongoDB CRUD.txt"
```

### Run Any Question
```bash
cd EXAM/SPOS/Q[number]     # Replace [number] with question number
javac *.java
java [MainClass]           # Replace with main class name
```

## 📋 Program Categories

### SPOS Implementation
- **Q1-Q4**: Assembler and Macro Processor
- **Q5**: Dynamic Link Library (JNI)
- **Q6-Q14**: Memory Management and CPU Scheduling
- **Q15-Q18**: Page Replacement and Deadlock Prevention

### DBMS Implementation
- **SQL Operations**: Basic to advanced database queries
- **PL/SQL**: Stored procedures, functions, and triggers
- **MongoDB**: NoSQL database operations and aggregation
- **Connectivity**: Database application integration

## 📝 License

MIT License - Free to use for educational purposes.