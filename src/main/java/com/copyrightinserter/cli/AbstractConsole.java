package com.copyrightinserter.cli;

import com.copyrightinserter.exceptions.ArgumentParseException;

public abstract class AbstractConsole {
	private String[] arguments;
	
	protected AbstractConsole(String[] arguments) {
		this.arguments = arguments;
	}
	
	public String[] getArguments(){
		return this.arguments;
	}
	
	public abstract void parse() throws ArgumentParseException;
	
	public abstract boolean hasOption(String option);
	
	public abstract String getOptionValue(String option);
	
	public abstract String[] getOptionValues(String option);
	
	public abstract void showUsage();
	
}