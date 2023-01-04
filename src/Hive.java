public class Hive{
    boolean entry = true;
    static int numberOfBees;
    public Hive(int numberOfBees){
        Hive.numberOfBees = numberOfBees;
        Thread[] bees = new Thread[numberOfBees+1];
        for(int i=0; i<numberOfBees; i++){
            Thread b = new Thread(new Bee(), Integer.toString(i+1));
            bees[i] = b;
            b.start();
        }
    }

}