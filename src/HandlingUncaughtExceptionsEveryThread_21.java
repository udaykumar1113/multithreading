public class HandlingUncaughtExceptionsEveryThread_21 {
    public static void main(String args[]){

        Thread.setDefaultUncaughtExceptionHandler(new ExceptionHandler());
        new Thread(new ExceptionLeakingTask()).start();
        new Thread(new ExceptionLeakingTask()).start();
        new Thread(new ExceptionLeakingTask()).start();
        new Thread(new ExceptionLeakingTask()).start();
    }
}

class ExceptionLeakingTask implements Runnable{

    @Override
    public void run() {
        throw new RuntimeException();
    }
}

class ExceptionHandler implements Thread.UncaughtExceptionHandler{

    @Override
    public void uncaughtException(Thread t, Throwable e) {
        System.out.println(this+" exception caught for thread "+Thread.currentThread().getName());
    }
}