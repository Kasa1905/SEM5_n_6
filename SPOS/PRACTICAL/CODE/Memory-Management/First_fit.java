import java.util.*;

// First Fit memory allocation
class First_fit {
	public static void main(String[] args) {
		try (Scanner sc = new Scanner(System.in)) {
			System.out.print("Enter number of blocks: ");
			int b = sc.nextInt();
			int[] blocks = new int[b];
			int sumBlocks = 0;
			System.out.println("Enter sizes of " + b + " blocks:");
			for (int i = 0; i < b; i++) { blocks[i] = sc.nextInt(); sumBlocks += blocks[i]; }

			System.out.print("Enter number of jobs: ");
			int m = sc.nextInt();
			int[] jobs = new int[m];
			System.out.println("Enter sizes of " + m + " jobs:");
			for (int i = 0; i < m; i++) jobs[i] = sc.nextInt();

			int[] remaining = Arrays.copyOf(blocks, b);
			int[] assign = new int[m];
			Arrays.fill(assign, -1);
			int sumAssigned = 0;

			// First Fit: pick first block with enough remaining size
			for (int i = 0; i < m; i++) {
				for (int j = 0; j < b; j++) {
					if (remaining[j] >= jobs[i]) {
						assign[i] = j;
						remaining[j] -= jobs[i];
						sumAssigned += jobs[i];
						break;
					}
				}
			}

			System.out.println("\nAllocation result (First Fit):\nJob\tSize\t->\tBlock\tBlockSize\tRemainingAfter");
			for (int i = 0; i < m; i++) {
				if (assign[i] != -1) {
					System.out.println((i+1)+"\t"+jobs[i]+"\t->\t"+(assign[i]+1)+"\t"+blocks[assign[i]]+"\t"+remaining[assign[i]]);
				} else {
					System.out.println((i+1)+"\t"+jobs[i]+"\t->\tNot allocated");
				}
			}

			System.out.println("\nSum of blocks: " + sumBlocks);
			System.out.println("Sum of assigned jobs: " + sumAssigned);
			double eff = sumBlocks == 0 ? 0.0 : (double)sumAssigned / sumBlocks;
			System.out.printf(Locale.US, "Efficiency (sumAssigned/sumBlocks): %.4f (%.2f%%)\n", eff, eff*100.0);
		}
	}
}
