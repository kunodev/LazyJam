package de.black.core.tools.log;

import java.util.HashMap;

public class LogManager {

	private static LogManager instance;
	private HashMap<String, Logger> logMap;
	private final Logger defaultLogger = new Logger("debug.log");

	public static LogManager getInstance() {
		if (instance == null)
			instance = new LogManager();
		return instance;
	}

	public LogManager() {
		this.logMap = new HashMap<String, Logger>();
	}

	public void addLogger(String key, Logger l) {
		this.logMap.put(key, l);
	}

	public void log(String message) {
		this.log(defaultLogger, message);
	}

	public void log(String loggerName, String message) {
		Logger l = this.logMap.get(loggerName);
		if (l != null) {
			this.log(l, message);
		}
	}

	private void log(Logger logger, String message) {
		logger.log(message);
	}

}
