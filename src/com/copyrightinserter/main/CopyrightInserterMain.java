package com.copyrightinserter.main;

import java.io.File;
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

	public static void main(String[] args) throws IOException {
		File root = new File("C:\\Users\\Dimcho\\Desktop\\testCopyright");
		Reader reader = new NoticeReader();
		Writer writer = new NoticeWriter();
		List<String> extensions = new ArrayList<String>();
		extensions.add(".java");
		extensions.add(".txt");
		
		Inserter inserter = new Inserter(reader, writer, extensions);
		inserter.insert(root, "// This is top comment", NoticePosition.Top);
	}
}
