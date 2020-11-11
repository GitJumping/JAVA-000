import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class Five_JoinAsyncReturn {
    static volatile int success = 0;
    public static void main(String[] args) throws InterruptedException {
        final Map<String, String> result= new ConcurrentHashMap<>();
        System.out.println("open a thread");
        Thread thread = new Thread(new Runnable(){
            @Override
            public void run() {
                try {
                    Thread.sleep(1000);
                    result.put("finalRst", "结果");
                    success = 1;
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });
        thread.start();
        thread.join();
        System.out.println(result.get("finalRst"));
    }
}
