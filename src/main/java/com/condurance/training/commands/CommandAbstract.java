package com.condurance.training.commands;

import java.util.*;

import com.codurance.training.tasks.Task;

public abstract class CommandAbstract {
	static long lastId = 0;
	static Map<String, List<Task>> tasks = new LinkedHashMap<>();
	
	public abstract void execute(String command);
	
	static long nextId() {
		return ++lastId;
	}
}
