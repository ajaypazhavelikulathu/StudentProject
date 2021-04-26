package main.java.com.scg.student.service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class ConsoleReader {
	
	 BufferedReader in=new BufferedReader(new InputStreamReader(System.in));
	 

	
		

	public  String readString() throws IOException {
		
		String string=in.readLine();
		return string;
		
	}
	public int readInteger() throws NumberFormatException, IOException {
		int integer=Integer.parseInt(in.readLine());
		return integer;
	}
	

	}

		

	


