package com.copyrightinserter.commands;

import java.io.File;
import java.io.IOException;

import com.copyrightinserter.constants.InserterConstants;
import com.copyrightinserter.exceptions.AlreadyInsertedException;

public class InsertBeforeCommand extends AbstractCommand {

    public InsertBeforeCommand(Object... args){
     // TODO: initialize fields
    }

    @Override
    protected void execute() throws IOException, AlreadyInsertedException {
        String source = this.manipulator.readFromFile(this.file);
        if (source.startsWith(this.notice)) {
            throw new AlreadyInsertedException("The notice you have tried to insert is already inserted.");
        }
        String begin = this.notice + InserterConstants.LINE_SEPARATOR;
        String newSource = begin + source;

        this.file.delete();
        this.file = new File(file.getAbsolutePath());
        this.file.getParentFile().mkdirs();
        this.file.createNewFile();

        // TODO: consider whether this trim() is necessary
        this.manipulator.writeToFile(this.file, newSource.trim());
    }
}