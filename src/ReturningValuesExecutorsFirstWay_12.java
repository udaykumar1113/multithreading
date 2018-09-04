import java.util.concurrent.*;

public class ReturningValuesExecutorsFirstWay_12 {
    public static void main(String args[]){
        ExecutorService executorService= Executors.newCachedThreadPool(new MyThreadName());
        Future<Integer> result1=executorService.submit(new CalculationTaskA(2,3,3000));
        Future<Integer> result2=executorService.submit(new CalculationTaskA(3,4,2000));
        Future<Integer> result3=executorService.submit(new CalculationTaskA(5,6,1000));
        Future<Integer> result4=executorService.submit(new CalculationTaskA(6,7,500));

        Future<?> result5=executorService.submit(new LoopTaskA());

        try {
            System.out.println("Result 1 "+result1.get());
            System.out.println("Result 2 "+result2.get());
            System.out.println("Result 3 "+result3.get());
            System.out.println("Result 4 "+result4.get());
            System.out.println("Result 5 "+result5.get());
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
        executorService.shutdown();
        System.out.println("Main Thread complete");
    }
}

class CalculationTaskA implements Callable<Integer>{

    int a;
    int b;
    int c;
    int sleepTime;
    public CalculationTaskA(int a,int b, int sleepTime) {
        this.a=a;
        this.b=b;
        this.sleepTime=sleepTime;
    }

    @Override
    public Integer call() throws Exception {

        System.out.println("Started calculation task "+ Thread.currentThread().getName());
        Thread.sleep(sleepTime);
        System.out.println("Finished calculation task "+ Thread.currentThread().getName()+" sum is "+(a+b));
        return a+b;
    }
}

class LoopTaskA implements Runnable{

    @Override
    public void run() {
        try {
            Thread.sleep(1500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        for(int i=0;i<10;i++){
            System.out.println(Thread.currentThread().getName()+" "+i);
        }
    }
}

class MyThreadName implements ThreadFactory{
    public static String threadName="My Thread";
    public static int count=0;
    @Override
    public Thread newThread(Runnable r) {

        Thread t=new Thread(r, threadName+ ++count);
        return t;
    }
}