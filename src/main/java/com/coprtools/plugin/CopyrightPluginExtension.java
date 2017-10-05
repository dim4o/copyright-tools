package com.coprtools.plugin;

//TODO: Follow this convention: `path` for file, `dir` for folder location
public class CopyrightPluginExtension {
    private String message = "Default Greeting from Gradle";
    
    private String rootDir = ".";
    
    private boolean blankLine = true;
    
    private boolean info = false;
    
    private String noticePath = "./notice.txt";
    
    private String newNoticePath = "./new_notice.txt";
    
    private String outputDir;
    
    private String position = "top"; // top or bottom
    
    private String[] extensions;
    
    private String action;// insert, remove, replace

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getRootDir() {
        return rootDir;
    }

    public void setRootDir(String rootDir) {
        this.rootDir = rootDir;
    }

    public String getNoticePath() {
        return noticePath;
    }

    public void setNoticePath(String noticePath) {
        this.noticePath = noticePath;
    }

    public String[] getExtensions() {
        return extensions;
    }

    public void setExtensions(String[] extensions) {
        this.extensions = extensions;
    }

    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = action;
    }

    public boolean isBlankLine() {
        return blankLine;
    }

    public void setBlankLine(boolean blankLine) {
        this.blankLine = blankLine;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public String getOutputDir() {
        return outputDir;
    }

    public void setOutputDir(String outputDir) {
        this.outputDir = outputDir;
    }

    public String getNewNoticePath() {
        return newNoticePath;
    }

    public void setNewNoticePath(String newNoticePath) {
        this.newNoticePath = newNoticePath;
    }

    public boolean getInfo() {
        return info;
    }

    public void setInfo(boolean info) {
        this.info = info;
    }
}
