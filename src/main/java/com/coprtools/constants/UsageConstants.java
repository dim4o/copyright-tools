/*
 * Copyright (C) 2016 Dimcho Nedev
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.coprtools.constants;

/**
 * Contains constants that describe the program's usage
 */
public class UsageConstants {
	public static final String BOTTOM_OPTION_DESCR = "Insert on bottom";

	public static final String EXTENSIONS_OPTION_DESC = "File extensions selector. "
			+ "Expects list of space separated extensions(start with '.'). Example: .java .txt .js";

	public static final String HELP_OPTION_DESC = "Display help";

	public static final String ROOT_OPTION_DESC = "Path to input folder";

	public static final String INFO_DESC = "Enables job info console logging";

	public static final String NOTICE_OPTION_DESC = "Path to the license notice text file";

	public static final String TOP_OPTION_DESC = "Insert on top";

	public static final String BLANK_OPTION_DESC = "Add user defined number of blank lines after notice";

	public static final String REMOVE_DESC = "Removes copyright notice";

	public static final String REPLACE_DESC = "Replace old copyright notice with new one.";

	public static final String OUTPUT_OPTION_DESC = "Writes the result to specific output folder.";

	public static final String INFO_OPTION_DESC = "Enables job info console logging";

	public static final String NEW_NOTICE_OPTION_DESC = "Path to the new license notice text file";

	public static final String USAGE = "[-r] [<root folder path>] [-n] [<notice path>] [-e] [.<ext1> .<ext2>...]";

	public static final String HEADER = "Copyright inserter options";

	public static final String FOOTER = "Project home: http://github/dim4o/copyright-inserter";
}
