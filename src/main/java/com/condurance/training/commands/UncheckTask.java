package com.condurance.training.commands;

import java.io.PrintWriter;
import java.util.List;
import java.util.Map;

import com.codurance.training.tasks.Task;

public class UncheckTask extends CommandAbstract {

	private PrintWriter out;

	public UncheckTask(PrintWriter writer) {
		this.out = writer;
	}

	@Override
	public void execute(String idString) {
		setDone(idString, false);
		
	}
	private void setDone(String idString, boolean done) {
        int id = Integer.parseInt(idString);
        for (Map.Entry<String, List<Task>> project : tasks.entrySet()) {
            for (Task task : project.getValue()) {
                if (task.getId() == id) {
                    task.setDone(done);
                    return;
                }
            }
        }
        out.printf("Could not find a task with an ID of %d.", id);
        out.println();
    }

}
