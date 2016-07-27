package com.copyrightinserter.io.writer;

import java.io.BufferedWriter;
import java.io.Closeable;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class NoticeWriter implements Writer, Closeable {

	private BufferedWriter bufferedWriter = null;
	
	public NoticeWriter(){}
	
	private NoticeWriter(File file) throws IOException {
		this.bufferedWriter = new BufferedWriter(new FileWriter(file, true));
	}
	
	@Override
	public void write(String copyrightNotice) throws IOException {
		this.bufferedWriter.write(copyrightNotice);
	}
	
	@Override
	public void close() throws IOException {
		this.bufferedWriter.close();
	}

	@Override
	public Writer Instantiate(File file) throws IOException {
		return new NoticeWriter(file);
	}
}
