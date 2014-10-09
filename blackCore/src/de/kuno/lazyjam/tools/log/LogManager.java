package de.kuno.lazyjam.tools.log;

import java.util.HashMap;

import de.kuno.lazyjam.tools.cdi.annotations.Service;

@Service
public class LogManager {

	private HashMap<String, Logger> logMap;
	private final Logger defaultLogger = new Logger("debug.log");

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
