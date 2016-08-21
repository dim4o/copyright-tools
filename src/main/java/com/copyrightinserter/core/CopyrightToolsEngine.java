package com.copyrightinserter.core;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.logging.FileHandler;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

import com.copyrightinserter.cli.AbstractConsole;
import com.copyrightinserter.commands.AbstractCommand;
import com.copyrightinserter.commands.CommandFactory;
import com.copyrightinserter.commands.CommandType;
import com.copyrightinserter.constants.ConsoleCommandConstants;
import com.copyrightinserter.constants.InserterConstants;
import com.copyrightinserter.constants.OptionConstants;
import com.copyrightinserter.constants.UserMessagesConstants;
import com.copyrightinserter.exceptions.ArgumentParseException;
import com.copyrightinserter.util.FileManipulator;
import com.copyrightinserter.writer.Writer;

import sun.reflect.generics.reflectiveObjects.NotImplementedException;

public class CopyrightToolsEngine implements Runnable {
    private static final Logger LOGGER = Logger.getLogger(Inserter.class.getName());

    private AbstractConsole cli;

    private FileManipulator manipulator;

    private Writer writer;

    private CommandFactory commandFactory;

    public CopyrightToolsEngine(
            AbstractConsole cli,
            FileManipulator manipulator,
            Writer writer,
            CommandFactory commandFactory) {
        this.cli = cli;
        this.manipulator = manipulator;
        this.writer = writer;
        this.commandFactory = commandFactory;
    }

    @Override
    public void run() {
        LOGGER.setLevel(Level.SEVERE);
        try {
            this.cli.parse();

            if (cli.hasOption(OptionConstants.HELP_SHORT)) {
                cli.showUsage();
                return;
            }

            String textConsoleCommand = cli.getArguments()[0];

            String rootFolderPath = cli.getOptionValue(OptionConstants.INSERT_SHORT);
            String noticePath = cli.getOptionValue(OptionConstants.NOTICE_SHORT);
            String[] extensions = cli.getOptionValues(OptionConstants.EXTENSION_SHORT);

            File rootDir = new File(rootFolderPath);
            if (cli.hasOption(OptionConstants.INFO_LONG)) {
                enableLogging(rootDir.getAbsolutePath());
            }

            File noticeFile = new File(noticePath);
            String notice = this.manipulator.readFromFile(noticeFile);
            if(cli.hasOption(OptionConstants.BLANK_SHORT)){
                notice = insertBlankSpaceAfterNotice(notice);
            }

            CommandType commandType = resolveCommandType(textConsoleCommand);

            AbstractCommand command = commandFactory.create(
                    commandType,
                    notice,
                    extensions,
                    this.manipulator);

            command.executeRecursivly(rootDir);

            if (!command.isHasError()) {
                writer.writeLine(UserMessagesConstants.SUCCESFULL_OPERATION_MESSAGE);
            } else {
                writer.writeLine(
                        UserMessagesConstants.FAILD_OPERTION_MESSAGE,
                        rootDir.getAbsolutePath() + File.separator + InserterConstants.LOG_FILENAME);
            }
        } catch (ArgumentParseException e) {
            LOGGER.log(Level.SEVERE, "Error while parsing arguments: " + e.getMessage());
        } catch (FileNotFoundException e) {
            LOGGER.log(Level.SEVERE, e.getMessage());
        } catch (SecurityException e) {
            LOGGER.log(Level.SEVERE, "Security violation has occurred: " + e.getMessage());
        } catch (IOException e) {
            LOGGER.log(Level.SEVERE, e.getMessage());
        } catch (Exception e) {
            LOGGER.log(Level.SEVERE, "Unknown exception: " + e.getClass().getName());
        }
    }

    private void enableLogging(String rootPath) throws SecurityException, IOException{
        LOGGER.setLevel(Level.ALL);
        String logFilePath = rootPath + File.separator + InserterConstants.LOG_FILENAME;
        FileHandler fileHandler = new FileHandler(logFilePath);
        fileHandler.setFormatter(new SimpleFormatter());
        fileHandler.setLevel(Level.ALL);
        LOGGER.addHandler(fileHandler);
    }

    private String insertBlankSpaceAfterNotice(String notice){
        int blankLines = Integer.parseInt(cli.getOptionValue("blank"));
        for (int i = 0; i < blankLines; i++) {
            notice += InserterConstants.LINE_SEPARATOR;
        }

        return notice;
    }

    private CommandType resolveCommandType(String consoleCommand){

        CommandType resultCommand = null;;
        switch (consoleCommand) {
        case ConsoleCommandConstants.INSERT:
            boolean bootomCondition = this.cli.hasOption(OptionConstants.BOOTOM_SHORT);
            resultCommand = bootomCondition
                    ? CommandType.INSERT_AFTER
                    : CommandType.INSERT_BEFORE;
            break;
        case ConsoleCommandConstants.REMOVE:
            resultCommand = CommandType.REMOVE;
            break;
        case ConsoleCommandConstants.REPLACE:
            resultCommand = CommandType.REPLACE;
            break;
        default:
            throw new NotImplementedException();
        }

        return resultCommand;
    }
}