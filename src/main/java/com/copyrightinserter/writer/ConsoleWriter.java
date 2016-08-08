package com.copyrightinserter.writer;
public class ConsoleWriter implements Writer{

    @Override
    public void writeLine(String line) {
        System.out.println(line);
    }

    public void writeLine(String format, Object... args) {
        System.out.println(String.format(format, args));
    }
}
