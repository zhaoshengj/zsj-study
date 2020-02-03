package study.thread;

public class ThreadTest  extends Thread{

    public static void main(String[] args) {
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println("");
            }
        });

        try {
            thread.wait();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        try {
            thread.sleep(2);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
