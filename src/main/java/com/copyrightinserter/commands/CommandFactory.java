package com.copyrightinserter.commands;

import com.copyrightinserter.constants.CommandConstants;

import sun.reflect.generics.reflectiveObjects.NotImplementedException;

public class CommandFactory {
    AbstractCommand create(String commandName, Object... args){
        AbstractCommand command = null;
        switch (commandName) {
        case CommandConstants.INSERT_BEFORE:
            // TODO: assign args
            command = new InsertBeforeCommand(args);
            break;
        case CommandConstants.INSERT_AFTER:
            // TODO: assign args
            command = new InsertAfterCommand(args);
            break;
        case CommandConstants.REMOVE:
            // TODO: assign args
            command = new RemoveCommand(args);
            break;
        case CommandConstants.REPLACE:
            // TODO: assign args
            command = new ReplaceCommand(args);
            break;
        default:
            throw new NotImplementedException();
        }

        return command;
    }
}