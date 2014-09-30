package de.black.core.tools.log;

import java.io.BufferedWriter;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Logger {

	private String logPath;
	private BufferedWriter writer;
	private SimpleDateFormat startFormat = new SimpleDateFormat("dd.MM.yyyy HH:mm:ss");
	private SimpleDateFormat defaultFormat = new SimpleDateFormat("HH:mm:ss");

	public Logger(String path) {

		this.logPath = path;
		try {
			writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(logPath), "utf-8"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Date d = new Date(System.currentTimeMillis());
		this.log("Log created at " + startFormat.format(d), false);
	}

	public void log(String message) {
		log(message, true);
	}

	private void log(String message, boolean time) {
		try {
			if (time) {
				Date d = new Date(System.currentTimeMillis());
				writer.write(defaultFormat.format(d) + " -- ");
			}
			writer.write(message + "\n");
			writer.flush();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	protected void finalize() throws Throwable {
		writer.close();
	}

}
