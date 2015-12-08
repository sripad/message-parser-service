package com.coextrix.vehicletracking.messageparserservice.config;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class MessageParserServiceConfig {

	Properties prop = new Properties();

	public MessageParserServiceConfig() {
		super();
		init();
	}

	private void init() {
		InputStream input = null;
		try {
			input = new FileInputStream("build.properties");
			prop.load(input);
		} catch (IOException ex) {
			ex.printStackTrace();
		} finally {
			if (input != null) {
				try {
					input.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}

	public String getConfig(String key) {
		return prop.getProperty(key);
	}

	public int getConfigInt(String key) {
		try {
			return Integer.parseInt(prop.getProperty(key));
		} catch (Exception e) {
			return 0;
		}
	}
}
