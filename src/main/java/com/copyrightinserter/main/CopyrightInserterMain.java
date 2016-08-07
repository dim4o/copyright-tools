package com.copyrightinserter.main;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

import com.copyrightinserter.cli.AbstractConsole;
import com.copyrightinserter.cli.ApacheCliConsole;
import com.copyrightinserter.constants.OptionConstants;
import com.copyrightinserter.exceptions.ArgumentParseException;
import com.copyrightinserter.inserter.Inserter;
import com.copyrightinserter.inserter.NoticePosition;
import com.copyrightinserter.util.FileManipulator;
import com.copyrightinserter.util.SourceManipulator;

public class CopyrightInserterMain {

	private static final Logger LOGGER = Logger.getLogger(Inserter.class.getName());

	public static void main(String[] args) {

		try {
			AbstractConsole cli = new ApacheCliConsole(args);
			cli.parse();

			if (cli.hasOption("h")) {
				cli.showUsage();
			}
			/*
			 * Level level = cli.hasOption("info") ? Level.OFF : Level.ALL;
			 * LOGGER.setLevel(level);
			 */

			String rootFolder = cli.getOptionValue(OptionConstants.INSERT_SHORT);
			String noticePath = cli.getOptionValue(OptionConstants.NOTICE_SHORT);
			String[] extensions = cli.getOptionValues(OptionConstants.EXTENSION_SHORT);
			NoticePosition noticePotition = 
					cli.hasOption(OptionConstants.BOOTOM_SHORT) ? NoticePosition.Bottom : NoticePosition.Top;

			File root = new File(rootFolder);
			File noticeFile = new File(noticePath);

			FileManipulator manipulator = new SourceManipulator();
			String notice = manipulator.readFromFile(noticeFile);
			Inserter inserter = new Inserter(manipulator, extensions);
			inserter.insert(root, notice, noticePotition);

			int all = inserter.getSucceedInserts() + inserter.getFailedIserts();
			float jobFinishPercent = 0;

			if (all > 0) {
				jobFinishPercent = (inserter.getSucceedInserts() / all) * 100;
			}

			System.out.println();
			LOGGER.info(String.format("Job finished at %s percents.", jobFinishPercent));
			LOGGER.info(String.format("%s succeed, %s failed inserts", inserter.getSucceedInserts(),
					inserter.getFailedIserts()));

		} catch (ArgumentParseException e) {
			LOGGER.log(Level.SEVERE, "Error while parsing arguments: " + e.getMessage());
		} catch (FileNotFoundException e) {
			LOGGER.log(Level.SEVERE, e.getMessage());
		} catch (IOException e) {
			LOGGER.log(Level.SEVERE, e.getMessage());
		} catch (Exception e) {
			LOGGER.log(Level.SEVERE, "Unknown exception: " + e.getClass().getName());
		}
	}

}
