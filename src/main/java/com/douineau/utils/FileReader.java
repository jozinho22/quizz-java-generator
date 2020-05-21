package com.douineau.utils;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

public class FileReader {

	public File getFile(String path) throws IOException {

		return new File(this.getClass().getClassLoader().getResource(path).getFile());
	}
}
