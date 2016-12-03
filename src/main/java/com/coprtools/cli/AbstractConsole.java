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

package com.coprtools.cli;

import org.apache.commons.cli.MissingArgumentException;

import com.coprtools.exceptions.ArgumentParseException;

/**
 * This is a console abstraction that determines the behavior of all future
 * consoles.
 *
 * @author Dimcho Nedev
 */
public abstract class AbstractConsole {
    protected String[] arguments;

    protected AbstractConsole(String[] arguments) {
        this.arguments = arguments;
    }

    /**
     * Every console should can parse an arguments.
     *
     * @throws ArgumentParseException
     *             - thrown when fails to parse the arguments
     */
    public abstract void parse() throws ArgumentParseException;

    /**
     * Checks whether the arguments contains a specific option.
     *
     * @param option
     *            - the option that will be checked
     * @return <code>true</code> if the option exists, otherwise -
     *         <code>false</code>.
     */
    public abstract boolean hasOption(String option);

    /**
     * Gets an argument for the specific option.
     *
     * @param option
     *            - an existing option
     * @return an argument for an existing option
     * @throws MissingArgumentException
     *             - thrown when the option has no argument
     */
    // TODO: Remove the exception or add a custom exception. This abstract
    // method should be decoupled from apache.commons.cli
    public abstract String getOptionValue(String option) throws MissingArgumentException;

    /**
     * Gets all arguments for the specific option.
     *
     * @param option
     *            - an existing option
     * @return an array of the arguments for an existing option
     * @throws MissingArgumentException
     *             - thrown when the option has no arguments
     */
    // TODO: Remove the exception or add a custom exception. This abstract
    // method should be decoupled from apache.commons.cli
    public abstract String[] getOptionValues(String option) throws MissingArgumentException;

    /**
     * Getter for the arguments.
     *
     * @return an array of String arguments
     */
    public abstract String[] getArguments();

    /**
     * Visualizes the descriptions for each option
     */
    public abstract void showUsage();

}
