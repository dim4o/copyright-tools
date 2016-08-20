package com.copyrightinserter.commands;

import java.io.File;

import com.copyrightinserter.constants.CommandConstants;
import com.copyrightinserter.util.FileManipulator;
import com.copyrightinserter.util.SourceManipulator;

import sun.reflect.generics.reflectiveObjects.NotImplementedException;

public class CommandFactory {
    public AbstractCommand create(String commandName, Object... args){
        AbstractCommand command = null;

        String notice = (String) args[0];
        String[] extension = (String[]) args[1];
        FileManipulator manipulator = (SourceManipulator) args[2];

        switch (commandName) {
        case CommandConstants.INSERT_BEFORE:
            command = new InsertBeforeCommand(notice, extension, manipulator);
            break;
        case CommandConstants.INSERT_AFTER:
            //command = new InsertAfterCommand(args);
            break;
        case CommandConstants.REMOVE:
            //command = new RemoveCommand(args);
            break;
        case CommandConstants.REPLACE:
            //command = new ReplaceCommand(args);
            break;
        default:
            throw new NotImplementedException();
        }

        return command;
    }
}