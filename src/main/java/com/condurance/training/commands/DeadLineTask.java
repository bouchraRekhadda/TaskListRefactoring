package com.condurance.training.commands;

import java.io.PrintWriter;
import java.util.*;
import com.codurance.training.tasks.Task;
import static com.codurance.training.tasks.ValidateDateFormat.isValidateDateFormat;

public class DeadLineTask extends CommandAbstract {

	private PrintWriter out;

	public DeadLineTask(PrintWriter writer) {
		this.out = writer;
	}

	@Override
	public void execute(String commandLine) {
		String[] subcommandRest = commandLine.split(" ", 2);
		String idString = subcommandRest[0];
		int id = Integer.parseInt(idString);
		String taskDeadLine = subcommandRest[1];
		if (checkDateFormat(taskDeadLine)) {
			for (Map.Entry<String, List<Task>> project : tasks.entrySet()) {
				for (Task task : project.getValue()) {
					if (task.getId() == id) {
						task.setDeadLine(taskDeadLine);
						out.printf("   Deadline well set for task %d: %s%n", task.getId(), task.getDeadLine());
					}
				}
			}
		}

	}

	private Boolean checkDateFormat(String taskDeadLine) {
		if (!taskDeadLine.matches("([0-9]{4})/([0-9]{2})/([0-9]{2})")) {
			out.printf(".........Veuillez respecter le format suivant yyyy/MM/dd..........");
			out.println();
			return false;
		} else if (!isValidateDateFormat(taskDeadLine)) {
			out.printf("........La date n'est pas valide.........%n");
			out.println();
			return false;
		}
		return true;
	}

}
