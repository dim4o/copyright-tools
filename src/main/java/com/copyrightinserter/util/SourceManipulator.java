package com.copyrightinserter.util;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class SourceManipulator implements FileManipulator{

	private static String LINE_SEPARATOR = System.getProperty("line.separator");

	private static String EMPTY_STRING = "";
	
	@Override
	public String readFromFile(File file){
		StringBuilder sourceBuilder = new StringBuilder();
		String line = EMPTY_STRING; // TODO: Try = null initialization
		String source = null;
		BufferedReader reader = null;
		
		try {
			reader = new BufferedReader(new FileReader(file));
			
			while((line = reader.readLine()) != null){
				sourceBuilder.append(line);
				sourceBuilder.append(LINE_SEPARATOR);
			}
			
			source = sourceBuilder.toString().trim();
			
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO: handle exception
		} finally {
			// TODO: Change the next block
			try {
				reader.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		
		return source;
	}

	@Override
	public void writeToFile(File file, String source) {
		BufferedWriter writer = null;
		
		try {
			writer = new BufferedWriter(new FileWriter(file, true));
			writer.write(source);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			// TODO: Change the next block
			try {
				writer.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

}
