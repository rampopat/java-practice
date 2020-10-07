package concurrency;

import java.util.LinkedList;
import java.util.List;

import concurrency.schedulers.Scheduler;

public class Executor {

	private ConcurrentProgram program;
	private Scheduler scheduler;

	public Executor(ConcurrentProgram program, Scheduler scheduler) {
		this.program = program;
		this.scheduler = scheduler;
	}

	/**
	 * Executes program with respect to scheduler
	 *
	 * @return the final state and history of execution
	 */
	public String execute() {
		List<Integer> history = new LinkedList<Integer>();
		boolean deadlockOccurred = false;

		while (!program.isTerminated() && !deadlockOccurred) {
			try {
				int thread = scheduler.chooseThread(program);
				program.step(thread);
				history.add(thread);
			} catch (DeadlockException e) {
				deadlockOccurred = true;
			}
		}

		StringBuilder result = new StringBuilder();
		result.append("Final state: " + program + "\n");
		result.append("History: " + history + "\n");
		result.append("Termination status: "
				+ (deadlockOccurred ? "deadlock" : "graceful") + "\n");
		return result.toString();
	}

	// An incorrect attempt at overriding the equals method
	// of Object
	public boolean equals(Executor that) {
		return program.toString() == that.program.toString();
	}

	@Override
	public boolean equals(Object o) {
		if (!(o instanceof Executor)) {
			return false;
		} else {
			Executor executor = (Executor) o;
			return (program.toString().equals(executor.program.toString()));
		}
	}

	@Override
	public int hashCode() {
		return program.toString().hashCode();
	}
}
