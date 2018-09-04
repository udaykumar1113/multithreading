import java.util.concurrent.*;

public class CheckTaskAliveExecutors {
    public static void main(String args[]){
        ExecutorService executorService= Executors.newCachedThreadPool();
        //Future<?> task1=executorService.submit(new LoopTaskC());
        //Future<Integer> task2=executorService.submit(new LiveCalculationService(5,10,500));

        FutureTask<Integer> ft4 = new FutureTask<Integer>(new LoopTaskC(), 999);
        FutureTask<Integer> ft5 = new FutureTask<Integer>(new LiveCalculationService(4, 5, 5000));

        executorService.execute(ft4);
        executorService.execute(ft5);
        executorService.shutdown();

        for(int i=0;i<11;i++) {
            //System.out.println("task 1 status" +task1.isDone());
            //System.out.println("task 2 status" +task2.isDone());
            System.out.println("Future Task 4 status: " + ft4.isDone());
            System.out.println("Future Task 5 status: " + ft5.isDone());

            // System.out.println("Task 1 result: "+task1.get());
            // System.out.println("Task 2 result: "+task2.get());

        }
        try {
            System.out.println("Future Task 4 result: " + ft4.get());
            System.out.println("Future Task 5 result: " + ft5.get());
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
    }
}

class LiveCalculationService implements Callable<Integer> {

    int a;
    int b;
    int sleepTime;
    public LiveCalculationService(int a, int b, int sleepTime) {
        this.a = a;
        this.b = b;
        this.sleepTime = sleepTime;
    }

    @Override
    public Integer call() throws Exception {
        Thread.sleep(sleepTime);
        return a+b;
    }
}
