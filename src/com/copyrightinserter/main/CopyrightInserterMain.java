package com.copyrightinserter.main;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.io.RandomAccessFile;

import javax.swing.text.StyledEditorKit.FontFamilyAction;

import com.copyrightinserter.io.reader.NoticeReader;
import com.copyrightinserter.io.reader.Reader;
import com.copyrightinserter.io.writer.NoticeWriter;
import com.copyrightinserter.io.writer.Writer;

public class CopyrightInserterMain {

	private static String LINE_SEPARATOR = System.getProperty("line.separator");
	private static String EMPTY_STRING = "";

	public static void insertNoticeToFile(File file, String notice, boolean header) throws IOException {
		BufferedWriter writer = null;
		BufferedReader reader = null;
		try {
			FileWriter fileWriter = new FileWriter(file, true);
			writer = new BufferedWriter(fileWriter);
			String result = EMPTY_STRING;
			if (header) {
				
				String line = EMPTY_STRING;
				try {
					reader = new BufferedReader(new FileReader(file));

					while ((line = reader.readLine()) != null) {
						result += line + LINE_SEPARATOR;
					}
					//result.replaceFirst("", "");
					result.trim();
					String begin = notice + LINE_SEPARATOR;
					result = begin + result;

				} catch (IOException e) {
					throw e;
				} finally {
					reader.close();
					writer.close();
				}

				boolean deleted = file.delete();
				if (!deleted) {
					// throw new IOException("");
				}

				file = new File(file.getAbsolutePath());
				System.out.println("./" + file.getName());
				file.getParentFile().mkdirs();
				file.createNewFile();
			} else {
				result = LINE_SEPARATOR + notice;
			}
			
			fileWriter = new FileWriter(file, true);
			writer = new BufferedWriter(fileWriter);
			writer.write(result.trim());
		} catch (IOException e) {
			throw e;
		} finally {
			writer.close();
		}
	}

	public static void main(String[] args) throws IOException {
		/*
		 * File file = new File("out.java"); insertNoticeToFile(file, "start");
		 */
		File root = new File("C:\\Users\\Dimcho\\Desktop\\testCopyright");
		Reader reader = new NoticeReader();
		Writer writer = new NoticeWriter();

		Inserter inserter = new Inserter(reader, writer);
		inserter.insert(root, "// This is top comment");
	}
	
	

	public static void traverse(File rootDir) throws IOException {
		File[] files = rootDir.listFiles();
		for (File file : files) {
			if (!file.isDirectory()) {
				System.out.println(file.getName());
				boolean isExt = file.getName().endsWith(".java");
				if (isExt) {
					insertNoticeToFile(file, "// This is top comment", true);
				}
			} else {
				traverse(file);
			}
		}
	}
}
