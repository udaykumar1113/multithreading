public class HandlingThreadUncaughtExceptionsIndividually_22 {
    public static void main(String args[]){
        Thread thread1=new Thread(new ExceptionLeakingTask_22(),"My Thread 1");
        thread1.setUncaughtExceptionHandler(new ExceptionHandler_22());

        Thread thread2=new Thread(new ExceptionLeakingTask_22(),"My Thread 2");
        thread2.setUncaughtExceptionHandler(new ExceptionHandler_22());

        Thread thread3=new Thread(new ExceptionLeakingTask_22(),"My Thread 3");
        thread3.setUncaughtExceptionHandler(new ExceptionHandler_22());

        Thread thread4=new Thread(new ExceptionLeakingTask_22(),"My Thread 4");
        thread4.setUncaughtExceptionHandler(new ExceptionHandler_22());

        thread1.start();
        thread2.start();
        thread3.start();
        thread4.start();
    }
}

class ExceptionLeakingTask_22 implements Runnable{

    @Override
    public void run() {
        throw new RuntimeException();
    }
}

class ExceptionHandler_22 implements Thread.UncaughtExceptionHandler{

    @Override
    public void uncaughtException(Thread t, Throwable e) {
        System.out.println(this+" exception caught for thread "+Thread.currentThread().getName());
    }
}