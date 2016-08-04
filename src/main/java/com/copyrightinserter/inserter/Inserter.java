package com.copyrightinserter.inserter;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import com.copyrightinserter.io.reader.Reader;
import com.copyrightinserter.io.writer.Writer;

public class Inserter {

	private static final Logger log = Logger.getLogger(Inserter.class.getName());

	private static String LINE_SEPARATOR = System.getProperty("line.separator");

	private static String EMPTY_STRING = "";

	private Reader reader;

	private Writer writer;
	
	private String[] extensions;

	public Inserter(Reader reader, Writer writer, String[] extensions) throws IOException {
		this.reader = reader;
		this.writer = writer;
		this.extensions = extensions;
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
			// TODO: Exception message
			log.log(Level.SEVERE, "", e);
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
	
	public void insert(File rootDir, String notice, NoticePosition position) throws IOException {
		File[] files = rootDir.listFiles();
		for (File file : files) {
			if (!file.isDirectory()) {
				System.out.println(file.getName());
				boolean isExt = containsExtension(file, this.extensions);
				if (isExt && position.equals(NoticePosition.Top)) {
					insertBefore(file, notice);
				} else if(isExt){
					insertAfter(file, notice);
				}
			} else {
				insert(file, notice, position);
			}
		}
	}
	
	boolean containsExtension(File file, String[] fileExtensions){
		
		for (String extension : fileExtensions) {
			if(file.getName().endsWith(extension)){
				return true;
			}
		}
		
		return false;
	}
}
