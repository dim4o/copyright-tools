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
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

import com.coprtools.core.CopyrightToolsEngine;
import com.coprtools.exceptions.AlreadyInsertedException;
import com.coprtools.util.FileManipulator;

/**
 * An abstract class that represents type for the base command
 *
 * @author Dimcho Nedev
 */
public abstract class AbstractCommand {

    private static final Logger LOGGER = Logger.getLogger(CopyrightToolsEngine.class.getName());

    protected String notice;

    private String[] extensions;

    private boolean hasError;

    protected FileManipulator manipulator;

    /**
     * This is the base command functionality that will be overridden from each
     * one command. Represents an atomic file manipulation: insert, remove or
     * replace a notice.
     *
     * @param targetFile
     *            - the file that will be manipulated
     * @throws Exception
     *             - thrown when the manipulation failed
     */
    protected abstract void executeOnce(File targetFile) throws Exception;

    AbstractCommand(String notice, String[] extensions, FileManipulator manipulator) {
        this.notice = notice;
        this.extensions = extensions;
        this.manipulator = manipulator;
    }

    /**
     * Executes the current command recursively for all files in the specified
     * target location directory
     *
     * @param targetLocation
     *            - the target directory where the command will be executed
     */
    public void executeRecursively(File targetLocation) {
        File[] files = targetLocation.listFiles();

        for (File file : files) {
            if (!file.isDirectory()) {
                boolean isExt = containsExtension(file, this.extensions);
                try {
                    if (isExt) {
                        executeOnce(file);
                        LOGGER.log(Level.INFO, String.format("%s - DONE", file.getName()));
                    }
                } catch (IOException e) {
                    this.hasError = true;
                    LOGGER.log(Level.SEVERE, String.format("%s - ERROR - %s", file.getName(), e.getMessage()));
                } catch (AlreadyInsertedException e) {
                    this.hasError = true;
                    LOGGER.log(Level.INFO, String.format("%s - ALREADY INSERTED (nothong to do here) - %s",
                            file.getName(), e.getMessage()));
                } catch (Exception e) {
                    e.printStackTrace();
                }
            } else {
                executeRecursively(file);
            }
        }
    }

    /**
     * Checks whether an array of file extensions contains a specific file
     * extension
     *
     * @param file
     *            - the file which extension will be checked
     * @param fileExtensions
     *            - array of file extension
     * @return
     */
    private boolean containsExtension(File file, String[] fileExtensions) {
        for (String extension : fileExtensions) {
            if (file.getName().endsWith(extension)) {
                return true;
            }
        }

        return false;
    }

    public boolean isHasError() {
        return this.hasError;
    }
}
