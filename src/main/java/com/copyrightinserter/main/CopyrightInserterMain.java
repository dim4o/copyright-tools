package com.copyrightinserter.main;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.logging.FileHandler;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

import com.copyrightinserter.cli.AbstractConsole;
import com.copyrightinserter.cli.ApacheCliConsole;
import com.copyrightinserter.constants.OptionConstants;
import com.copyrightinserter.constants.UserMessagesConstants;
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

            if (cli.hasOption(OptionConstants.HELP_SHORT)) {
                cli.showUsage();
            }

            LOGGER.setLevel(Level.SEVERE);

            String rootFolder = cli.getOptionValue(OptionConstants.INSERT_SHORT);
            String noticePath = cli.getOptionValue(OptionConstants.NOTICE_SHORT);
            String[] extensions = cli.getOptionValues(OptionConstants.EXTENSION_SHORT);
            NoticePosition noticePotition = cli.hasOption(OptionConstants.BOOTOM_SHORT) ? NoticePosition.Bottom
                    : NoticePosition.Top;

            File root = new File(rootFolder);
            if (cli.hasOption("info")) {
                LOGGER.setLevel(Level.ALL);
                String logFilePath = root.getAbsolutePath() + "/CopyrightInserter.log";
                FileHandler fileHandler = new FileHandler(logFilePath);
                fileHandler.setFormatter(new SimpleFormatter());
                fileHandler.setLevel(Level.ALL);
                LOGGER.addHandler(fileHandler);
            }

            File noticeFile = new File(noticePath);
            FileManipulator manipulator = new SourceManipulator();
            String notice = manipulator.readFromFile(noticeFile);
            Inserter inserter = new Inserter(manipulator, extensions);
            inserter.insert(root, notice, noticePotition);

            System.out.println();
            if (inserter.getFailedIserts() == 0) {
                System.out.println(UserMessagesConstants.SUCCESFULL_OPERATION_MESSAGE);
            } else {
                System.out.println(UserMessagesConstants.FAILD_OPERTION_MESSAGE);
            }
        } catch (ArgumentParseException e) {
            LOGGER.log(Level.SEVERE, "Error while parsing arguments: " + e.getMessage());
        } catch (FileNotFoundException e) {
            LOGGER.log(Level.SEVERE, e.getMessage());
        } catch (SecurityException e) {
            LOGGER.log(Level.SEVERE, "security violation has occurred: " + e.getMessage());
        } catch (IOException e) {
            LOGGER.log(Level.SEVERE, e.getMessage());
        } catch (Exception e) {
            LOGGER.log(Level.SEVERE, "Unknown exception: " + e.getClass().getName());
        }
    }
}
