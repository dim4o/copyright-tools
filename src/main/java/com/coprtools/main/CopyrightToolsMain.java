package com.coprtools.main;

import com.coprtools.cli.AbstractConsole;
import com.coprtools.cli.ApacheCliConsole;
import com.coprtools.commands.CommandFactory;
import com.coprtools.core.CopyrightToolsEngine;
import com.coprtools.util.FileManipulator;
import com.coprtools.util.SourceManipulator;
import com.coprtools.writer.ConsoleWriter;
import com.coprtools.writer.Writer;

public class CopyrightToolsMain {
    public static void main(String[] args) {
        AbstractConsole cli = new ApacheCliConsole(args);
        FileManipulator manipulator = new SourceManipulator();
        Writer writer = new ConsoleWriter();
        CommandFactory commandFactrory = new CommandFactory();
        CopyrightToolsEngine engine = new CopyrightToolsEngine(cli, manipulator, writer, commandFactrory);

        engine.run();
    }
}
