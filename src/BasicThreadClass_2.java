import java.util.concurrent.TimeUnit;

public class BasicThreadClass_2 {
    public static void main(String args[]){
        ThreadRunnableWorker threadWorker = new ThreadRunnableWorker();
        new ThreadRunnableWorker();
    }
}

class ThreadRunnableWorker implements Runnable {
    public static int counter=0;
    private int id;

    public ThreadRunnableWorker() {
        id=this.counter++;
        new Thread(this).start();
    }

    public void run() {
        for (int i = 0; i < 10; i++) {
            System.out.println("<Thread "+id+"> "+i);
            try {
                TimeUnit.MILLISECONDS.sleep(200);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
