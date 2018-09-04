import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadFactory;

public class DaemonsWithExecutors_15 {
    public static void main(String args[]){
        ExecutorService executorService= Executors.newCachedThreadPool(new DaemonThreadFactory());
        executorService.execute(new LoopTaskD(500));
        executorService.execute(new LoopTaskD(5000));
        executorService.execute(new LoopTaskD(500));

        executorService.shutdown();
    }
}

class DaemonThreadFactory extends NamedThreadFactory{
    private static int count=0;

    public Thread newThread(Runnable r){
        Thread t=super.newThread(r);
        count++;
        if(count%2==0) {
            t.setDaemon(true);
        }
        return t;
    }
}

class NamedThreadFactory implements ThreadFactory {
    String name="Thread-";
    private static int count=0;
    @Override
    public Thread newThread(Runnable r) {
        Thread t=new Thread(r, name+ ++count);
        return t;
    }
}