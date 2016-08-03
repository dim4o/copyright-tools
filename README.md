# copyright-inserter
This is a sample toll for insertinc copyright notice to any kind of source code files.

## Requirements
The only requirement is to have JDK installed and set JAVA_HOME enviroment variable

## Build project
To build `copyright-inserter` project you may run terminal/console and navigate to the progects's root folder. Now you can execute:
* `./gradlew build` (Linux/macOS)
* `gradlew build` (Windows)

## Usage
### Arguments
* `-h` - help
* `-i` - path to input folder. If the path contains `\` character you must to replace with `\\`. Normally use `/` character.
* `-n` - path to input folder. If the path contains `\` character you must to replace with `\\`. Normally use `/` character.
* `-e` - file extension list. List of the file extension on witch you want isert to. Example: `.java .gradle`.

This is the standard **argument's** body: `-i "<pathToInputFolder>" -n "<pathToNoticeTextFile>" -e <extensionsList>`
### Run comand line with arguments
There are two ways:
* From `build/lib` folder run `java -jar copyright-inserter-<version>.jar <arguments>`(Linux/macOS/Windows)
* From `build/scripts` run:
  * `./copyright-inserter <arguments>` (Linux/macOS)
  * `copyright-inserter.bat <arguments>` (Windows)