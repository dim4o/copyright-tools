package com.copyrightinserter.cli;

import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.CommandLineParser;
import org.apache.commons.cli.DefaultParser;
import org.apache.commons.cli.HelpFormatter;
import org.apache.commons.cli.Option;
import org.apache.commons.cli.Options;
import org.apache.commons.cli.ParseException;

import com.copyrightinserter.exceptions.ArgumentParseException;

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
		options.addOption(Option.builder("h")
				.longOpt("help")
				.optionalArg(true)
				.desc("Display help")
				.build());
		
		// file extensions list
		options.addOption(Option.builder("e")
				.longOpt("extensions")
				.optionalArg(false)
				.desc("File extensions selector. Expects list of space separated extensions")
				.hasArgs()
				.build());
		
		// input folder location
		options.addOption(Option.builder("i")
				.longOpt("input")
				.optionalArg(false)
				.desc("Path to input folder")
				.hasArg()
				.build());
		
		// notice file location
		options.addOption(Option.builder("n")
				.longOpt("notice")
				.optionalArg(false)
				.desc("Path to notice license text file")
				.hasArg()
				.build());
		
		// Insert on top of the source
		options.addOption(Option.builder("t")
				.longOpt("top")
				.optionalArg(false)
				.desc("Insert on top")
				.hasArg(false).build());
		
		// Insert on bottom of the source
		options.addOption(Option.builder("b")
				.longOpt("bottom")
				.optionalArg(false)
				.desc("Insert on bottom")
				.build());

		// Print info option
		/*options.addOption(Option.builder("info")
				.optionalArg(true)
				.desc("")
				.build());*/
		
		return options;
	}

	public void parse() throws ArgumentParseException {
		CommandLineParser parser = new DefaultParser();
		try {
			this.cmd = parser.parse(options, this.getArguments());
		} catch (ParseException e) {
			throw new ArgumentParseException(e.getMessage());
		}
	}

	public boolean hasOption(String option) {
		return this.cmd.hasOption(option);
	}
	
	public void showUsage() {
		HelpFormatter formatter = new HelpFormatter();
		formatter.printHelp("Copyright license inserter", "header", this.options, "footer", true);
	}

	@Override
	public String getOptionValue(String option) {
		return this.cmd.getOptionValue(option);
	}

	@Override
	public String[] getOptionValues(String option) {
		return this.cmd.getOptionValues(option);
	}
	
}
