package concurrency.schedulers;

import concurrency.ConcurrentProgram;
import concurrency.statements.Stmt;
import concurrency.statements.WaitStmt;
import java.util.Iterator;
import java.util.Set;

public abstract class SchedulerImplementation implements Scheduler {

  protected int getMinThread(Set<Integer> threads) {
    Iterator<Integer> iterator = threads.iterator();
    int thread = iterator.next();

    while (iterator.hasNext()) {
      int nextThread = iterator.next();
      if (nextThread < thread) {
        thread = nextThread;
      }
    }

    return thread;
  }

}
