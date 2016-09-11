package com.coprtools.commands;

import java.io.File;
import java.io.IOException;

import com.coprtools.constants.InserterConstants;
import com.coprtools.exceptions.AlreadyInsertedException;
import com.coprtools.util.FileManipulator;

public class InsertBeforeCommand extends AbstractCommand {

    public InsertBeforeCommand(
            String notice,
            String[] extensions,
            FileManipulator manipulator){
        super(notice, extensions, manipulator);
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