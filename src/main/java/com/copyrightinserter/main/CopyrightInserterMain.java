package com.copyrightinserter.main;

import java.io.File;
import java.io.IOException;

import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.ParseException;

import com.copyrightinserter.util.FileManipulator;
import com.copyrightinserter.util.SourceManipulator;
import com.copyrightinserter.cli.AbstractCli;
import com.copyrightinserter.cli.Cli;
import com.copyrightinserter.inserter.Inserter;
import com.copyrightinserter.inserter.NoticePosition;

public class CopyrightInserterMain {

	private static String LINE_SEPARATOR = System.getProperty("line.separator");

	private static String EMPTY_STRING = "";

	public static void main(String[] args) throws IOException, ParseException {
		AbstractCli cli = new Cli(args);
		CommandLine cmd = (CommandLine)cli.parse();
		
		/*if(cmd.hasOption("h")){
			HelpFormatter formatter = new HelpFormatter();
			formatter.printHelp("Copyright license inserter", "header", cli.getOptions(), "footer", true);
		}*/

		String rootFolder = cmd.getOptionValue("i");
		String noticePath = cmd.getOptionValue("n");
		String[] extensions = cmd.getOptionValues("e");
		
		File root = new File(rootFolder);
		File noticeFile = new File(noticePath);

		FileManipulator manipulator = new SourceManipulator();
		String notice = manipulator.readFromFile(noticeFile);
		
		Inserter inserter = new Inserter(manipulator, extensions);
		inserter.insert(root, notice, NoticePosition.Top);
	}
	
}
