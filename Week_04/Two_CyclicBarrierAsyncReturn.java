public class Two_CyclicBarrierAsyncReturn {
    public static void main(String[] args) throws InterruptedException, BrokenBarrierException {
        final CyclicBarrier cyclicBarrier = new CyclicBarrier(2);
        final Map<String, String> result= new ConcurrentHashMap<>();
        System.out.println("open a thread");
        Thread thread = new Thread(new Runnable(){
            @Override
            public void run() {
                try {
                    Thread.sleep(1000);
                    result.put("finalRst", "结果");
                    try {
                        cyclicBarrier.await();
                    } catch (BrokenBarrierException e) {
                        e.printStackTrace();
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });
        thread.start();
        cyclicBarrier.await();
        System.out.println(result.get("finalRst"));
    }
}
