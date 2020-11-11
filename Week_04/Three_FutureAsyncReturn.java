import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.*;

public class Three_FutureAsyncReturn {
    private static ExecutorService executorService = Executors.newSingleThreadExecutor();
    public static void main(String[] args) throws InterruptedException, BrokenBarrierException, ExecutionException {
        Future<String> result = executorService.submit(new Callable<String>() {
            @Override
            public String call() throws Exception {
                Thread.sleep(1000);
                return "结果";
            }
        });
        System.out.print(result.get());
        executorService.shutdown();
    }
}
