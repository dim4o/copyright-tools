package com.copyrightinserter.commands;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

import com.copyrightinserter.util.FileManipulator;

public class RemoveCommand extends AbstractCommand {

    public RemoveCommand(
            String notice,
            String[] extensions,
            FileManipulator manipulator){
        super(notice, extensions, manipulator);
    }

    @Override
    protected void executeOnce(File targetFile) throws FileNotFoundException, IOException {
        String source = this.manipulator.readFromFile(targetFile);
        String newSource = source.replaceFirst(this.notice, "").trim();
        this.manipulator.overrideFile(targetFile, newSource);
    }
}