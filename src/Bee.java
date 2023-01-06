import java.util.Iterator;
import java.util.Random;
import java.util.concurrent.locks.Condition;

public class Bee implements Runnable{
    boolean pos = true;
    Random rand = new Random();
    Entrance en;

    public Bee(Entrance en){
        this.en = en;
    }

    /* Wlatywanie do ula */
    public void goIn(int entrance) {
        try {
            //czas potrzebny na wlecenie
            Thread.sleep(1000);
            System.out.println("Pszczoła " + Thread.currentThread().getName() + " wlatuje przez przelot " + entrance);
            pos = true;
            int timeSpent = rand.nextInt(5)+1;
            Thread.sleep(timeSpent*1000);
        }
        catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    /* Wylatywanie z ula */
    public void goOut(int entrance) {
        try {
            //czas potrzebny na wylecenie
            Thread.sleep(1000);
            System.out.println("Pszczoła " + Thread.currentThread().getName() + " wylatuje z przelotu " + entrance);
            pos = false;
            int timeSpent = rand.nextInt(6)+5;
            Thread.sleep(timeSpent*1000);
        }
        catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void run() {
        while(true){
            System.out.println("Pszczoła " + Thread.currentThread().getName() + " podlatuje pod przelot 1");
            if(en.e1.tryLock()){
                try{
                    if(pos){
                        goOut(1);
                    }
                    else{
                        goIn(1);
                    }
                }
                finally {
                    en.e1.unlock();
                }
            }
            else{
                System.out.println("Pszczoła " + Thread.currentThread().getName() + " przelot 1 zajęty, sprawdza przelot 2");
                if(en.e2.tryLock()){
                    en.isLocked = true;
                    try{
                        if(pos){
                            goOut(2);
                        }
                        else{
                            goIn(2);
                        }
                    }
                    finally {
                        try{
                            en.isLocked = false;
                            en.isFree.signalAll();
                        }
                        finally {
                            en.e2.unlock();
                        }
                    }
                }
                else{
                    System.out.println("Pszczoła " + Thread.currentThread().getName() + " przelot 2 zajęty, oczekuję przelot 2");
                    try {
                        en.isLocked = true;
                        while(en.isLocked){
                            en.isFree.await();
                        }
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                    /*
                    finally {
                        en.e2.unlock();
                    }
                    */
                }
            }
        }
    }
}
