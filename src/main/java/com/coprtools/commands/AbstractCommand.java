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
 */
public abstract class AbstractCommand {

    private static final Logger LOGGER = Logger.getLogger(CopyrightToolsEngine.class.getName());

    protected String notice;

    private String[] extensions;

    private boolean hasError;

    protected FileManipulator manipulator;

    protected abstract void executeOnce(File targetFile) throws Exception;

    AbstractCommand(
            String notice,
            String[] extensions,
            FileManipulator manipulator){
        this.notice = notice;
        this.extensions = extensions;
        this.manipulator = manipulator;
    }


    /**
     * Executes the current command recursively for all files
     * in the specified target location directory
     *
     * @param targetLocation
     *          - the target directory where the command will be executed
     */
    public void executeRecursively(File targetLocation){
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
     * Checks whether an array of file extensions contains a specific file extension
     *
     * @param file
     *          - the file which extension will be checked
     * @param fileExtensions
     *          - array of file extension
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