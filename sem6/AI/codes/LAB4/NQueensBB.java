
public class NQueensBB {     
        static final int N = 8;
     
      
     
        // Arrays for Branch & Bound
     
        static boolean[] col = new boolean[N];
     
        static boolean[] leftDiag = new boolean[2 * N - 1];
     
        static boolean[] rightDiag = new boolean[2 * N - 1];
     
      
     
        static int[][] board = new int[N][N];
     
      
     
        // Function to print solution
     
        static void printBoard() {
     
            for (int i = 0; i < N; i++) {
     
                for (int j = 0; j < N; j++) {
     
                    System.out.print(board[i][j] + " ");
     
                }
     
                System.out.println();
     
            }
     
        }
     
      
     
        // Solve using Branch & Bound + Backtracking
     
        static boolean solve(int row) {
     
            if (row == N) {
     
                printBoard();
     
                return true; // stop after first solution (minimum backtracking)
     
            }
     
      
     
            for (int c = 0; c < N; c++) {
     
      
     
                // Check if safe using arrays (O(1))
     
                if (!col[c] && !leftDiag[row - c + N - 1] && !rightDiag[row + c]) {
     
      
     
                    // Place queen
     
                    board[row][c] = 1;
     
                    col[c] = true;
     
                    leftDiag[row - c + N - 1] = true;
     
                    rightDiag[row + c] = true;
     
      
     
                    // Recur
     
                    if (solve(row + 1))
     
                        return true;
     
      
     
                    // Backtrack
     
                    board[row][c] = 0;
     
                    col[c] = false;
     
                    leftDiag[row - c + N - 1] = false;
     
                    rightDiag[row + c] = false;
     
                }
     
            }
     
            return false;
     
        }
     
      
     
        public static void main(String[] args) {
     
            if (!solve(0)) {
     
                System.out.println("No solution exists");
     
            }
     
        }
     
    }
