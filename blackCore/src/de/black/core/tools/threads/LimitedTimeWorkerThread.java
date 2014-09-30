package de.black.core.tools.threads;

import de.black.core.tools.stuff.ATask;

public class LimitedTimeWorkerThread extends WorkerThread {

	private int maxTimes;

	public LimitedTimeWorkerThread(int sleepTimeMillis, ATask event, int maxTimes) {
		super(sleepTimeMillis, event);
		this.maxTimes = maxTimes;
	}

	@Override
	public void run() {
		do {
			super.run();
			this.maxTimes--;
		} while (maxTimes > 0 && !this.kill);
	}

}
