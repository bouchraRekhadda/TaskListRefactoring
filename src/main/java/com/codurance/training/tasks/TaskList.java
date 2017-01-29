package com.codurance.training.tasks;

import java.io.BufferedReader;
import java.io.*;

import com.condurance.training.commands.*;

public final class TaskList implements Runnable {
	
	private static final String QUIT = "quit";
	private final BufferedReader in;
	private final PrintWriter out;
	Object CommandObject;

    public TaskList(BufferedReader reader, PrintWriter writer) {
        this.in = reader;
        this.out = writer;
    }

    public void run() {
        while (true) {
            out.print("> ");
            out.flush();
            String commandLine;
            try {
                commandLine = in.readLine();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            if (commandLine.equals(QUIT)) {
                break;
            }
            else{
            	String[] commandRest = commandLine.split(" ", 2);
                String command = commandRest[0];
            	 switch (command) {
                 case "show":
                	 CommandObject= new ShowTask(out);
                	 ((ShowTask) CommandObject).execute(" ");
                     break;
                 case "add":
                	 CommandObject= new AddTask(out);
                	 ((AddTask) CommandObject).execute(commandRest[1]);
                     break;
                 case "check":
                	 CommandObject = new CheckTask(out);
     				((CheckTask) CommandObject).execute(commandRest[1]);
                     break;
                 case "uncheck":
                	 CommandObject = new UncheckTask(out);
      				((UncheckTask) CommandObject).execute(commandRest[1]);
                     break;
                 case "delete":
                	 CommandObject = new DeleteTask(out);
      				((DeleteTask) CommandObject).execute(commandRest[1]);
                     break;
                 case "deadline":
                	 CommandObject = new DeadLineTask(out);
      				((DeadLineTask) CommandObject).execute(commandRest[1]);
                     break;
                 case "view":
                	 CommandObject = new ViewBy(out);
      				((ViewBy) CommandObject).execute(commandRest[1]);
                     break;
                 case "today":
                	 CommandObject = new ViewBy(out);
                	 ((ViewBy) CommandObject).TodayTasks();
                     break;
                 case "help":
                     help();
                     break;
                 default:
                     error(command);
                     break;
             }
            }
        }
    }

    private void help() {
        out.println("Commands:");
        out.println("  show");
        out.println("  add project <project name>");
        out.println("  add task <project name> <task description>");
        out.println("  check <task ID>");
        out.println("  uncheck <task ID>");
        out.println("  delete <task ID>");
        out.println("  deadline <task ID> <Date>");
        out.println("  today");
        out.println("  view by <project Name> / < Date> / <DeadLine>");
        out.println();
    }

    private void error(String command) {
        out.printf("I don't know what the command \"%s\" is.", command);
        out.println();
    }

    }
