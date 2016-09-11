package com.coprtools.writer;
public class ConsoleWriter implements Writer{

    @Override
    public void writeLine(String line) {
        System.out.println(line);
    }

    @Override
    public void writeLine(String format, Object... args) {
        System.out.println(String.format(format, args));
    }
}
