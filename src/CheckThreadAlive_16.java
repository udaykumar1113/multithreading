import java.util.concurrent.TimeUnit;

public class CheckThreadAlive_16 {
    public static void main(String args[]){

        String currentThreadName = Thread.currentThread().getName();
        System.out.println("[" + currentThreadName + "] Main thread starts here...");
        Thread thread1=new Thread(new LoopTaskC());
        Thread thread2=new Thread(new LoopTaskC());

        boolean t1IsAlive = thread1.isAlive();
        boolean t2IsAlive = thread2.isAlive();

        thread1.start();
        thread2.start();

        while(true) {
            try {
                TimeUnit.MILLISECONDS.sleep(600);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            t1IsAlive = thread1.isAlive();
            t2IsAlive = thread2.isAlive();

            System.out.println("[" + currentThreadName + "] Is '" + thread1.getName() + "' alive = "
                    + t1IsAlive);
            System.out.println("[" + currentThreadName + "] Is '" + thread2.getName() + "' alive = "
                    + t2IsAlive);

            if (!t1IsAlive && !t2IsAlive) {
                break;
            }

        }

        System.out.println("[" + currentThreadName + "] Main thread ends here...");
    }
}

class LoopTaskC implements Runnable{
    private static int count = 0;
    private int instanceNumber;
    private String taskId;

    @Override
    public void run() {
        System.out.println("##### [" + Thread.currentThread().getName() + "] <" + taskId + "> STARTING #####");
        for (int i=10; i>0; i--) {
            System.out.println("[" + Thread.currentThread().getName() + "] <" + taskId + ">TICK TICK " + i);
            try {
                TimeUnit.MILLISECONDS.sleep((long)(Math.random() * 1000));
            } catch (InterruptedException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
        System.out.println("***** [" + Thread.currentThread().getName() + "] <" + taskId + "> DONE ******");
    }

    public LoopTaskC() {
        this.instanceNumber = ++count;
        this.taskId = "LoopTaskC" + instanceNumber;
    }
}