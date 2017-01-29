package com.codurance.training.tasks;

import java.text.SimpleDateFormat;
import java.util.Date;

public final class Task {
    private final long id;
    private final String description;
    private boolean done;
    private String date;
    private String deadLine;

    public Task(long id, String description, boolean done) {
        this.id = id;
        this.description = description;
        this.done = done;
        Date dateNow= new Date();
        SimpleDateFormat sdf=new SimpleDateFormat("yyyy/MM/dd");
        this.date=sdf.format(dateNow);
    }

    public String getDeadLine() {
		return deadLine;
	}

	public void setDeadLine(String deadLine) {
		this.deadLine = deadLine;
	}

	public long getId() {
        return id;
    }

    public String getDescription() {
        return description;
    }

    public boolean isDone() {
        return done;
    }

    public void setDone(boolean done) {
        this.done = done;
    }

	public String getDate() {
		return date;
	}
}
