package com.douineau.utils;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class PropertiesReader {
	
	public static Properties getProperties(String path) throws IOException {
		Properties prop = new Properties();
		try {
			InputStream stream = PropertiesReader.class.getResourceAsStream(path);

			prop.load(stream);
		} catch (FileNotFoundException fnfe) {
			fnfe.printStackTrace();
		} catch (IOException ioe) {
			ioe.printStackTrace();
		}
		return prop;
	}
}
