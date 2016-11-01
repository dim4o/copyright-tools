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

public abstract class AbstractConsole {
    protected String[] arguments;

    protected AbstractConsole(String[] arguments) {
        this.arguments = arguments;
    }

    public abstract void parse() throws ArgumentParseException;

    public abstract boolean hasOption(String option);

    public abstract String getOptionValue(String option) throws MissingArgumentException;

    public abstract String[] getOptionValues(String option);

    public abstract String[] getArguments();

    public abstract void showUsage();

}
