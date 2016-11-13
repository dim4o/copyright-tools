/*
 * Copyright (C) 2016 Dimcho Nedev
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.coprtools.commands;

import com.coprtools.exceptions.InvalidCommandException;
import com.coprtools.util.FileManipulator;
import com.coprtools.util.SourceManipulator;

/**
 * A class that is responsible to create {@AbstractCommand command} object
 */
public class CommandFactory {
    /**
     * Creates the desired {@AbstractCommand object} by given command type and
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
            // TODO: Consider to remove this exception
            throw new InvalidCommandException();
        }

        return command;
    }
}
