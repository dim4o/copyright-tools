# copyright-tools
This is a simple tool for inserting, removing and replacing a copyright notice to any kinds of source code files.

## Requirements
The only requirement is to have JDK installed.

## Build project
To build `copyright-inserter` project you may run terminal/console and navigate to the progect's root folder. Now you can execute:
* `./gradlew build` (Linux/macOS)
* `gradlew build` (Windows)

## Usage

### Arguments
* `-h` or `--help` - help
* `-r` or `--root` - path to the root directory. If the path contains `\` character you must to replace with `\\`.
* `-n` or `--notice` - path to input folder. If the path contains `\` character you must to replace with `\\`.
* `-e` or `--extensions` - list of the file extensions on which you want to execute the command. Example: `.java .gradle`.
* `-bl` or `--blank` - with this option a blank line will be inserted after the notice. If you use with a `--bootom` option a blank line will be inserted before the notice.
* `-i` or `--info` - enable log info. Log file will be created in the root directory.
* `-nn` or `--new-notice` - if you want to replace an old notice with a new notice this will be the path to the new notice.
* `-b` or `--bottom` - use this option if you want to insert notice after the source.

This is the standard **argument's** body:
* `-r "<pathToInputFolder>" -n "<pathToNoticeTextFile>" -e <extensionsList>`
* `--root "<pathToInputFolder>" --notice "<pathToNoticeTextFile>" --extensions <extensionsList>`

### Run comand line with arguments
There are two ways:
* From `build/lib` folder run `java -jar copyright-inserter-<version>.jar <arguments>`(Linux/macOS/Windows)
* From `build/scripts` run:
  * `./copyright-inserter <arguments>` (Linux/macOS)
  * `copyright-inserter.bat <arguments>` (Windows)

### Examples
Suppose you want to insert notice with a blank line(after) before all `java` and `gradle` files from the project, you can run this command:
`-r <pathToProjectRootDir> -n <pathToTheNotice> -e .java .gradle -bl`
