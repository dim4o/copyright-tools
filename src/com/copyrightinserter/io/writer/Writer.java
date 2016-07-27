package com.copyrightinserter.io.writer;

import java.io.Closeable;
import java.io.File;
import java.io.IOException;

import com.copyrightinserter.io.reader.Reader;

public interface Writer extends Closeable {
	void write(String copyrightNotice) throws IOException;
	
	void close() throws IOException;
	
	Writer Instantiate(File file) throws IOException;
}
