import java.util.concurrent.TimeUnit;

public class ThreadInterruptUsingNormalThreads_18 {
    public static void main(String args[]) throws InterruptedException {
        Thread t1=new Thread(new LoopTaskF(),"Thread-1");
        Thread t2=new Thread(new LoopTaskF(),"Thread-2");
        Thread t3=new Thread(new LoopTaskF(),"Thread-3");
        Thread t4=new Thread(new LoopTaskF(),"Thread-4");
        t1.start();
        t2.start();
        t3.start();
        t4.start();

        TimeUnit.MILLISECONDS.sleep(5000);

        t1.interrupt();
        t2.interrupt();
        t3.interrupt();
        t4.interrupt();
    }
}

class LoopTaskF implements Runnable{

    public LoopTaskF() {
    }

    @Override
    public void run() {
        for(int i=1;;i++){
            System.out.println(Thread.currentThread().getName()+ " "+i);

            if(Thread.interrupted()){
                System.out.println(Thread.currentThread().getName()+" interrupted");
                break;
            }
            doSomeWork();
        }
    }

    private void doSomeWork() {
        System.out.println("Doing work of inside loop");
    }
}