package com.codurance.training.tasks;

import java.io.*;

class MainTaskRunner {
	   public static void main(String[] args) throws Exception {
		
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		PrintWriter out = new PrintWriter(System.out);
		TaskList taskList=new TaskList(in, out);
		taskList.run();
	   }
	}
