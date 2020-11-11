import java.util.concurrent.*;

public class Four_FutureTaskAsyncReturn {
    private static ExecutorService executorService = Executors.newSingleThreadExecutor();
    public static void main(String[] args) throws InterruptedException, BrokenBarrierException, ExecutionException {
        FutureTask<String> task = new FutureTask<String>(new Callable<String>() {
            @Override
            public String call() throws Exception {
                Thread.sleep(1000);
                return "结果";
            }
        });
        executorService.submit(task);
        System.out.print(task.get());
        executorService.shutdown();
    }
}
