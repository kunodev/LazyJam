package de.black.core.tools.threads;

import de.black.core.tools.stuff.ATask;

public class WorkerThread extends Thread {
	
	private int sleepTimeMillis;
	private ATask task;
	public boolean running = true;
	public boolean kill = false;
	
	public WorkerThread(int sleepTimeMillis, ATask event ) {
		this.task = event;
		this.sleepTimeMillis = sleepTimeMillis;
	}
	
	@Override
	public void run() {
		if(running) {
			task.execute();
			try {
				sleep(sleepTimeMillis);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

}
