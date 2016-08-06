package com.copyrightinserter.main;

import java.io.File;
import java.io.IOException;
import java.util.logging.Logger;

import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.ParseException;

import com.copyrightinserter.util.SourceManipulator;
import com.copyrightinserter.cli.AbstractCli;
import com.copyrightinserter.cli.Cli;
import com.copyrightinserter.inserter.Inserter;
import com.copyrightinserter.inserter.NoticePosition;
import com.copyrightinserter.util.FileManipulator;

public class CopyrightInserterMain {

	private static final Logger LOGGER = Logger.getLogger(Inserter.class.getName());

	private static String LINE_SEPARATOR = System.getProperty("line.separator");

	private static String EMPTY_STRING = "";

	public static void main(String[] args) throws IOException, ParseException {
		AbstractCli cli = new Cli(args);
		CommandLine cmd = (CommandLine) cli.parse();

		/*
		 * if(cmd.hasOption("h")){ HelpFormatter formatter = new
		 * HelpFormatter(); formatter.printHelp("Copyright license inserter",
		 * "header", cli.getOptions(), "footer", true); }
		 */

		String rootFolder = cmd.getOptionValue("i");
		String noticePath = cmd.getOptionValue("n");
		String[] extensions = cmd.getOptionValues("e");

		File root = new File(rootFolder);
		File noticeFile = new File(noticePath);

		FileManipulator manipulator = new SourceManipulator();
		String notice = manipulator.readFromFile(noticeFile);

		Inserter inserter = new Inserter(manipulator, extensions);
		inserter.insert(root, notice, NoticePosition.Top);

		int all = inserter.getSucceedInserts() + inserter.getFailedIserts();
		float jobFinishPercent = 0;

		if (all > 0) {
			jobFinishPercent = (inserter.getSucceedInserts() / all) * 100;
		}

		System.out.println();
		LOGGER.info(String.format("Job finished at %s percents.", jobFinishPercent));
		LOGGER.info(String.format("%s succeed, %s failed inserts", inserter.getSucceedInserts(),
				inserter.getFailedIserts()));
	}

}
