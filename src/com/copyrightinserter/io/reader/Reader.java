package com.copyrightinserter.io.reader;

import java.io.Closeable;
import java.io.File;
import java.io.IOException;

public interface Reader extends Closeable {
	
	String readLine() throws IOException;
	
	Reader instantiate(File file) throws IOException;
	
	void close() throws IOException;
}
