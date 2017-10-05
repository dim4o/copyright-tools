/*
 * Copyright (C) 2016 Dimcho Nedev
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.coprtools.tests;

import java.io.FileNotFoundException;
import java.io.IOException;

import org.junit.Assert;
import org.junit.Test;

public class InsertAfterCommandTest extends BaseCommandTest {

    private static final String SHOULD_NOT_END_WITH_NOTICE = "The file shouldn't end with notice";

    @Test
    public void testInserAfter_withOneExtensionAndShortOptions_shouldReturnCorretResult()
            throws FileNotFoundException, IOException {
        String insertAfterCommand = "insert -r ./temp/rootDir -n ./temp/notice.txt -e .java -b";
        this.executeCommand(insertAfterCommand);
        //String noticeWithBlankLineAfter = NOTICE + "\n";

        // There is copyright notice before the content
        Assert.assertTrue(NOT_INSERTED, javaFile_1_content.endsWith(NOTICE));
        Assert.assertTrue(NOT_INSERTED, javaFile_2_content.endsWith(NOTICE));

        // No copyright notice before the content
        Assert.assertFalse(SHOULD_NOT_END_WITH_NOTICE, csFile_1_content.endsWith(NOTICE));
        Assert.assertFalse(SHOULD_NOT_END_WITH_NOTICE, csFile_2_content.endsWith(NOTICE));
        Assert.assertFalse(SHOULD_NOT_END_WITH_NOTICE, cppFile_1_content.endsWith(NOTICE));
        Assert.assertFalse(SHOULD_NOT_END_WITH_NOTICE, cppFile_2_1_content.endsWith(NOTICE));
        Assert.assertFalse(SHOULD_NOT_END_WITH_NOTICE, cppFile_2_2_content.endsWith(NOTICE));
    }

    @Test
    public void testInsertAfter_withNoticeWithOneBlankLineTest() throws FileNotFoundException, IOException{
        String command = "insert -b -r ./temp/rootDir -n ./temp/notice.txt -e .cs -bl";
        this.executeCommand(command);
        String noticeWithBlankLineAfter = System.getProperty("line.separator") + NOTICE;

        // There is copyright notice before the content
        Assert.assertTrue(NOT_INSERTED, csFile_1_content.endsWith(noticeWithBlankLineAfter));
        Assert.assertTrue(NOT_INSERTED, csFile_2_content.endsWith(noticeWithBlankLineAfter));
    }

    @Test
    public void testInsertAfter_withTwoFileExtensions_shouldReturnCorretResult()
            throws FileNotFoundException, IOException{
        String insertAfterCommand = "insert -b -r ./temp/rootDir -n ./temp/notice.txt -e .cs .java";
        this.executeCommand(insertAfterCommand);

        // There is copyright notice before the content
        Assert.assertTrue(NOT_INSERTED, csFile_1_content.endsWith(NOTICE));
        Assert.assertTrue(NOT_INSERTED, csFile_2_content.endsWith(NOTICE));
        Assert.assertTrue(NOT_INSERTED, javaFile_1_content.endsWith(NOTICE));
        Assert.assertTrue(NOT_INSERTED, javaFile_2_content.endsWith(NOTICE));

        // No blank line between the notice and the source
        Assert.assertFalse(NOT_INSERTED, cppFile_1_content.endsWith(NOTICE));
        Assert.assertFalse(NOT_INSERTED, cppFile_2_1_content.endsWith(NOTICE));
        Assert.assertFalse(NOT_INSERTED, cppFile_2_2_content.endsWith(NOTICE));
    }
}