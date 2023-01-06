import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

public class Entrance {
    final ReentrantLock e1 = new ReentrantLock();
    final ReentrantLock e2 = new ReentrantLock();
    final Condition isFree = e2.newCondition();
    boolean isLocked = false;
}
