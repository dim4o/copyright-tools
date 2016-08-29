package com.copyrightinserter.commands;

import com.copyrightinserter.exceptions.InvalidCommandException;
import com.copyrightinserter.util.FileManipulator;
import com.copyrightinserter.util.SourceManipulator;

public class CommandFactory {
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