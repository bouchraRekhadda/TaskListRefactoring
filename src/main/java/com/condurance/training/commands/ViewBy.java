package com.condurance.training.commands;

import java.io.PrintWriter;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;
import com.codurance.training.tasks.Task;
import static com.codurance.training.tasks.ValidateDateFormat.isValidateDateFormat;

public class ViewBy extends CommandAbstract {

	private PrintWriter out;

	public ViewBy(PrintWriter writer) {
		this.out = writer;
	}

	@Override
	public void execute(String commandLine) {
		String[] subcommandRest = commandLine.split(" ", 3);
		String ByWhat = subcommandRest[1];
		switch (ByWhat) {
		case "date":
			getTasksByDate(subcommandRest[2]);
			break;
		case "deadline":
			getTasksByDeadLine(subcommandRest[2]);
			break;
		case "project":
			getTasksByProject(subcommandRest[2]);
			break;
		}

	}

	private void getTasksByDeadLine(String deadline) {
		if (checkDateFormat(deadline)) {
			for (Map.Entry<String, List<Task>> project : tasks.entrySet()) {
				out.println(project.getKey());
				for (Task task : project.getValue()) {
					if (task.getDeadLine().equals(deadline)) {
				out.printf(" [%c] %d: %s %s%n",(task.isDone() ? 'x' : ' ')
						,task.getId(),task.getDescription(), task.getDeadLine());
					}
				}
				out.println();
			} 
		}
		else{
			out.printf("We are sorry, no task matched this date %s%n", deadline);
			out.println();}
		
	}

	private void getTasksByDate(String date) {
		if (checkDateFormat(date)) {
			for (Map.Entry<String, List<Task>> project : tasks.entrySet()) {
				out.println(project.getKey());
				for (Task task : project.getValue()) {
					if (task.getDate().equals(date)) {

						out.printf(" [%c] %d: %s %s%n", (task.isDone() ? 'x' : ' '), task.getId(),
								task.getDescription(), task.getDate());
					}
				}
			}
			out.println();
		} else
			out.printf("We are sorry, no task matched this date %s%n", date);
		out.println();

	}

	private void getTasksByProject(String projectName) {
		Boolean exist=false;
		for (Map.Entry<String, List<Task>> project : tasks.entrySet()) {
			if (project.getKey().equals(projectName)) {
				exist=true;
				for (Task task : project.getValue()) {
					out.printf(" [%c] %d: %s%n", (task.isDone() ? 'x' : ' '), task.getId(), task.getDescription());
				}
				return;
			} 
		}
		if(exist==false)out.printf("We are sorry, no project matched this project Name %s%n", projectName);
		out.println();
	}
	
	public void TodayTasks() {
		Boolean exist=false;
		DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd");
        Date d = new Date();
        String date=dateFormat.format(d);
		for (Map.Entry<String, List<Task>> project : tasks.entrySet()) {
			for (Task task : project.getValue()) {
				if (task.getDeadLine()!=null && task.getDeadLine().equals(date)) {
					exist=true;
					out.printf("    [%c] %d: %s%n", 
		(task.isDone() ?'x' : ' '), task.getId(), task.getDescription());
				}
			}
		}
		if(exist==false)out.printf("we are sorry, no task had the deadline as today");
		out.println();
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
