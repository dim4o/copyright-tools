package com.copyrightinserter.main;

import java.io.File;
import java.io.IOException;

import com.copyrightinserter.io.reader.Reader;
import com.copyrightinserter.io.writer.Writer;

public class Inserter {

	private static String LINE_SEPARATOR = System.getProperty("line.separator");

	private static String EMPTY_STRING = "";

	private Reader reader;

	private Writer writer;

	public Inserter(Reader reader, Writer writer) throws IOException {
		this.reader = reader;
		this.writer = writer;
	}

	public void insertBefore(File file, String notice) throws IOException {

		try {

			String result = EMPTY_STRING;
			String line = EMPTY_STRING;

			try {
				this.reader = reader.instantiate(file);

				while ((line = this.reader.readLine()) != null) {
					result += line + LINE_SEPARATOR;
				}

				result.trim();
				String begin = notice + LINE_SEPARATOR;
				result = begin + result;

			} catch (IOException e) {
				throw e;
			} finally {
				this.reader.close();
			}

			file.delete();
			file = new File(file.getAbsolutePath());
			file.getParentFile().mkdirs();
			file.createNewFile();

			this.writer = writer.Instantiate(file);
			this.writer.write(result.trim());
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			writer.close();
		}
	}

	void insertAfter(File file, String notice) throws IOException {
		try {
			this.writer = writer.Instantiate(file);
			this.writer.write(LINE_SEPARATOR);
			this.writer.write(notice);
		} catch (IOException e) {
			e.printStackTrace();
		} finally{
			writer.close();
		}
	}
	
	public void insert(File rootDir, String notice) throws IOException {
		File[] files = rootDir.listFiles();
		for (File file : files) {
			if (!file.isDirectory()) {
				System.out.println(file.getName());
				boolean isExt = file.getName().endsWith(".java");
				if (isExt) {
					insertBefore(file, notice);
				}
			} else {
				insert(file, notice);
			}
		}
	}
}
