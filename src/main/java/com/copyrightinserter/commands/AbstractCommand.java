package com.copyrightinserter.commands;

import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

import com.copyrightinserter.core.Inserter;
import com.copyrightinserter.exceptions.AlreadyInsertedException;
import com.copyrightinserter.util.FileManipulator;
import com.copyrightinserter.util.SourceManipulator;

public abstract class AbstractCommand {
    private static final Logger LOGGER = Logger.getLogger(Inserter.class.getName());

    private File rootDir;

    protected String notice;

    private String[] extensions;

    private boolean hasError;

    protected FileManipulator manipulator;

    protected abstract void executeOnce(File targetFile) throws Exception;

    AbstractCommand(Object... args){
        this.rootDir = (File)args[0];
        this.notice = (String)args[1];
        this.extensions = (String[])args[2];
        this.manipulator = (SourceManipulator)args[3];
    }

    public void executeRecursivly(){
        File[] files = this.rootDir.listFiles();

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
                    // TODO: handle exception
                }
            } else {
                executeRecursivly();
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