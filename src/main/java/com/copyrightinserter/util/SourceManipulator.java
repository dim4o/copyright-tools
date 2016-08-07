package com.copyrightinserter.util;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;

import com.copyrightinserter.constants.InserterConstants;

public class SourceManipulator implements FileManipulator{
	@Override
	public String readFromFile(File file) throws FileNotFoundException, IOException{
		StringBuilder sourceBuilder = new StringBuilder();
		String line = InserterConstants.EMPTY_STRING;
		String source = null;
		
		try (BufferedReader reader = new BufferedReader(new FileReader(file))){
			while((line = reader.readLine()) != null){
				sourceBuilder.append(line);
				sourceBuilder.append(InserterConstants.LINE_SEPARATOR);
			}
			
			source = sourceBuilder.toString().trim();
		}
		
		return source;
	}

	@Override
	public void writeToFile(File file, String source) throws IOException {
		
		try (Writer writer = new BufferedWriter(new FileWriter(file, true))){
			writer.write(source);
		}
	}

}
