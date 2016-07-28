package com.copyrightinserter.main;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.copyrightinserter.inserter.Inserter;
import com.copyrightinserter.inserter.NoticePosition;
import com.copyrightinserter.io.reader.NoticeReader;
import com.copyrightinserter.io.reader.Reader;
import com.copyrightinserter.io.writer.NoticeWriter;
import com.copyrightinserter.io.writer.Writer;

public class CopyrightInserterMain {

	private static String LINE_SEPARATOR = System.getProperty("line.separator");

	private static String EMPTY_STRING = "";
	
	public static void main(String[] args) throws IOException {
		File root = new File("C:\\Users\\Dimcho\\Desktop\\testCopyright");
		File noticeFile = new File("C:\\Users\\Dimcho\\Desktop\\Notice.txt");
		
		String notice = getNoticeFromFile(noticeFile); // "// This is top comment"
		
		Reader reader = new NoticeReader();
		Writer writer = new NoticeWriter();
		List<String> extensions = new ArrayList<String>();
		extensions.add(".java");
		extensions.add(".txt");
		
		Inserter inserter = new Inserter(reader, writer, extensions);
		inserter.insert(root, notice, NoticePosition.Top);
	}
	
	public static String getNoticeFromFile(File file) throws IOException{
		BufferedReader reader = new BufferedReader(new FileReader(file));
		
		StringBuilder noticeMessage = new StringBuilder();
		String line = EMPTY_STRING;
		
		while ((line = reader.readLine()) != null) {
			noticeMessage.append(line);
			noticeMessage.append(LINE_SEPARATOR);
		}
		
		return noticeMessage.toString().trim();
	}
}
