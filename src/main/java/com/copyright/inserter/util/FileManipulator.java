package com.copyright.inserter.util;

import java.io.File;

public interface FileManipulator {
	String readFromFile(File file);
	
	void writeToFile(File file, String source);
}
