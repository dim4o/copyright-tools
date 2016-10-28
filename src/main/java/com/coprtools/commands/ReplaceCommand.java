package com.coprtools.commands;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

import com.coprtools.util.FileManipulator;

/**
 * Replaces a copyright notice with another copyright notice
 */
public class ReplaceCommand extends AbstractCommand {
    private String newNotice;

    public ReplaceCommand(
            String notice,
            String[] extensions,
            FileManipulator manipulator,
            String newNotice){
        super(notice, extensions, manipulator);
            this.newNotice = newNotice;
    }

    @Override
    protected void executeOnce(File targetFile) throws FileNotFoundException, IOException {
        String source = this.manipulator.readFromFile(targetFile);
        String newSource = source.replaceFirst(this.notice, this.newNotice).trim();
        this.manipulator.overrideFile(targetFile, newSource);
    }
}