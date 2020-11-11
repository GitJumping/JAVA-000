import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

public class Seven_VolatileVariableAsyncReturn {
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
        int count = 0;
        while(success == 0) {
            if (count == 20) {
                break;
            }
            if(success == 1){
                break;
            }
            Thread.sleep(100);
            count ++;
            System.out.println("结果2");
        }
        System.out.print(result.get("finalRst"));
    }
}
