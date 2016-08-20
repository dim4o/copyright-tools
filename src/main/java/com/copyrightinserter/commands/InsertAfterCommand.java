package com.copyrightinserter.commands;

import java.io.File;
import java.io.IOException;

import com.copyrightinserter.constants.InserterConstants;
import com.copyrightinserter.util.FileManipulator;

public class InsertAfterCommand extends AbstractCommand {

    public InsertAfterCommand(
            String notice,
            String[] extensions,
            FileManipulator manipulator){
        super(notice, extensions, manipulator);
    }

    @Override
    protected void executeOnce(File targetFile) throws IOException  {
        this.manipulator.writeToFile(targetFile, InserterConstants.LINE_SEPARATOR + this.notice);
    }
}