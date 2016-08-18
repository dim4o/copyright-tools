package com.copyrightinserter.cli;

import com.copyrightinserter.exceptions.ArgumentParseException;

public abstract class AbstractConsole {
    protected String[] arguments;

    protected AbstractConsole(String[] arguments) {
        this.arguments = arguments;
    }

    public abstract void parse() throws ArgumentParseException;

    public abstract boolean hasOption(String option);

    public abstract String getOptionValue(String option);

    public abstract String[] getOptionValues(String option);

    public abstract String[] getArguments();

    public abstract void showUsage();

}