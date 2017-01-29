package com.condurance.training.commands;

import java.io.PrintWriter;
import java.util.*;
import com.codurance.training.tasks.Task;

public class DeleteTask extends CommandAbstract {

	private PrintWriter out;

	public DeleteTask(PrintWriter writer) {
		this.out = writer;
	}
	
	@Override
	public void execute(String idString) {
		int id = Integer.parseInt(idString);
		for (Map.Entry<String, List<Task>> project : tasks.entrySet()) {
			for (Task task : project.getValue()) {
				if (task.getId() == id) {
					project.getValue().remove(task);
					out.printf("task %d is removed succesfuly ", id);
					out.println();
					return;
				}
			}
		}
		out.printf("Could not find a task with an ID of %d.", id);
		out.println();
	}

}
