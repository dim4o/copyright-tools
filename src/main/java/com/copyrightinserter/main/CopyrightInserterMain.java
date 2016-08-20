package com.copyrightinserter.main;

import com.copyrightinserter.cli.AbstractConsole;
import com.copyrightinserter.cli.ApacheCliConsole;
import com.copyrightinserter.commands.CommandFactory;
import com.copyrightinserter.core.CopyrightToolsEngine;
import com.copyrightinserter.core.InsertEngine;
import com.copyrightinserter.util.FileManipulator;
import com.copyrightinserter.util.SourceManipulator;
import com.copyrightinserter.writer.ConsoleWriter;
import com.copyrightinserter.writer.Writer;

public class CopyrightInserterMain {
    public static void main(String[] args) {
        AbstractConsole cli = new ApacheCliConsole(args);
        FileManipulator manipulator = new SourceManipulator();
        Writer writer = new ConsoleWriter();
        CommandFactory commandFactrory = new CommandFactory();
        //InsertEngine engine = new InsertEngine(cli, manipulator, writer);
        CopyrightToolsEngine engine = new CopyrightToolsEngine(cli, manipulator, writer, commandFactrory);

        engine.run();
    }
}
