package com.copyrightinserter.commands;

import java.io.File;

public class ReplaceCommand extends AbstractCommand {
    private String newNotice;

    public ReplaceCommand(Object... args){
        super(args);
        // TODO: initialize newNotice
    }

    @Override
    protected void executeOnce(File targetFile) throws Exception {
        String source = this.manipulator.readFromFile(targetFile);
        String newSource = source.replaceFirst(this.notice, this.newNotice).trim();
        this.manipulator.overrideFile(targetFile, newSource);
    }
}