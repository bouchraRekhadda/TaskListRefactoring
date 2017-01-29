package com.condurance.training.commands;

import java.io.PrintWriter;
import java.util.*;
import com.codurance.training.tasks.Task;

public class ShowTask extends CommandAbstract {
	
	private PrintWriter out;

	public ShowTask(PrintWriter writer) {
		this.out = writer;
	}
	
	@Override
	public void execute(String command) {
		for (Map.Entry<String, List<Task>> project : tasks.entrySet()) {
            out.println(project.getKey());
            for (Task task : project.getValue()) {
                out.printf("    [%c] %d: %s %s%n", (task.isDone() ? 'x' : ' '), task.getId(), task.getDescription(),task.getDate());
            }
            out.println();
        }

	}

}
