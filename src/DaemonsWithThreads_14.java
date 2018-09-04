import java.util.concurrent.TimeUnit;

public class DaemonsWithThreads_14 {
    public static void main(String args[]){
        Thread thread1=new Thread(new LoopTaskD(1000),"Thread-1");
        Thread thread2=new Thread(new LoopTaskD(500),"Thread-2");

        thread1.setDaemon(true);

        thread1.start();
        thread2.start();

        System.out.println("Main thread complete");
    }
}

class LoopTaskD implements Runnable{
    private static int count = 0;
    private int instanceNumber;
    private String taskId;

    private long sleepTime;

    public LoopTaskD(long sleepTime) {
        this.sleepTime = sleepTime;
        this.instanceNumber = ++count;
        this.taskId = "LoopTaskD" + instanceNumber;
    }

    @Override
    public void run() {
        boolean isRunningType=Thread.currentThread().isDaemon();

        String threadType= isRunningType? "DAEMON" : "USER";

        String currentThreadName=Thread.currentThread().getName();

        System.out.println("##### [" + currentThreadName + ", " + threadType + "] <" + taskId + "> STARTING #####");

        for (int i=10; i>0; i--) {
            System.out.println("[" + currentThreadName + ", " + threadType + "] <" + taskId + ">TICK TICK " + i);

            try {
                TimeUnit.MILLISECONDS.sleep(sleepTime);
            } catch (InterruptedException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }

        System.out.println("***** [" + currentThreadName + ", " + threadType + "] <" + taskId + "> DONE ******");
    }
}