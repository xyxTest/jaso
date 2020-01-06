package com.yaj.xyx.util;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

public class ClassChild extends ClassParrent{
	private Set sb = new HashSet();
	public static void main(String args[]) throws IOException {
		BufferedReader br=new BufferedReader(new InputStreamReader(new FileInputStream(new File("C:/Users/Han/Desktop/iotest.txt")),"gbk"));
		String st="";
		while(st!=null){
			st = br.readLine();
			if(st!=null){
				System.out.println(st);
			}
		};
	}
}
