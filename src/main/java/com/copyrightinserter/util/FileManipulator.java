package com.copyrightinserter.util;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

public interface FileManipulator {
	String readFromFile(File file) throws FileNotFoundException, IOException;
	
	void writeToFile(File file, String source) throws IOException;
}
