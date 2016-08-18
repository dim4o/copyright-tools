package com.copyrightinserter.commands;

import java.io.File;

import com.copyrightinserter.constants.InserterConstants;

public class InsertAfterCommand extends AbstractCommand {

    public InsertAfterCommand(Object... args){
        super(args);
    }

    @Override
    protected void executeOnce(File targetFile) throws Exception {
        this.manipulator.writeToFile(targetFile, InserterConstants.LINE_SEPARATOR + this.notice);
    }
}