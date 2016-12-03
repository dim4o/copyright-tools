[![Build Status](https://travis-ci.org/dim4o/copyright-tools.svg?branch=master)](https://travis-ci.org/dim4o/copyright-tools)
# copyright-tools
This is a simple collection of tools for **inserting**, **removing** and **replacing** a copyright notice to any kinds of source code files.

## Requirements
The only requirement is to have JDK installed( >1.7).

## Build project
To build `copyright-tools` project you may run terminal/console and navigate to the progect's root folder. Now you can execute:
* `./gradlew build` (Linux/macOS)
* `gradlew build` (Windows)

## Usage

### Commands
The commands require `root`, `notice` and `extensions` arguments.

* **insert** - inserts a notice before/after the file content. Requires all mandatory arguments. Example usage:  
`insert --root "<pathToInputFolder>" --notice "<pathToTheNotice>" --extensions <ListOfExtensions>`

* **remove** - removes the first occurrence of the notice. Requires all mandatory arguments. This is an example usage with short options:  
`remove -r "<pathToInputFolder>" -n "<pathToTheNotice>" -e <ListOfExtensions>`

* **replace** - replace an old notice with a new one. Requires the mandatory argument and a `new-notice` argument. Example:  
`replace -r "<pathToInputFolder>" -n "<pathToTheNotice>" --new-notice "<pathToTheNewNotice>" -e <ListOfExtensions>`



### Options and arguments
* `-h` or `--help` - prints help, no mandatory option with no arguments.
* `-r` or `--root` - option for a path to the root directory. Requires a path to the source folder as argument. If the path contains `\` character you must to replace with `\\`. This is a mandatory option.
* `-n` or `--notice` - the option requires a path to the notice as argument. If the path contains `\` character you must to replace with `\\`. Mandatory option.
* `-e` or `--extensions` - list of the file extensions on which you want to execute the command. Example: `.java .gradle`.
* `-bl` or `--blank` - with this option a blank line will be inserted after the notice. If you use with a `--bootom` option a blank line will be inserted before the notice.
* `-i` or `--info` - enable log info. Log file will be created in the root directory.
* `-nn` or `--new-notice` - if you want to replace an old notice with a new notice this will be the path to the new notice.
* `-b` or `--bottom` - use this option if you want to insert notice after the source. This is an optional argument.
* `-o` or `--output` - with this option you can specify an output directory. The result will be stored in the output folder and root's folder will stay unchanged. Requires a path to an output folder as argument. It's a no mandatory option.

This is a standard argument's body:
* `-r "<pathToInputFolder>" -n "<pathToNoticeTextFile>" -e <extensionsList>`
* `--root "<pathToInputFolder>" --notice "<pathToNoticeTextFile>" --extensions <extensionsList>`

### Run comand line with arguments
There are two ways:
* From `build/lib` folder run `java -jar copyright-inserter-<version>.jar <arguments>`(Linux/macOS/Windows)
* From `build/scripts` run:
  * `./copyright-tools <arguments>` (Linux/macOS)
  * `copyright-tools.bat <arguments>` (Windows)

### Use case examples
* Suppose you want to insert a notice with a blank line (*after the notice*) before all `java` and `gradle` files from the project, you can run this command:  
`insert -r "C:\\targetDir" -n "C:\\Notice.txt" -e .java .gradle -bl`.

* If you want to insert the notice at the bottom of the code: just add `--bottom` to the above command (or `-b` option). In this case the blank line will be inserted *before* the notice.

* Suppose you want to remove a notice from existing code, you may run:  
`remove --root "C:/targetDir" --notice "C:/NoticeToRemove.txt" -e .java`.

* To replace an old copyright notice with a new one:  
`repalce -r "C:/targetDir" -n "C:/old-notice.txt" -nn "C:/new-notice.txt" -e .java`

* If you want to keep your original files untouched - simply run the above commands with `--output` (or `-o`) option and a path to desired output destination as an argument:  
`insert -r "C:\\targetDir" -n "C:\\Notice.txt" -e .java .gradle -bl -o "C:\\output" -e .java`.
