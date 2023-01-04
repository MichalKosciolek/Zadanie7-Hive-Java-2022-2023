import java.util.ArrayList;

public class Hive implements Runnable{
    boolean entry = true;
    static int numberOfBees;
    Thread[] bees = new Thread[numberOfBees];
    Hive h = new Hive(numberOfBees);

    @Override
    public void run() {
        Bee bee = new Bee();
        while(true){
            if(bee.pos){
                System.out.println("Pszczoła" + Thread.currentThread().getName() + "podlatuje pod przelot");
                bee.goOut();
            }
            else{
                System.out.println("Pszczoła" + Thread.currentThread().getName() + "podlatuje pod przelot");
                bee.goIn();
            }
        }
    }
    public Hive(int numberOfBees){
        Hive.numberOfBees = numberOfBees;
        for(int i=0; i<numberOfBees; i++){
            Thread b = new Thread(h, Integer.toString(i+1));
            bees[i] = b;
            b.start();
        }
    }

}
