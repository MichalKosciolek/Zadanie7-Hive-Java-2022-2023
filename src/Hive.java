public class Hive{
    static int numberOfBees;
    Entrance en = new Entrance();
    public Hive(int numberOfBees) {
        Hive.numberOfBees = numberOfBees;
        Thread[] bees = new Thread[numberOfBees+1];
        for(int i=0; i<numberOfBees; i++){
            Thread b = new Thread(new Bee(en), Integer.toString(i+1));
            bees[i] = b;
            b.start();
        }
        for(int i=0; i<numberOfBees; i++){
            try {
                bees[i].join();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }
}