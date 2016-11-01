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

public class RemoveCommandTest extends BaseCommandTest {

    private static final String SHOULD_START_WITH_NOTICE = "The file should start with notice";

    @Test
    public void testBaseRemoveNotice_withShortOptions_shouldReturnCorrectResult()
            throws FileNotFoundException, IOException {
        String insertCommand = "insert -r ./temp/rootDir -n ./temp/notice.txt -e .cpp .java .cs";
        this.executeCommand(insertCommand);

        String removeCommand = "remove -r ./temp/rootDir -n ./temp/notice.txt -e .cpp";
        this.executeCommand(removeCommand);

        // No copyright notice before the content
        Assert.assertFalse(SHOULD_NOT_START_WITH_NOTICE, cppFile_1_content.startsWith(NOTICE));
        Assert.assertFalse(SHOULD_NOT_START_WITH_NOTICE, cppFile_2_1_content.startsWith(NOTICE));
        Assert.assertFalse(SHOULD_NOT_START_WITH_NOTICE, cppFile_2_2_content.startsWith(NOTICE));

        // There is copyright notice before the content
        Assert.assertTrue(SHOULD_START_WITH_NOTICE, csFile_1_content.startsWith(NOTICE));
        Assert.assertTrue(SHOULD_START_WITH_NOTICE, csFile_2_content.startsWith(NOTICE));
        Assert.assertTrue(SHOULD_START_WITH_NOTICE, javaFile_2_content.startsWith(NOTICE));
        Assert.assertTrue(SHOULD_START_WITH_NOTICE, javaFile_2_content.startsWith(NOTICE));
    }

    @Test
    public void testRemoveNotice_withLongOptionsAndBlankLinesAfter_shouldRemoveLeadingSpacesAlso()
            throws FileNotFoundException, IOException {
        String insertCommand = "insert -r ./temp/rootDir -n ./temp/notice.txt -e .cpp .java .cs -bl 2";
        this.executeCommand(insertCommand);

        String removeCommand = "remove -root ./temp/rootDir --notice ./temp/notice.txt --extensions .java";
        this.executeCommand(removeCommand);

        Assert.assertFalse(SHOULD_NOT_START_WITH_NOTICE, javaFile_2_content.startsWith(NOTICE));
        Assert.assertFalse(SHOULD_NOT_START_WITH_NOTICE, javaFile_2_content.startsWith(NOTICE));

        Assert.assertFalse(SHOULD_NOT_START_WITH_NOTICE, javaFile_2_content.startsWith("\n\n"));
        Assert.assertFalse(SHOULD_NOT_START_WITH_NOTICE, javaFile_2_content.startsWith("\n\n"));
    }
}