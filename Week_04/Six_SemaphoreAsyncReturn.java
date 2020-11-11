import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.*;

public class Six_SemaphoreAsyncReturn {
    public static void main(String[] args) throws InterruptedException, BrokenBarrierException, ExecutionException {
        final Map<String, String> result= new ConcurrentHashMap<>();
        Semaphore semaphore = new Semaphore(1);
        semaphore.acquire();
        Thread thread = new Thread(new Task(semaphore, result));
        thread.start();
        semaphore.acquire();
        System.out.print(result.get("finalRst"));
    }

    public static class Task implements  Runnable{
        private Semaphore semaphore;
        private Map<String, String> result;
        public Task(Semaphore semaphore, Map<String, String> result) {
            this.semaphore = semaphore;
            this.result = result;
        }
        @Override
        public void run() {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            result.put("finalRst","结果");
            semaphore.release();
        }
    }
}
