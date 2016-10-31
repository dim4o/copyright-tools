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

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

import org.junit.Assert;
import org.junit.Test;

public class ReplaceCommandTest extends BaseCommandTest {

    private static final String NEW_NOTICE = "//This is a new notice";

    private static final String SHOOLD_START_WITH_NEW_NOTICE =
            "The file should start with the new notice";

    private static final String SHOOLD_NOT_START_WITH_NEW_NOTICE =
            "The file should not start with the new notice";

    @Test
    public void testBaseReplaceFunctionallity()
            throws FileNotFoundException, IOException {
        File newNotice = new File("./temp/new-notice.txt");
        newNotice.getParentFile().mkdirs();
        newNotice.createNewFile();
        this.writeToFile(newNotice, NEW_NOTICE);
        String insertCommand = "insert -r ./temp/rootDir -n ./temp/notice.txt -e .cpp .java";
        this.executeCommand(insertCommand);
        String replaceCommand =
                "replace -r ./temp/rootDir -n ./temp/notice.txt -nn ./temp/new-notice.txt -e .java";
        this.executeCommand(replaceCommand);

        Assert.assertTrue(SHOOLD_START_WITH_NEW_NOTICE, javaFile_1_content.startsWith(NEW_NOTICE));
        Assert.assertTrue(SHOOLD_START_WITH_NEW_NOTICE, javaFile_2_content.startsWith(NEW_NOTICE+System.getProperty("line.separator")));

        Assert.assertFalse(SHOOLD_NOT_START_WITH_NEW_NOTICE, csFile_1_content.startsWith(NEW_NOTICE));
        Assert.assertFalse(SHOOLD_NOT_START_WITH_NEW_NOTICE, csFile_2_content.startsWith(NEW_NOTICE));
        Assert.assertFalse(SHOOLD_NOT_START_WITH_NEW_NOTICE, cppFile_1_content.startsWith(NEW_NOTICE));
        Assert.assertFalse(SHOOLD_NOT_START_WITH_NEW_NOTICE, cppFile_2_1_content.startsWith(NEW_NOTICE));
        Assert.assertFalse(SHOOLD_NOT_START_WITH_NEW_NOTICE, cppFile_2_2_content.startsWith(NEW_NOTICE));
    }
}