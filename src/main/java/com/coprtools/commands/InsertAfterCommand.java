package com.coprtools.commands;

import java.io.File;
import java.io.IOException;

import com.coprtools.constants.InserterConstants;
import com.coprtools.util.FileManipulator;

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