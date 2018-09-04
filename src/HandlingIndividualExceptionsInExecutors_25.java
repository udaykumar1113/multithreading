import java.util.concurrent.ThreadFactory;

public class HandlingIndividualExceptionsInExecutors_25 {
    public static void main(String args[]){

    }
}

class ExceptionHandler_25 extends NamedThreadFactory_25 {

}


class NamedThreadFactory_25 implements ThreadFactory{
    String threadName="Thread-";
    static int count=0;

    @Override
    public Thread newThread(Runnable r) {
        Thread t=new Thread(r,threadName+ ++count);
        //t.setUncaughtExceptionHandler(new );
        return t;
    }
}