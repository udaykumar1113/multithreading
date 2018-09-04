import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class DefaultExceptionHandlingExecutors_24 {
    public static void main(String args[]){
        ExecutorService executorService_1= Executors.newCachedThreadPool();
        ExecutorService executorService_2= Executors.newCachedThreadPool();

        executorService_1.execute(new LeakingException());
        executorService_1.execute(new LeakingException());

        executorService_2.execute(new LeakingException());
        executorService_2.execute(new LeakingException());

        executorService_1.shutdown();
        executorService_2.shutdown();
    }
}

class LeakingException implements Runnable{

    @Override
    public void run() {
        throw new RuntimeException();
    }
}