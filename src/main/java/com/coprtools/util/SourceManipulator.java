package com.coprtools.util;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;

import com.coprtools.constants.InserterConstants;

/**
 * <p>
 * Util class that manipulate with any kinds of source files.
 * </p>
 *
 * @author dimcho.nedev
 *
 */
public class SourceManipulator implements FileManipulator {
    /**
     * Reads the source file.
     *
     * @param file
     *        - the given source file
     * @return source file content
     */
    @Override
    public String readFromFile(File file) throws FileNotFoundException, IOException {
        StringBuilder sourceBuilder = new StringBuilder();
        String line = InserterConstants.EMPTY_STRING;
        String source = null;

        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            while ((line = reader.readLine()) != null) {
                sourceBuilder.append(line);
                sourceBuilder.append(InserterConstants.LINE_SEPARATOR);
            }

            source = sourceBuilder.toString().trim();
        }

        return source;
    }

    // TODO: for removal
    @Override
    public String readFromFile(String filePath) throws FileNotFoundException, IOException{
        File file = new File(filePath);
        String content = this.readFromFile(file);
        return content;
    }

    /**
     * Writes copyright notice on end of the given file.
     *
     * @param file
     *        - the given source file
     * @param notice
     *        - the copyright notice
     * @throws IOException
     */
    @Override
    public void writeToFile(File file, String notice) throws IOException {

        try (Writer writer = new BufferedWriter(new FileWriter(file, true))) {
            writer.write(notice);
        }
    }

    /**
     * Overrides the original source file content with new source (with copyright notice).
     *
     * @param file
     *        - the original source file with the old source
     * @param newSource
     *        - the new source with copyright notice
     * @throws IOException
     */
    @Override
    public void overrideFile(File file, String newSource) throws IOException {
        file.delete();
        file = new File(file.getAbsolutePath());
        file.getParentFile().mkdirs();
        file.createNewFile();

        // TODO: consider whether this trim() is necessary
        this.writeToFile(file, newSource.trim());
    }
}