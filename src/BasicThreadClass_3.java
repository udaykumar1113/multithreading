import java.util.concurrent.TimeUnit;

public class BasicThreadClass_3 {
    public static void main(String args[]){
         new Thread(new ThreadRunWorker()).start();
         Thread t =new Thread(new ThreadRunWorker());
         t.start();
    }
}

class ThreadRunWorker implements Runnable {
    public static int counter=0;
    private int id;

    public ThreadRunWorker() {
        id=this.counter++;

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
