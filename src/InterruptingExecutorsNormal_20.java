import java.util.concurrent.*;

public class InterruptingExecutorsNormal_20 {
    public static void main(String args[]) throws InterruptedException {
        ExecutorService executorService= Executors.newCachedThreadPool();
        CalculationTaskF task1=new CalculationTaskF(5,10,50);
        CalculationTaskF task2=new CalculationTaskF(10,15,50);
        CalculationTaskF task3=new CalculationTaskF(15,20,50);
        LoopTaskf task4=new LoopTaskf();
        LoopTaskf task5=new LoopTaskf();

        Future<Integer> calTask1=executorService.submit(task1);
        Future<Integer> calTask2=executorService.submit(task2);
        Future<Integer> calTask3=executorService.submit(task3);
        Future<?> calTask4=executorService.submit(task4);
        Future<?> calTask5=executorService.submit(task5);
        executorService.shutdown();

        Thread.sleep(5000);

        System.out.println("Interrupting Task 1");
        calTask1.cancel(true);
        System.out.println("Interrupting Task 2");
        calTask2.cancel(true);

        System.out.println("Interrupting Task 3");
        calTask3.cancel(true);
        System.out.println("Interrupting Task 4");
        calTask4.cancel(false);

        System.out.println("Interrupting Task 5");
        calTask5.cancel(true);
    }
}

class CalculationTaskF implements Callable<Integer>{

    private int a,b,sleepDuration;

    public CalculationTaskF(int a, int b, int sleepDuration) {
        this.a = a;
        this.b = b;
        this.sleepDuration = sleepDuration;
    }

    @Override
    public Integer call() throws Exception {
        System.out.println(Thread.currentThread().getName());
        TimeUnit.MILLISECONDS.sleep(sleepDuration);
        System.out.println(Thread.currentThread().getName()+" executing summation "+ a+b );
        return a+b;
    }
}

class LoopTaskf implements Runnable{

    @Override
    public void run() {
        for(int i=1;;i++){
            System.out.println(Thread.currentThread().getName()+" printing "+i);
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            if(Thread.interrupted()){
                break;
            }

        }
    }
}