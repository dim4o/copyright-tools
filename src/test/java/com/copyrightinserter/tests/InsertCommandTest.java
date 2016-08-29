package com.copyrightinserter.tests;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import com.copyrightinserter.cli.AbstractConsole;
import com.copyrightinserter.cli.ApacheCliConsole;
import com.copyrightinserter.commands.CommandFactory;
import com.copyrightinserter.core.CopyrightToolsEngine;
import com.copyrightinserter.util.FileManipulator;
import com.copyrightinserter.util.SourceManipulator;
import com.copyrightinserter.writer.ConsoleWriter;

public class InsertCommandTest {

    private static final String NOTICE =
            "// This is a sample notice 1"
            + System.getProperty("line.separator")
            + "// This is a sample notice 2";

    private static final String SAMPLE_CONTENT =
            "This is sample content line 1."
            + System.getProperty("line.separator")
            + "This is sample content line 2";

    @BeforeClass
    public static void setUpBeforeClass() throws Exception {
        initializeFiles(SAMPLE_CONTENT, NOTICE);
    }

    @AfterClass
    public static void tearDownAfterClass() throws Exception {
        // TODO: Delete all test files
    }

    @Before
    public void setUp() throws Exception {
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void baseInsertNoticeWithOneExtensionAndShortOptionsTest() {
        String command = "insert -r ./temp/rootDir -n ./temp/notice.txt -e .java";
        String[] args = command.split(" ");
        AbstractConsole cli = new ApacheCliConsole(args);
        FileManipulator manipulator = new SourceManipulator();
        com.copyrightinserter.writer.Writer writer = new ConsoleWriter();
        CommandFactory commandFactrory = new CommandFactory();
        CopyrightToolsEngine  engine = new CopyrightToolsEngine(cli, manipulator, writer, commandFactrory);
        engine.run();
    }

    @Test
    public void insertNoticeWithOneBlankLineTest(){
        String command = "insert -r ./tempRoot -n ./notice.txt -e .java --info -bl 1";
    }

    private static void initializeFiles(String content, String notice) throws IOException {
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
        File javaFile1 = new File("./temp/rootDir/firstChild/javaFile_1.java");
        javaFile1.getParentFile().mkdirs();
        javaFile1.createNewFile();
        writer = new BufferedWriter(new FileWriter(javaFile1, true));
        writer.write(content);

        writer.close();

        File csFile1 = new File("./temp/rootDir/firstChild/csFile_1.cs");
        csFile1.getParentFile().mkdirs();
        csFile1.createNewFile();
        writer = new BufferedWriter(new FileWriter(csFile1, true));
        writer.write(content);

        writer.close();

        File cppFile1 = new File("./temp/rootDir/firstChild/cppFile_1.cpp");
        cppFile1.getParentFile().mkdirs();
        cppFile1.createNewFile();
        writer = new BufferedWriter(new FileWriter(cppFile1, true));
        writer.write(content);

        writer.close();

        File javaFile2 = new File("./temp/rootDir/secondChild/javaFile_2.java");
        javaFile2.getParentFile().mkdirs();
        javaFile2.createNewFile();
        writer = new BufferedWriter(new FileWriter(javaFile2, true));
        writer.write(content);

        writer.close();

        File csFile2 = new File("./temp/rootDir/secondChild/csFile_2.cs");
        csFile2.getParentFile().mkdirs();
        csFile2.createNewFile();
        writer = new BufferedWriter(new FileWriter(csFile2, true));
        writer.write(content);

        writer.close();

        File cppFile2 = new File("./temp/rootDir/secondChild/cppFile_21.cpp");
        cppFile2.getParentFile().mkdirs();
        cppFile2.createNewFile();
        writer = new BufferedWriter(new FileWriter(cppFile2, true));
        writer.write(content);

        writer.close();

        File cppFile22 = new File("./temp/rootDir/secondChild/cppFile_22.cpp");
        cppFile22.getParentFile().mkdirs();
        cppFile22.createNewFile();
        writer = new BufferedWriter(new FileWriter(cppFile22, true));
        writer.write(content);

        writer.close();

        File noticeFile = new File("./temp/notice.txt");
        noticeFile.getParentFile().mkdirs();
        noticeFile.createNewFile();
        writer = new BufferedWriter(new FileWriter(noticeFile, true));
        writer.write(notice);

        writer.close();
    }

}