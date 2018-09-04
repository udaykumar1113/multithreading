import java.util.concurrent.TimeUnit;

public class InterruptingBlockedThreads_19 {
    public static void main(String[] args) throws InterruptedException {
        String currentThreadName = Thread.currentThread().getName();
        System.out.println("[" + currentThreadName + "] Main thread starts here...");

        LoopTaskG task1 = new LoopTaskG();
        LoopTaskG task2 = new LoopTaskG();
        LoopTaskG task3 = new LoopTaskG();

        LoopTaskH task4 = new LoopTaskH();
        LoopTaskH task5 = new LoopTaskH();

        Thread t1 = new Thread(task1, "MyThread-1");
        t1.start();

        Thread t2 = new Thread(task2, "MyThread-2");
        t2.start();

        Thread t3 = new Thread(task3, "MyThread-3");
        t3.start();

        Thread t4 = new Thread(task4, "MyThread-4");
        t4.start();

        Thread t5 = new Thread(task5, "MyThread-5");
        t5.start();

        TimeUnit.MILLISECONDS.sleep(3000);

        System.out.println("[" + currentThreadName + "] Interrupting " + t1.getName() + "....");
        t1.interrupt();

        System.out.println("[" + currentThreadName + "] Interrupting " + t2.getName() + "....");
        t2.interrupt();

        System.out.println("[" + currentThreadName + "] Interrupting " + t3.getName() + "....");
        t3.interrupt();

        System.out.println("[" + currentThreadName + "] Interrupting " + t4.getName() + "....");
        t4.interrupt();

        System.out.println("[" + currentThreadName + "] Interrupting " + t5.getName() + "....");
        t5.interrupt();

        System.out.println("[" + currentThreadName + "] Main thread ends here...");
    }
}

class LoopTaskG implements Runnable{
    private static int count = 0;
    private int instanceNumber;
    private String taskId;


    @Override
    public void run() {
        String currentThreadName = Thread.currentThread().getName();

        System.out.println("##### [" + currentThreadName + "] <" + taskId + "> STARTING #####");

        for (int i=1;; i++) {
            System.out.println("[" + currentThreadName + "] <" + taskId + ">TICK TICK " + i);

            try {
                TimeUnit.MILLISECONDS.sleep((long)(Math.random() * 3000));
            } catch (InterruptedException e) {
                System.out.println("***** [" + currentThreadName + "] <" + taskId + "> Sleep Interrupted. Cancelling ...");
                break;
            }

        }

        System.out.println("***** [" + currentThreadName + "] <" + taskId + "> DONE ******");
    }


    public LoopTaskG() {
        this.instanceNumber = ++count;
        this.taskId = "LoopTaskG" + instanceNumber;
    }
}

class LoopTaskH implements Runnable{
    private static int count = 0;
    private int instanceNumber;
    private String taskId;

    private boolean sleepInterrupted = false;

    @Override
    public void run() {
        String currentThreadName = Thread.currentThread().getName();

        System.out.println("##### [" + currentThreadName + "] <" + taskId + "> STARTING #####");

        for (int i=1;; i++) {
            System.out.println("[" + currentThreadName + "] <" + taskId + ">TICK TICK " + i);

            try {
                TimeUnit.MILLISECONDS.sleep((long)(Math.random() * 3000));
            } catch (InterruptedException e) {
                System.out.println("***** [" + currentThreadName + "] <" + taskId + "> Sleep Interrupted. " +
                        "SETTING THE FLAG ...");
                sleepInterrupted = true;
            }

            doSomeMoreWork();

            if (sleepInterrupted || Thread.interrupted()) {
                System.out.println("***** [" + currentThreadName + "] <" + taskId + "> INTERRUPTED. Cancelling ...");
                break;
            }
        }

        System.out.println("***** [" + currentThreadName + "] <" + taskId + "> DONE ******");
    }


    private void doSomeMoreWork() {
        System.out.println("***** [" + Thread.currentThread().getName() + "] <" + taskId + "> DOING SOME MORE WORK ...");
    }

    public LoopTaskH() {
        this.instanceNumber = ++count;
        this.taskId = "LoopTaskH" + instanceNumber;
    }
}