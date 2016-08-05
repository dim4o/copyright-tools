package com.copyrightinserter.main;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.CommandLineParser;
import org.apache.commons.cli.DefaultParser;
import org.apache.commons.cli.HelpFormatter;
import org.apache.commons.cli.Option;
import org.apache.commons.cli.Options;
import org.apache.commons.cli.ParseException;

import com.copyright.inserter.util.FileManipulator;
import com.copyright.inserter.util.SourceManipulator;
import com.copyrightinserter.inserter.Inserter;
import com.copyrightinserter.inserter.NoticePosition;

public class CopyrightInserterMain {

	private static String LINE_SEPARATOR = System.getProperty("line.separator");

	private static String EMPTY_STRING = "";

	public static void main(String[] args) throws IOException, ParseException {

		/*Options options = new Options();
		options.addOption("h", false, "Display help");
		CommandLineParser parser = new DefaultParser(); 
		CommandLine cmd = parser.parse(options, args);
		if(cmd.hasOption("h")){
			HelpFormatter formatter = new HelpFormatter();
			formatter.printHelp("Copyright license inserter", "header", options, "footer", true);
		}
		System.out.println("Runned!!!");*/
		Options options = new Options();
		options.addOption("h", false, "Display help");
		options.addOption(Option.builder("e")
					.longOpt("extensions")
					.optionalArg(false)
					.desc("This is file an extensions selector")
					.hasArgs()
					.build());	
		options.addOption("i", true, "Path to input folder");
		options.addOption("n", true, "Path to notice license text file");
		
		CommandLineParser parser = new DefaultParser(); 
		CommandLine cmd = parser.parse(options, args);
		
		if(cmd.hasOption("h")){
			HelpFormatter formatter = new HelpFormatter();
			formatter.printHelp("Copyright license inserter", "header", options, "footer", true);
		}

		String rootFolder = cmd.getOptionValue("i");
		String noticePath = cmd.getOptionValue("n");
		String[] extensions = cmd.getOptionValues("e");
		
		File root = new File(rootFolder);
		File noticeFile = new File(noticePath);

		FileManipulator manipulator = new SourceManipulator();
		String notice = manipulator.readFromFile(noticeFile);
		
		Inserter inserter = new Inserter(manipulator, extensions);
		inserter.insert(root, notice, NoticePosition.Bottom);
	}
	
}
