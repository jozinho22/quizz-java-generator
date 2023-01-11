package com.douineau.qjgenerator.utils;

import java.io.File;
import java.io.IOException;

public class ResourcesFileReader {

	public File getFile(String path) throws IOException {
		return new File(this.getClass().getClassLoader().getResource(path).getFile());
	}
}
