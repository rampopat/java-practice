package concurrency.schedulers;

import concurrency.ConcurrentProgram;
import concurrency.DeadlockException;
import concurrency.statements.Stmt;
import concurrency.statements.WaitStmt;
import java.util.HashSet;
import java.util.Set;

public class FewestWaitsScheduler extends SchedulerImplementation {

  @Override
  public int chooseThread(ConcurrentProgram program) throws DeadlockException {
    Set<Integer> threads = program.getEnabledThreadIds();
    if (threads.isEmpty()) {
      throw new DeadlockException();
    } else {
      Set<Integer> minThreads = new HashSet<>();
      int minNumWaitStatements = Integer.MAX_VALUE;
      for (int thread : threads) {
        int numWaitStatements = countWaitStatements(program, thread);
        if (numWaitStatements < minNumWaitStatements) {
          minNumWaitStatements = numWaitStatements;
          minThreads = new HashSet<>();
          minThreads.add(thread);
        } else if (numWaitStatements == minNumWaitStatements) {
          minThreads.add(thread);
        }
      }
      return getMinThread(minThreads);
    }
  }

  protected static int countWaitStatements(ConcurrentProgram program, int thread) {
    int count = 0;
    for (Stmt statement : program.remainingStatements(thread)) {
      if (statement instanceof WaitStmt) {
        count += 1;
      }
    }
    return count;
  }

}
