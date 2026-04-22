import java.util.*;

public class LRU {
	private static int[] readPages(Scanner sc, int n) {
		int[] pages = new int[n];
		int i = 0;
		System.out.println("Enter " + n + " page numbers (space/newline separated):");
		while (i < n) {
			if (sc.hasNextInt()) pages[i++] = sc.nextInt();
			else {
				String bad = sc.next();
				System.out.println("Ignoring non-integer token: '" + bad + "'. Please continue entering integers.");
			}
		}
		return pages;
	}

	public static void main(String[] args) {
		try (Scanner sc = new Scanner(System.in)) {
			System.out.println("Enter number of pages:");
			int n = sc.nextInt();
			int[] pages = readPages(sc, n);

			System.out.println("Enter number of frames:");
			int f = sc.nextInt();
			if (f <= 0) {
				System.out.println("Number of frames must be > 0");
				return;
			}

			int[] frames = new int[f];
			Arrays.fill(frames, -1);
			int[] lastUsed = new int[f]; 
			Arrays.fill(lastUsed, -1);

			int pageFaults = 0, hits = 0;
			for (int i = 0; i < n; i++) {
				int p = pages[i];

				int hitIndex = -1;
				for (int j = 0; j < f; j++) {
					if (frames[j] == p) {
						hitIndex = j;
						break;
					}
				}

				if (hitIndex != -1) {
					lastUsed[hitIndex] = i;
					hits++;
					System.out.print("Ref " + p + " -> HIT   | Frames: ");
				} else {
					pageFaults++;
					System.out.print("Ref " + p + " -> FAULT | Frames: ");
					int empty = -1;
					for (int j = 0; j < f; j++) {
						if (frames[j] == -1) { empty = j; break; }
					}
					if (empty != -1) {
						frames[empty] = p;
						lastUsed[empty] = i;
					} else {
						int lruIndex = 0;
						int minUsed = lastUsed[0];
						for (int j = 1; j < f; j++) {
							if (lastUsed[j] < minUsed) {
								minUsed = lastUsed[j];
								lruIndex = j;
							}
						}
						frames[lruIndex] = p;
						lastUsed[lruIndex] = i;
					}
				}

				for (int j = 0; j < f; j++) {
					if (frames[j] != -1) System.out.print(frames[j] + " ");
					else System.out.print("- ");
				}
				System.out.println();
			}

		System.out.println("Total References: " + n);
		System.out.println("Total Hits: " + hits);
		System.out.println("Total Page Faults: " + pageFaults);
		}
	}
}
