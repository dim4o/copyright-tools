package com.copyrightinserter.tests;

import java.io.FileNotFoundException;
import java.io.IOException;

import org.junit.Assert;
import org.junit.Test;

public class InsertBeforeCommandTest extends BaseCommandTest {

    private static final String NOT_INSERTED = "The notice is not inserted!";

    private static final String SHOULD_NOT_START_WITH_NOTICE = "The file shouldn't start with notice";

    @Test
    public void baseInsertNoticeWithOneExtensionAndShortOptionsTest() throws FileNotFoundException, IOException {
        String insertCommand = "insert -r ./temp/rootDir -n ./temp/notice.txt -e .java";
        this.executeCommand(insertCommand);
        String noticeWithBlankLineAfter = NOTICE + "\n";

        // There is copyright notice before the content
        Assert.assertTrue(NOT_INSERTED, javaFile_1_content.startsWith(NOTICE));
        Assert.assertTrue(NOT_INSERTED, javaFile_2_content.startsWith(NOTICE));

        // No copyright notice before the content
        Assert.assertFalse(SHOULD_NOT_START_WITH_NOTICE, csFile_1_content.startsWith(NOTICE));
        Assert.assertFalse(SHOULD_NOT_START_WITH_NOTICE, csFile_2_content.startsWith(NOTICE));
        Assert.assertFalse(SHOULD_NOT_START_WITH_NOTICE, cppFile_1_content.startsWith(NOTICE));
        Assert.assertFalse(SHOULD_NOT_START_WITH_NOTICE, cppFile_2_1_content.startsWith(NOTICE));
        Assert.assertFalse(SHOULD_NOT_START_WITH_NOTICE, cppFile_2_2_content.startsWith(NOTICE));

        // No blank line between the notice and the source
        Assert.assertFalse(NOT_INSERTED, javaFile_1_content.startsWith(noticeWithBlankLineAfter));
        Assert.assertFalse(NOT_INSERTED, javaFile_2_content.startsWith(noticeWithBlankLineAfter));
    }

    @Test
    public void insertNoticeWithOneBlankLineTest() throws FileNotFoundException, IOException{
        String command = "insert -r ./temp/rootDir -n ./temp/notice.txt -e .cs -bl 1";
        this.executeCommand(command);
        String noticeWithBlankLineAfter = NOTICE + System.getProperty("line.separator");

        // There is copyright notice before the content
        Assert.assertTrue(NOT_INSERTED, csFile_1_content.startsWith(noticeWithBlankLineAfter));
        Assert.assertTrue(NOT_INSERTED, csFile_2_content.startsWith(noticeWithBlankLineAfter));
    }

    @Test
    public void insertNoticeForTwoFileExtensionsTest() throws FileNotFoundException, IOException{
        String command = "insert -r ./temp/rootDir -n ./temp/notice.txt -e .cs .java";
        this.executeCommand(command);

        // There is copyright notice before the content
        Assert.assertTrue(NOT_INSERTED, csFile_1_content.startsWith(NOTICE));
        Assert.assertTrue(NOT_INSERTED, csFile_2_content.startsWith(NOTICE));
        Assert.assertTrue(NOT_INSERTED, javaFile_1_content.startsWith(NOTICE));
        Assert.assertTrue(NOT_INSERTED, javaFile_2_content.startsWith(NOTICE));

        // No blank line between the notice and the source
        Assert.assertFalse(NOT_INSERTED, cppFile_1_content.startsWith(NOTICE));
        Assert.assertFalse(NOT_INSERTED, cppFile_2_1_content.startsWith(NOTICE));
        Assert.assertFalse(NOT_INSERTED, cppFile_2_2_content.startsWith(NOTICE));
    }
}