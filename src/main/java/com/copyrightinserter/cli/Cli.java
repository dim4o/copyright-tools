package com.copyrightinserter.cli;

import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.CommandLineParser;
import org.apache.commons.cli.DefaultParser;
import org.apache.commons.cli.HelpFormatter;
import org.apache.commons.cli.Option;
import org.apache.commons.cli.Options;
import org.apache.commons.cli.ParseException;

public class Cli implements AbstractCli{
	
	private Options options;
	
	private String[] arguments;
	
	public Cli(String args[]) {
		this.arguments = args;
		this.options = initalizeOptions();
	}
	
	private Options initalizeOptions(){
		Options options = new Options();
		options.addOption("h", false, "Display help");
		options.addOption(Option.builder("e")
					.longOpt("extensions")
					.optionalArg(false)
					.desc("This is file an extensions selector")
					.hasArgs()
					.build());	
		options.addOption("i", true, "Path to input folder");
		options.addOption("n", true, "Path to notice license text file");
		options.addOption(Option.builder("t")
				.longOpt("top")
				.optionalArg(false)
				.desc("Insert on top")
				.hasArg(false)
				.build());
		options.addOption(Option.builder("b")
				.longOpt("bottom")
				.optionalArg(false)
				.desc("Insert on top")
				.hasArg(false)
				.build());
		
		return options;
	}
	
	@Override
	public Options getOptions(){
		return this.options;
	}
	
	@Override
	public Object parse() throws ParseException{
		CommandLineParser parser = new DefaultParser();
		CommandLine cmd = parser.parse(options, arguments);
		
		return cmd;
	}

	@Override
	public boolean hasOption(String option) {
		return this.options.hasOption(option);
	}

	@Override
	public void printHelp() {
		if(this.hasOption("h")){
			HelpFormatter formatter = new HelpFormatter();
			formatter.printHelp("Copyright license inserter", "header", this.options, "footer", true);
		}
	}
}
