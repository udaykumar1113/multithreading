public class DefaultNOverrideExceptionHandling_23 {
    public static void main(String args[]){
        Thread.setDefaultUncaughtExceptionHandler(new ExceptionHandler_23("Default Handler"));

        Thread thread1=new Thread(new ExceptionLeakingTask_23(),"Thread-1");
        thread1.setUncaughtExceptionHandler(new ExceptionHandler_23("Custom Handler-1"));

        Thread thread2=new Thread(new ExceptionLeakingTask_23(), "Thread-2");

        Thread thread3=new Thread(new ExceptionLeakingTask_23(),"Thread-3");
        thread3.setUncaughtExceptionHandler(new ExceptionHandler_23("Custom Handler-2"));

        Thread thread4=new Thread(new ExceptionLeakingTask_23(), "Thread-4");

        thread1.start();
        thread2.start();
        thread3.start();
        thread4.start();
    }
}

class ExceptionLeakingTask_23 implements Runnable{

    @Override
    public void run() {
        throw new RuntimeException();
    }
}

class ExceptionHandler_23 implements Thread.UncaughtExceptionHandler{
    String handlerId;

    public ExceptionHandler_23(String handlerId) {
        this.handlerId=handlerId;
    }

    @Override
    public String toString() {
        return this.getClass().getSimpleName()+ " "+this.hashCode();
    }

    @Override
    public void uncaughtException(Thread t, Throwable e) {
        System.out.println(handlerId+" exception caught for thread "+Thread.currentThread().getName()+ e.getMessage());
    }
}