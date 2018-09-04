import java.util.concurrent.*;

public class ExecutorCompletionService_13 {
    public static void main(String args[]){
        ExecutorService executorService= Executors.newCachedThreadPool();
        CompletionService<TaskResult<String, Integer>> completionTask= new ExecutorCompletionService<TaskResult<String, Integer>>(executorService);

        completionTask.submit(new CalculationServiceB(1,2,2000));
        completionTask.submit(new CalculationServiceB(3,4,1500));
        completionTask.submit(new CalculationServiceB(5,6,500));
        completionTask.submit(new CalculationServiceB(7,8,100));
        completionTask.submit(new LoopTaskB(), new TaskResult<>("Loop Task B",50000));

        for(int i=0;i<5;i++){
            try {
                System.out.println("Result "+i+ completionTask.take().get());
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (ExecutionException e) {
                e.printStackTrace();
            }
        }
    }

}

class CalculationServiceB implements Callable<TaskResult<String, Integer>> {
    private int a;
    private int b;
    private int sleepTime;

    public CalculationServiceB(int a, int b, int sleepTime) {
        this.a = a;
        this.b = b;
        this.sleepTime = sleepTime;
    }


    @Override
    public TaskResult<String, Integer> call() throws Exception {
        Thread.sleep(sleepTime);
        return new TaskResult<String, Integer>(Thread.currentThread().getName(),a+b);
    }
}

class LoopTaskB implements Runnable{

    @Override
    public void run() {
        for(int i=0;i<10;i++){
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(" "+i);
        }
    }
}

class TaskResult<T,S>{
    private T taskId;
    private S result;

    public TaskResult(T taskId, S result) {
        this.taskId = taskId;
        this.result = result;
    }

    public T getTaskId() {
        return taskId;
    }

    public void setTaskId(T taskId) {
        this.taskId = taskId;
    }

    public S getResult() {
        return result;
    }

    public void setResult(S result) {
        this.result = result;
    }

    @Override
    public String toString() {
        return "~~~~~~~~~~~~~~~~TaskResult{" +
                "taskId=" + taskId +
                ", result=" + result +
                "}~~~~~~~~~~~~~~~";
    }
}