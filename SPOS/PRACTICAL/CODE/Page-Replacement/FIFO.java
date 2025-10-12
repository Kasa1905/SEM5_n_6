import java.util.*;

public class FIFO {
       private static int[] readPages(Scanner sc, int n) {
              int[] pages = new int[n];
              int i = 0;
              System.out.println("Enter " + n + " page numbers (space/newline separated):");
              while (i < n) {
                     if (sc.hasNextInt()) {
                            pages[i++] = sc.nextInt();
                     } else {
                            // consume invalid token and prompt again
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
                     int pageFaults = 0, pointer = 0, hits = 0;
                     for (int i = 0; i < n; i++) {
                            boolean found = false;
                            for (int j = 0; j < f; j++) {
                                   if (frames[j] == pages[i]) { found = true; break; }
                            }
                            if (!found) {
                                   frames[pointer] = pages[i];
                                   pointer = (pointer + 1) % f;
                                   pageFaults++;
                                   System.out.print("Ref " + pages[i] + " -> FAULT | Frames: ");
                            } else {
                                   hits++;
                                   System.out.print("Ref " + pages[i] + " -> HIT   | Frames: ");
                            }
                            for (int j = 0; j < f; j++) {
                                   System.out.print(frames[j] != -1 ? (frames[j] + " ") : "- ");
                            }
                            System.out.println();
                     }
                     System.out.println("Total References: " + n);
                     System.out.println("Total Hits: " + hits);
                     System.out.println("Total Page Faults: " + pageFaults);
              }
       }
}
