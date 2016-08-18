package com.copyrightinserter.commands;

import java.io.File;
import java.io.IOException;

import com.copyrightinserter.constants.InserterConstants;
import com.copyrightinserter.exceptions.AlreadyInsertedException;

public class InsertBeforeCommand extends AbstractCommand {

    //private File file;

    public InsertBeforeCommand(Object... args){
        super(args);
    }

    @Override
    protected void executeOnce(File targetFile) throws IOException, AlreadyInsertedException {
        String source = this.manipulator.readFromFile(targetFile);
        if (source.startsWith(this.notice)) {
            throw new AlreadyInsertedException("The notice you have tried to insert is already inserted.");
        }
        String begin = this.notice + InserterConstants.LINE_SEPARATOR;
        String newSource = begin + source;

        this.manipulator.overrideFile(targetFile, newSource);
    }
}