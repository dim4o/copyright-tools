package com.copyrightinserter.io.reader;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.Closeable;

public class NoticeReader implements Reader {

	private BufferedReader bufferedReader = null;
	
	public NoticeReader(){}
	
	private NoticeReader(File file) throws IOException {
		FileReader fileReader = new FileReader(file);
		this.bufferedReader = new BufferedReader(fileReader);
	}
	
	@Override
	public String readLine() throws IOException {
		String line = bufferedReader.readLine();
		return line;
	}

	@Override
	public void close() throws IOException {
		bufferedReader.close();
	}

	@Override
	public Reader instantiate(File file) throws IOException {
		return new NoticeReader(file);
	}
}
