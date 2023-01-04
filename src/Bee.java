import java.util.Random;

public class Bee implements Runnable{

    boolean pos = true;
    Random rand = new Random();

    public void goIn() {
        System.out.println("Pszczoła " + Thread.currentThread().getName() + " wlatuje przez przelot");
        pos = true;
        int timeSpent = rand.nextInt(5000);
        try {
            Thread.sleep(timeSpent+1000);
        }
         catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    public void goOut() {
        System.out.println("Pszczoła " + Thread.currentThread().getName() + " wylatuje z przelotu");
        pos = false;
        int timeSpent = rand.nextInt(5000)+5;
        try {
            Thread.sleep(timeSpent+6000);
        }
        catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void run() {
        while(true){
            if(pos){
                System.out.println("Pszczoła " + Thread.currentThread().getName() + " podlatuje pod przelot");
                goOut();
            }
            else{
                System.out.println("Pszczoła " + Thread.currentThread().getName() + " podlatuje pod przelot");
                goIn();
            }
        }
    }
}
