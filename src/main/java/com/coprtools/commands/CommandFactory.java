package com.coprtools.commands;

import com.coprtools.exceptions.InvalidCommandException;
import com.coprtools.util.FileManipulator;
import com.coprtools.util.SourceManipulator;

/**
 * A class that is responsible to create command object
 */
public class CommandFactory {
    /**
     * Creates the desired AbstractCommand object by given command type and
     * specific set of parameters
     *
     * @param commandType
     *        - the type of the command
     * @param args
     *        - the command arguments
     * @return the desired command object
     * @throws InvalidCommandException
     */
    public AbstractCommand create(CommandType commandType, Object... args)
            throws InvalidCommandException{
        AbstractCommand command = null;

        String notice = (String) args[0];
        String[] extension = (String[]) args[1];
        FileManipulator manipulator = (SourceManipulator) args[2];

        switch (commandType) {
        case INSERT_BEFORE:
            command = new InsertBeforeCommand(notice, extension, manipulator);
            break;
        case INSERT_AFTER:
            command = new InsertAfterCommand(notice, extension, manipulator);
            break;
        case REMOVE:
            command = new RemoveCommand(notice, extension, manipulator);
            break;
        case REPLACE:
            String newNotice = (String) args[3];
            command = new ReplaceCommand(notice, extension, manipulator, newNotice);
            break;
        default:
            throw new InvalidCommandException();
        }

        return command;
    }
}