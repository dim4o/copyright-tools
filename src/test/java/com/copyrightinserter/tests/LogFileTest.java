package com.copyrightinserter.tests;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

import org.junit.Assert;
import org.junit.Test;

public class LogFileTest extends BaseCommandTest{

    @Test
    public void testLogFile_afterEnableInfo_shouldExists()
            throws FileNotFoundException, IOException{
        String command = "insert -r ./temp/rootDir -n ./temp/notice.txt -e .java --info";
        this.executeCommand(command);

        File logFile = new File("./temp/rootDir/CopyrightInserter.log");
        String fileContent = readFromFile(logFile);

        Assert.assertTrue(logFile.exists());
        Assert.assertTrue(fileContent != null);
    }

    @Test
    public void testLogFile_ifInfoIsNotEnabled_shouldNotExists()
            throws FileNotFoundException, IOException{
        String command = "insert -r ./temp/rootDir -n ./temp/notice.txt -e .java";
        this.executeCommand(command);

        File logFile = new File("./temp/rootDir/CopyrightInserter.log");

        Assert.assertFalse(logFile.exists());
    }
}
