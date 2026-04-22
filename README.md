# SEM5_n_6

This repository contains practical programs, notes, and exam-oriented implementations for Semester 5 and Semester 6 coursework.

## Scope

- sem5
	- SPOS implementations (CPU scheduling, memory management, page replacement, assembler, macro processor, synchronization, deadlock handling)
	- Exam-wise SPOS questions (`Q1` to `Q18`)
	- DBMS material (SQL, PL/SQL, MongoDB, connectivity)
- sem6
	- AI resources
	- CC resources

## Repository Layout

```text
sem5/
	EXAM/
		DBMS/
		SPOS/
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
	CC/
```

## Common File Types

- Java source files (`.java`)
- Python scripts (`.py`)
- C source files (`.c`)
- Input/output and reference text files (`.txt`)

## How To Run

### Java programs

```bash
cd sem5/SPOS/CPU-Scheduling
javac FCFS.java
java FCFS
```

For question-wise exam folders:

```bash
cd sem5/EXAM/SPOS/Q1
javac Pass1.java
java Pass1
```

### Python scripts

```bash
cd sem5/EXAM/SPOS/Q10/HCI
python3 online_quiz.py
```

### C programs

```bash
cd sem5/EXAM/SPOS/Q5
gcc mathcalc.c -o mathcalc
./mathcalc
```

## Notes

- Some folders contain alternate implementations of similar algorithms for practice.
- Text files in DBMS and SPOS sections are intended as practical inputs, outputs, and study notes.

## License

This project is licensed under the MIT License. See `LICENSE` for details.