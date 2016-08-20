package com.copyrightinserter.commands;

import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

import com.copyrightinserter.core.Inserter;
import com.copyrightinserter.exceptions.AlreadyInsertedException;
import com.copyrightinserter.util.FileManipulator;

public abstract class AbstractCommand {
    private static final Logger LOGGER = Logger.getLogger(Inserter.class.getName());

    private File rootDir;

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

    public void executeRecursivly(File targetLocation){
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
                executeRecursivly(file);
            }
        }
    }

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