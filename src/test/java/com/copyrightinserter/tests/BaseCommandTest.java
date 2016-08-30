package com.copyrightinserter.tests;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;

import org.junit.After;
import org.junit.Assume;
import org.junit.Before;

import com.copyrightinserter.cli.AbstractConsole;
import com.copyrightinserter.cli.ApacheCliConsole;
import com.copyrightinserter.commands.CommandFactory;
import com.copyrightinserter.constants.InserterConstants;
import com.copyrightinserter.core.CopyrightToolsEngine;
import com.copyrightinserter.util.FileManipulator;
import com.copyrightinserter.util.SourceManipulator;
import com.copyrightinserter.writer.ConsoleWriter;

public class BaseCommandTest {

    private static final String SAMPLE_CONTENT =
            "This is sample content line 1."
            + System.getProperty("line.separator")
            + "This is sample content line 2";

    protected static final String NOTICE =
            "// This is a sample notice 1"
            + System.getProperty("line.separator")
            + "// This is a sample notice 2";

    protected File javaFile1, javaFile2, csFile1, csFile2, cppFile1, cppFile21, cppFile22;

    protected String javaFile_1_content, javaFile_2_content, csFile_1_content,
        csFile_2_content, cppFile_1_content, cppFile_2_1_content, cppFile_2_2_content;

    @Before
    public void setUp() throws Exception {
        Writer writer;
        // Create directories
        File temp = new File("./temp");
        temp.mkdir();

        File rootFolderDir = new File("./temp/rootDir");
        rootFolderDir.mkdir();

        File firstChildDir = new File("./temp/rootDir/firstChild");
        firstChildDir.mkdir();

        File secondChildDir = new File("./temp/rootDir/secondChild");
        secondChildDir.mkdir();

        // Create and add files
        javaFile1 = new File("./temp/rootDir/firstChild/javaFile_1.java");
        javaFile1.getParentFile().mkdirs();
        javaFile1.createNewFile();
        writer = new BufferedWriter(new FileWriter(javaFile1, true));
        writer.write(SAMPLE_CONTENT);
        writer.close();

        csFile1 = new File("./temp/rootDir/firstChild/csFile_1.cs");
        csFile1.getParentFile().mkdirs();
        csFile1.createNewFile();
        writer = new BufferedWriter(new FileWriter(csFile1, true));
        writer.write(SAMPLE_CONTENT);
        writer.close();

        cppFile1 = new File("./temp/rootDir/firstChild/cppFile_1.cpp");
        cppFile1.getParentFile().mkdirs();
        cppFile1.createNewFile();
        writer = new BufferedWriter(new FileWriter(cppFile1, true));
        writer.write(SAMPLE_CONTENT);
        writer.close();

        javaFile2 = new File("./temp/rootDir/secondChild/javaFile_2.java");
        javaFile2.getParentFile().mkdirs();
        javaFile2.createNewFile();
        writer = new BufferedWriter(new FileWriter(javaFile2, true));
        writer.write(SAMPLE_CONTENT);
        writer.close();

        csFile2 = new File("./temp/rootDir/secondChild/csFile_2.cs");
        csFile2.getParentFile().mkdirs();
        csFile2.createNewFile();
        writer = new BufferedWriter(new FileWriter(csFile2, true));
        writer.write(SAMPLE_CONTENT);
        writer.close();

        cppFile21 = new File("./temp/rootDir/secondChild/cppFile_21.cpp");
        cppFile21.getParentFile().mkdirs();
        cppFile21.createNewFile();
        writer = new BufferedWriter(new FileWriter(cppFile21, true));
        writer.write(SAMPLE_CONTENT);
        writer.close();

        cppFile22 = new File("./temp/rootDir/secondChild/cppFile_22.cpp");
        cppFile22.getParentFile().mkdirs();
        cppFile22.createNewFile();
        writer = new BufferedWriter(new FileWriter(cppFile22, true));
        writer.write(SAMPLE_CONTENT);
        writer.close();

        File noticeFile = new File("./temp/notice.txt");
        noticeFile.getParentFile().mkdirs();
        noticeFile.createNewFile();
        writer = new BufferedWriter(new FileWriter(noticeFile, true));
        writer.write(NOTICE);
        writer.close();
    }

    @After
    public void tearDown() throws Exception {
        File rootDir = new File("./temp");
        deleteFilesRecursively(rootDir);
        Assume.assumeFalse(rootDir.exists());
    }

    protected void executeCommand(String command) throws FileNotFoundException, IOException {
        String args[] = command.split(" ");
        AbstractConsole cli = new ApacheCliConsole(args);
        FileManipulator manipulator = new SourceManipulator();
        com.copyrightinserter.writer.Writer writer = new ConsoleWriter();
        CommandFactory commandFactrory = new CommandFactory();
        CopyrightToolsEngine  engine = new CopyrightToolsEngine(cli, manipulator, writer, commandFactrory);
        engine.run();

        // Read created files
        javaFile_1_content = readFromFile(javaFile1);
        javaFile_2_content = readFromFile(javaFile2);
        csFile_1_content = readFromFile(csFile1);
        csFile_2_content = readFromFile(csFile2);
        cppFile_1_content = readFromFile(cppFile1);
        cppFile_2_1_content = readFromFile(cppFile21);
        cppFile_2_2_content = readFromFile(cppFile22);
    }

    private String readFromFile(File file) throws FileNotFoundException, IOException {
        StringBuilder sourceBuilder = new StringBuilder();
        String line = InserterConstants.EMPTY_STRING;
        String source = null;

        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            while ((line = reader.readLine()) != null) {
                sourceBuilder.append(line);
                sourceBuilder.append(InserterConstants.LINE_SEPARATOR);
            }
            source = sourceBuilder.toString().trim();
        }

        return source;
    }

    private static void deleteFilesRecursively(File rootFolder) {
        File[] files = rootFolder.listFiles();
        for (File file : files) {
            if(file.isDirectory()) {
                deleteFilesRecursively(file);
            }
            file.delete();
        }
        rootFolder.delete();
    }
}
