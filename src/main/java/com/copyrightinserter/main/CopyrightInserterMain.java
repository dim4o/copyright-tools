package com.copyrightinserter.main;

import com.copyrightinserter.cli.AbstractConsole;
import com.copyrightinserter.cli.ApacheCliConsole;
import com.copyrightinserter.inserter.InsertEngine;
import com.copyrightinserter.util.FileManipulator;
import com.copyrightinserter.util.SourceManipulator;
import com.copyrightinserter.writer.ConsoleWriter;
import com.copyrightinserter.writer.Writer;

public class CopyrightInserterMain {
    public static void main(String[] args) {
        AbstractConsole cli = new ApacheCliConsole(args);
        FileManipulator manipulator = new SourceManipulator();
        Writer writer = new ConsoleWriter();
        InsertEngine engine = new InsertEngine(cli, manipulator, writer);

        engine.run();
    }
}
