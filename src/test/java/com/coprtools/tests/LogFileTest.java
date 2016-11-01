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