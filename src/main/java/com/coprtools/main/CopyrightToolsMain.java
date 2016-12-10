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

package com.coprtools.main;

import com.coprtools.cli.AbstractConsole;
import com.coprtools.cli.ApacheCliConsole;
import com.coprtools.commands.CommandFactory;
import com.coprtools.core.CopyrightToolsEngine;
import com.coprtools.util.FileManipulator;
import com.coprtools.util.SourceManipulator;
import com.coprtools.writer.ConsoleWriter;
import com.coprtools.writer.Writer;

/**
 * This is the main entry point for the copyright-tools application.
 *
 * @author Dimcho Nedev
 */
public class CopyrightToolsMain {
    public static void main(String[] args) {
        AbstractConsole cli = new ApacheCliConsole(args);
        FileManipulator manipulator = new SourceManipulator();
        Writer writer = new ConsoleWriter();
        CommandFactory commandFactrory = new CommandFactory();
        CopyrightToolsEngine engine = new CopyrightToolsEngine(cli, manipulator, writer, commandFactrory);

        engine.run();
    }
}
