# SEM5_n_6

This repository is a structured collection of Semester 5 and Semester 6 academic material. It mixes practical programs, exam-oriented implementations, lab exercises, reference code, input/output samples, and study documents across multiple subjects.

The repository is organized so that each folder represents a topic or lab area. Most code is intentionally kept close to the problem statement style used in labs and exams, so the files can be run directly or adapted for submissions, viva preparation, and revision.

## What Is Inside

### Semester 5

- SPOS practical programs covering CPU scheduling, memory management, page replacement, assembler passes, macro processor passes, deadlock avoidance, and synchronization problems.
- SPOS exam question folders organized from `Q1` to `Q18`, with code, sample input, and output files where applicable.
- DBMS notes and SQL scripts covering basic SQL, joins, PL/SQL procedures, triggers, cursor examples, MongoDB CRUD, aggregation, map-reduce, and connectivity programs.

### Semester 6

- AI code examples and PDF reference material.
- Computer Communication reference PDFs.
- DSBDA material including data sets, code, and lab manual resources.

## Repository Layout

```text
sem5/
	EXAM/
		DBMS/
			SQL, PL/SQL, MongoDB, connectivity and query files
		SPOS/
			Q1 to Q18 exam-oriented folders
	SPOS/
		CPU-Scheduling/
		Deadlock-Bankers/
		Macro-Pass1/
		Macro-Pass2/
		Memory-Management/
		Page-Replacement/
		Pass1-Assembler/
		Pass2-Assembler/
		Reader-Writer-Problem/

sem6/
	AI/
		codes/
		pdfs/
	CC/
		lecture and lab PDFs
	DSBDA/
		Data set/
		code/
		lab manual/
```

## Subject Guide

### SPOS

The SPOS section focuses on operating system and system programming lab work. It includes:

- CPU scheduling algorithms such as FCFS, SJF, priority scheduling, and Round Robin.
- Memory allocation strategies such as First Fit, Best Fit, and Worst Fit.
- Page replacement algorithms such as FIFO, LRU, and Optimal.
- Assembler implementations for pass 1 and pass 2.
- Macro processor implementations for pass 1 and pass 2.
- Synchronization and concurrency examples such as the Reader-Writer problem.
- Deadlock avoidance using the Banker’s algorithm.

There are two SPOS locations in the repository:

- `sem5/SPOS` contains the main practical implementations.
- `sem5/EXAM/SPOS` contains exam-specific question folders and supporting files.

### DBMS

The DBMS section contains SQL scripts and database programming examples, including:

- Basic SQL queries and table creation.
- Joins and relational query examples.
- PL/SQL procedures, triggers, cursors, and procedural logic.
- MongoDB CRUD, aggregation, map-reduce, and connectivity examples.
- Question-wise files that mirror lab-style DBMS exercises.

### AI

The AI section contains code examples and PDF reference material for semester 6 AI topics. The code is primarily stored under `sem6/AI/codes`, and supporting documents are stored in `sem6/AI/pdfs`.

### CC

The CC folder contains computer communication reference PDFs and lab material.

### DSBDA

The DSBDA folder contains:

- Data set material used for exercises.
- Code examples.
- Lab manual resources.

## Common File Types

The repository uses a small set of file types repeatedly:

- `.java` for SPOS and AI-related Java programs.
- `.py` for small GUI or demonstration programs.
- `.c` for C-based practicals.
- `.txt` for input files, outputs, notes, and SQL scripts.
- `.pdf` for lab manuals and reference documents.

## How To Use The Repository

Most programs in this repo are designed to be run from the folder where the source file and its related input files already exist.

### Java Programs

Compile and run a Java program from its folder:

```bash
cd sem5/SPOS/CPU-Scheduling
javac FCFS.java
java FCFS
```

If the class is inside a package, run it from the repository root using the full package name after compiling.

### Python Programs

Run a Python file directly with Python 3:

```bash
cd sem5/EXAM/SPOS/Q10/HCI
python3 online_quiz.py
```

### C Programs

Compile the file with GCC and then run the generated executable:

```bash
cd sem5/EXAM/SPOS/Q5
gcc mathcalc.c -o mathcalc
./mathcalc
```

### SQL and MongoDB Files

The database files are mostly script-style text files. They can be copied into the relevant database tool or shell:

- Oracle SQL or PL/SQL scripts can be run in SQL*Plus, SQL Developer, or another Oracle-compatible environment.
- MongoDB files can be executed in `mongosh`.
- Connectivity examples are reference programs and may need the correct driver, database name, user credentials, and local server setup.

## Folder Pattern

Many folders follow one of these patterns:

1. Source file(s) plus input text file(s).
2. Source file(s) plus sample output file(s).
3. UI/demo programs in a small HCI folder.
4. Reference documents stored alongside the code.

This layout is intentional so the folder can be used as a self-contained lab or exam workspace.

## Notes For Readers

- Some folders contain alternate implementations of the same algorithm. This is useful for comparing approaches and for exam revision.
- Many text files are intentionally left in the repository because they act as problem inputs, expected outputs, or documentation.
- The repository is not a single application. It is a study archive and practical code collection.
- File and folder names are kept close to the original lab/question naming, even when they are not conventional software project names.

## Suggested Navigation

If you want to understand the repository quickly, start here:

1. `sem5/SPOS` for the main OS/system programming practicals.
2. `sem5/EXAM/SPOS` for question-wise SPOS material.
3. `sem5/EXAM/DBMS` for SQL, PL/SQL, and MongoDB files.
4. `sem6/AI` for AI code and reference PDFs.
5. `sem6/DSBDA` for data science and big data analysis material.

## License

This project is licensed under the MIT License. See `LICENSE` for details.