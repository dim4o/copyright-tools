package com.coprtools.commands;

import java.io.File;
import java.io.IOException;

import com.coprtools.util.FileManipulator;

/**
 * Inserts a copyright notice after the source code (file content)
 */
public class InsertAfterCommand extends AbstractCommand {

    public InsertAfterCommand(
            String notice,
            String[] extensions,
            FileManipulator manipulator){
        super(notice, extensions, manipulator);
    }

    @Override
    protected void executeOnce(File targetFile) throws IOException  {
        this.manipulator.writeToFile(targetFile, this.notice);
    }
}