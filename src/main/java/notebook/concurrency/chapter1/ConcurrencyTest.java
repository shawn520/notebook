package notebook.concurrency.chapter1;

/**
 * @author Shawn
 * @date 2019/10/2
 */
public class ConcurrencyTest {

    public static void main(String[] args) throws InterruptedException {
        long [] counts = {10000, 100000, 1000000, 100000000};
        for(int i=0; i<counts.length; i++) {
            long concurrent = concurrency(counts[i]);
            long serial = serial(counts[i]);
            System.out.printf("count = %d, serial = %dms, concurrency = %dms \n",
                    counts[i], serial, concurrent);

        }

    }

    private static long concurrency(final long count) throws InterruptedException {
        long start = System.currentTimeMillis();
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                int a = 0;
                for(long i=0; i<count; i++) {
                    a += 5;
                }
            }
        });
        thread.start();

        int b= 0;
        for(long i=0; i<count; i++) {
            b--;
        }
        thread.join();
        long time = System.currentTimeMillis() - start;
        return time;
    }

    private static long serial(final long count) {
        long start = System.currentTimeMillis();
        int a = 0;
        for(long i=0; i<count; i++) {
            a += 5;
        }
        int b=0;
        for(int i=0; i<count; i++) {
            b--;
        }
        long time = System.currentTimeMillis() - start;
        return time;
    }
}
