package com.copyrightinserter.commands;

import com.copyrightinserter.constants.InserterConstants;

public class InsertAfterCommand extends AbstractCommand{

    public InsertAfterCommand(Object... args){
     // TODO: initialize fields
    }

    @Override
    protected void execute() throws Exception {
        this.manipulator.writeToFile(this.file, InserterConstants.LINE_SEPARATOR + this.notice);
    }
}