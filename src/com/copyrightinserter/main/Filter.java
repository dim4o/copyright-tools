package com.copyrightinserter.main;

import java.io.File;
import java.util.List;

public class Filter {
	List<String> fileExtensions;
	
	Filter(List<String> fileExtensions){
		this.fileExtensions = fileExtensions;
	}
	
	boolean containsExtension(File file, boolean positive){
		
		for (String extension : fileExtensions) {
			if(file.getName().endsWith(extension)){
				return true;
			}
		}
		
		return false;
	}
	
}
