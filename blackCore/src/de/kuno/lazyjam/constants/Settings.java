package de.kuno.lazyjam.constants;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import de.kuno.lazyjam.tools.cdi.annotations.Service;
import de.kuno.lazyjam.tools.log.LogManager;

@Service
public class Settings {

	public static String SCREEN_HEIGHT = "SCREENHEIGHT";
	public static String SCREEN_WIDTH = "SCREENWIDTH";

	private JSONObject data;
	private String path;
	private boolean loaded;

	public Settings() {
		data = new JSONObject();
		loaded = false;
		setFilePath("settings.json");
		load();
	}

	public void setFilePath(String path) {
		this.path = path;
	}

	public void load() {
		JSONParser parser;
		Object obj;
		BufferedReader in = null;
		/* Can't load any config */
		if (path.equals("")) {
			System.err.println("Could not load config. Path not set");
			return;
		}

		try {
			in = new BufferedReader(new InputStreamReader(new FileInputStream(path), "utf-8"));
			parser = new JSONParser();
			obj = parser.parse(in);
			data = (JSONObject) obj;
			loaded = true;
		} catch (FileNotFoundException e) {
			setToDefault(this);
			System.err.println("no settings file found, creating fallback!");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void save() {
		BufferedWriter out = null;
		/* Can't save any config */
		if (path.equals("")) {
			System.err.println("Could not save config. Path not set");
			return;
		}

		try {
			out = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(path), "utf-8"));
			data.writeJSONString(out);
			// String jsonText = out.toString();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				out.close();
			} catch (Exception ex) {
			}
		}
	}

	public int getInt(Object key) {
		if (!loaded) {
			System.err.println("Settings not loaded");
			return -1;
		}

		Object odata = data.get(key);

		if (odata == null) {
			System.err.println("Key '" + key + "' in Settings not found");
			return -1;
		}

		return ((Number) odata).intValue();
	}

	public String getString(Object key) {
		String result;
		if (!loaded) {
			System.err.println("Settings not loaded");
			return "";
		}
		result = (String) data.get(key);

		if (result == null) {
			System.err.println("Key '" + key + "' in Settings not found");
		}

		return result;
	}

	@SuppressWarnings("unchecked")
	public void putData(Object key, Object value) {
		data.put(key, value);
	}

	public static void setToDefault(Settings s) {
		s.putData(SCREEN_WIDTH, 1024);
		s.putData(SCREEN_HEIGHT, 786);
		s.loaded = true;
		s.save();
	}

}
