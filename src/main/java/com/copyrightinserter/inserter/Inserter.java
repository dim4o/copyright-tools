package com.copyrightinserter.inserter;

import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

import com.copyrightinserter.util.FileManipulator;

public class Inserter {

	private static final Logger LOGGER = Logger.getLogger(Inserter.class.getName());

	private static String LINE_SEPARATOR = System.getProperty("line.separator");

	private String[] extensions;
	
	private FileManipulator manipulator;

	public Inserter(FileManipulator manipulator, String[] extensions) throws IOException {
		this.extensions = extensions;
		this.manipulator = manipulator;
	}

	public void insertBefore(File file, String notice) throws IOException {
		String source = this.manipulator.readFromFile(file);
		String begin = notice + LINE_SEPARATOR;
		String newSource = begin + source;
		
		file.delete();
		file = new File(file.getAbsolutePath());
		file.getParentFile().mkdirs();
		file.createNewFile();
		
		// TODO: consider whether this trim() is necessary
		this.manipulator.writeToFile(file, newSource.trim());
	}

	void insertAfter(File file, String notice) throws IOException {
		this.manipulator.writeToFile(file, LINE_SEPARATOR + notice);
	}
	
	public void insert(File rootDir, String notice, NoticePosition position) {
		File[] files = rootDir.listFiles();
		for (File file : files) {
			if (!file.isDirectory()) {
				boolean isExt = containsExtension(file, this.extensions);
				try {
					if(isExt){
						if (position.equals(NoticePosition.Top)) {
							insertBefore(file, notice);
						} else {
							insertAfter(file, notice);
						}
						
						LOGGER.log(Level.INFO, String.format("%s - DONE", file.getName()));
					}
				} catch (IOException e) {
					// TODO Auto-generated catch block
					LOGGER.log(Level.SEVERE, String.format("%s - FAILS", file.getName()), e);
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
