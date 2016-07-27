package com.copyrightinserter.main;

import java.io.Console;

public class Engine implements Runnable{

	@Override
	public void run() {
		boolean isRunning = true;
		Console  cnsl = System.console();
		while(isRunning){
			String command = cnsl.readLine();
			
		}
	}
	
}
