package com.coprtools.plugin;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Arrays;

import org.gradle.api.DefaultTask;
import org.gradle.api.tasks.TaskAction;

import com.coprtools.exceptions.InvalidCommandException;
import com.coprtools.main.CopyrightToolsMain;

public class CoprtTask extends DefaultTask {
    @TaskAction
    public void greet() throws FileNotFoundException, IOException, InvalidCommandException {
        CoprtPluginExtension extension = getProject().getExtensions().findByType(CoprtPluginExtension.class);
        if (extension == null) {
            extension = new CoprtPluginExtension();
        }

        String rootDir = extension.getRootDir();
        String noticePath = extension.getNoticePath();
        String extensions = String.join(" ", extension.getExtensions());
        String action = extension.getAction();
        
        // TODO: add logic for blank line insertion or borrow a method from the Engine(in some way)
        String blankLine = extension.isBlankLine() ? "-bl" : "";
        
        String argsuments = action + " -r " + rootDir + " -n " + noticePath + " -e " + extensions + " " + blankLine + " --info";
        String[] args = argsuments.split(" ");
        
        System.out.println(Arrays.toString(args));
        
        System.out.println(argsuments);
        
        /*ArgumentManager am = new ArgumentManager();
        am.parse(args);*/
        
        CopyrightToolsMain.main(args);
        
        /*AbstractConsole cli = new ApacheCliConsole(args);
        // checks for "help" option before to parse argument to avoid MissingArgumentException
        // (because some other options are required)
        // TODO: This solution can be useful: https://stackoverflow.com/a/14357255/2595579
        if(Arrays.asList(args).contains("-h") || Arrays.asList(args).contains("--help")) {
            cli.showUsage();
        } else {
            FileManipulator manipulator = new SourceManipulator();
            Writer writer = new ConsoleWriter();
            CommandFactory commandFactrory = new CommandFactory();
            CopyrightToolsEngine engine = new CopyrightToolsEngine(cli, manipulator, writer, commandFactrory);

            engine.run();
        }*/

        /*FileManipulator manipulator = new SourceManipulator();
        CommandFactory factory = new CommandFactory();
        
        File noticeFile = new File(noticePath);
        String notice = manipulator.readFromFile(noticeFile);
        
        File rootFile = new File(rootDir);
        
        AbstractCommand command = factory.create(
                CommandType.valueOf(action.toUpperCase()),
                notice,
                extensions,
                manipulator,
                null);

        command.executeRecursively(rootFile);*/
//        HelloWorld helloWorld = new HelloWorld(message);
        /*System.out.println("Message: " + message);
        System.out.println("Root Dir: " + rootDir);
        System.out.println("Notice Path: " + noticePath);
        System.out.println("Extensions: " + extensions[0]);*/
        
    }
}
