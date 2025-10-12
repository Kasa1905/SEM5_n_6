import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;

class RW {
    private static final long READ_DELAY_MS = 400;
    private static final long WRITE_DELAY_MS = 600;

    // Semaphores (fair mode to reduce starvation)
    static final Semaphore mutex = new Semaphore(1, true);  // controlling readCount
    static final Semaphore wrt = new Semaphore(1, true);    // controlling writers
    static int readCount = 0;                               // number of readers

    // Reader Class
    static final class Reader implements Runnable {
        private final int readerId;

        Reader(int id) {
            this.readerId = id;
        }

        @Override
        public void run() {
            try {
                // Entry section
                mutex.acquire();
                try {
                    readCount++;
                    if (readCount == 1) {
                        wrt.acquire(); // first reader locks writer
                    }
                } finally {
                    mutex.release();
                }

                // Critical Section (Reading)
                System.out.println("Reader " + readerId + " is READING");
                TimeUnit.MILLISECONDS.sleep(READ_DELAY_MS); // simulate read time
                System.out.println("Reader " + readerId + " has FINISHED READING");

                // Exit section
                mutex.acquire();
                try {
                    readCount--;
                    if (readCount == 0) {
                        wrt.release(); // last reader unlocks writer
                    }
                } finally {
                    mutex.release();
                }

            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                System.err.println("Reader " + readerId + " was interrupted");
            }
        }
    }

    // Writer Class
    static final class Writer implements Runnable {
        private final int writerId;

        Writer(int id) {
            this.writerId = id;
        }

        @Override
        public void run() {
            try {
                // Entry section
                wrt.acquire();

                // Critical Section (Writing)
                System.out.println("Writer " + writerId + " is WRITING");
                TimeUnit.MILLISECONDS.sleep(WRITE_DELAY_MS); // simulate write time
                System.out.println("Writer " + writerId + " has FINISHED WRITING");

                // Exit section
                wrt.release();

            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                System.err.println("Writer " + writerId + " was interrupted");
            }
        }
    }
}

// Main Class
public class ReaderWriterProblem {
    public static void main(String[] args) {
        List<Runnable> tasks = List.of(
                new RW.Reader(1),
                new RW.Writer(1),
                new RW.Reader(2),
                new RW.Reader(3),
                new RW.Writer(2)
        );

        ExecutorService executor = Executors.newFixedThreadPool(tasks.size());
        try {
            tasks.forEach(executor::execute);
        } finally {
            executor.shutdown();
            try {
                if (!executor.awaitTermination(5, TimeUnit.SECONDS)) {
                    executor.shutdownNow();
                }
            } catch (InterruptedException e) {
                executor.shutdownNow();
                Thread.currentThread().interrupt();
            }
        }
    }
}