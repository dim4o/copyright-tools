package com.copyrightinserter.cli;

import org.apache.commons.cli.Options;
import org.apache.commons.cli.ParseException;

public interface AbstractCli {
	public Object parse() throws ParseException;
	
	public Options getOptions();
	
	public boolean hasOption(String option);
	
	void printHelp();
}
