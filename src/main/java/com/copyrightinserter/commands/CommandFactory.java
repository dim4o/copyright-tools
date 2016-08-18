package com.copyrightinserter.commands;

import com.copyrightinserter.constants.CommandConstants;

import sun.reflect.generics.reflectiveObjects.NotImplementedException;

public class CommandFactory {
    public AbstractCommand create(String commandName, Object... args){
        AbstractCommand command = null;

        switch (commandName) {
        case CommandConstants.INSERT_BEFORE:
            command = new InsertBeforeCommand(args);
            break;
        case CommandConstants.INSERT_AFTER:
            command = new InsertAfterCommand(args);
            break;
        case CommandConstants.REMOVE:
            command = new RemoveCommand(args);
            break;
        case CommandConstants.REPLACE:
            command = new ReplaceCommand(args);
            break;
        default:
            throw new NotImplementedException();
        }

        return command;
    }
}