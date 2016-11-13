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

import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.CommandLineParser;
import org.apache.commons.cli.DefaultParser;
import org.apache.commons.cli.HelpFormatter;
import org.apache.commons.cli.Option;
import org.apache.commons.cli.OptionBuilder;
import org.apache.commons.cli.Options;
import org.apache.commons.cli.ParseException;

import com.coprtools.constants.OptionConstants;
import com.coprtools.constants.UsageConstants;
import com.coprtools.exceptions.ArgumentParseException;

public class ApacheCliConsole extends AbstractConsole {

    private Options options;

    private CommandLine cmd;

    public ApacheCliConsole(String arguments[]) {
        super(arguments);
        this.options = initalizeOptions();
    }

    private Options initalizeOptions() {
        Options options = new Options();

        // help option
        options.addOption(Option.builder(OptionConstants.HELP_SHORT)
                .longOpt(OptionConstants.HELP_LONG)
                .optionalArg(true)
                .desc(UsageConstants.HELP_OPTION_DESC).build());

        // file extensions list
        options.addOption(Option.builder(OptionConstants.EXTENSION_SHORT)
                .longOpt(OptionConstants.EXTENSION_LONG)
                .optionalArg(false)
                .required()
                .desc(UsageConstants.EXTENSIONS_OPTION_DESC)
                .hasArgs().build());

        // input folder location
        options.addOption(Option.builder(OptionConstants.ROOT_SHORT)
                .longOpt(OptionConstants.ROOT_LONG)
                .optionalArg(false)
                .required()
                .desc(UsageConstants.ROOT_OPTION_DESC)
                .hasArg().build());

        // notice file location
        options.addOption(Option.builder(OptionConstants.NOTICE_SHORT)
                .longOpt(OptionConstants.NOTICE_LONG)
                .optionalArg(false)
                .required()
                .desc(UsageConstants.NOTICE_OPTION_DESC)
                .hasArg().build());

        // new notice file location
        options.addOption(Option.builder(OptionConstants.NEW_NOTICE_SHORT)
                .longOpt(OptionConstants.NEW_NOTICE_LONG)
                .optionalArg(false)
                .desc(UsageConstants.NEW_NOTICE_OPTION_DESC)
                .hasArg().build());

        // insert on bottom of the source
        options.addOption(Option.builder(OptionConstants.BOOTOM_SHORT)
                .longOpt(OptionConstants.BOTTOM_LONG)
                .optionalArg(false)
                .desc(UsageConstants.BOTTOM_OPTION_DESCR).build());

        // enable job info logging option
        options.addOption(Option.builder()
                .longOpt(OptionConstants.INFO_LONG)
                .optionalArg(true)
                .desc(UsageConstants.INFO_OPTION_DESC).build());

        // add blank line before/after notice
        options.addOption(Option.builder(OptionConstants.BLANK_SHORT)
                .longOpt(OptionConstants.BLANK_LONG)
                .optionalArg(true)
                .desc(UsageConstants.BLANK_OPTION_DESC).build());

        // specifies an output destination
        options.addOption(Option.builder(OptionConstants.OUTPUT_SHORT)
                .longOpt(OptionConstants.OUTPUT_LONG)
                .optionalArg(true)
                .desc(UsageConstants.OUTPUT_OPTION_DESC).build());

        /*
         * The notice argument will be parsed as a string (not as a file path).
         * With this option you can remove/replace a string.
         */
        options.addOption(Option.builder(OptionConstants.STRING_SHORT)
                .longOpt(OptionConstants.STRING_LONG)
                .optionalArg(true)
                .desc(UsageConstants.STRING_OPTION_DESC).build());

        return options;
    }

    @Override
    public void parse() throws ArgumentParseException {
        CommandLineParser parser = new DefaultParser();
        try {
            this.cmd = parser.parse(options, this.arguments);
        } catch (ParseException e) {
            throw new ArgumentParseException(e.getMessage());
        }
    }

    @Override
    public boolean hasOption(String option) {
        return this.cmd.hasOption(option);
    }

    @Override
    public void showUsage() {
        HelpFormatter formatter = new HelpFormatter();
        formatter.printHelp(
                UsageConstants.USAGE,
                UsageConstants.HEADER,
                this.options,
                UsageConstants.FOOTER,
                false);
    }

    @Override
    public String getOptionValue(String option) {
        return this.cmd.getOptionValue(option);
    }

    @Override
    public String[] getOptionValues(String option) {
        return this.cmd.getOptionValues(option);
    }

    @Override
    public String[] getArguments() {
        return this.cmd.getArgs();
    }
}
