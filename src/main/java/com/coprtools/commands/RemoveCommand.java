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

package com.coprtools.commands;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

import com.coprtools.util.FileManipulator;

/**
 * Removes copyright notice from source file
 */
public class RemoveCommand extends AbstractCommand {

    public RemoveCommand(
            String notice,
            String[] extensions,
            FileManipulator manipulator){
        super(notice, extensions, manipulator);
    }

    @Override
    protected void executeOnce(File targetFile) throws FileNotFoundException, IOException {
        String source = this.manipulator.readFromFile(targetFile);
        String newSource = source.replaceFirst(this.notice, "").trim();
        this.manipulator.overrideFile(targetFile, newSource);
    }
}
