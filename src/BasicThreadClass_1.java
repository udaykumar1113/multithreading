import java.util.concurrent.TimeUnit;

public class BasicThreadClass_1 {
    public static void main(String args[]){
        ThreadWorker threadWorker = new ThreadWorker();
        new ThreadWorker();
    }
}

class ThreadWorker extends Thread {
    public static int counter=0;
    private int id;

    public ThreadWorker() {
        id=this.counter++;
        this.start();
    }

    public void run() {
            for (int i = 0; i < 10; i++) {
                System.out.println("<Thread"+id+"> "+i);
                try {
                    TimeUnit.MILLISECONDS.sleep(200);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
}
