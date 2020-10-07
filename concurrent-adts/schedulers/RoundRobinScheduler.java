package concurrency.schedulers;

import concurrency.ConcurrentProgram;
import concurrency.DeadlockException;
import java.util.HashSet;
import java.util.Set;

public class RoundRobinScheduler extends SchedulerImplementation {

  private boolean isFirstChoice = true;
  private int currentThread;

  @Override
  public int chooseThread(ConcurrentProgram program) throws DeadlockException {
    Set<Integer> threads = program.getEnabledThreadIds();
    if (threads.isEmpty()) {
      throw new DeadlockException();
    } else {
      if (isFirstChoice) {
        isFirstChoice = false;
        return getMinThread(threads);
      } else {
        Set<Integer> validThreads = new HashSet<>();

        for (int thread : threads) {
          if (thread > currentThread) {
            validThreads.add(thread);
          }
        }

        if (validThreads.isEmpty()) {
          currentThread = getMinThread(threads);
        } else {
          currentThread = getMinThread(validThreads);
        }
        return currentThread;
      }
    }
  }

}
