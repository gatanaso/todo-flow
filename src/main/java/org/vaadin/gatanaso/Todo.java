package org.vaadin.gatanaso;

public class Todo {
	
	private String task;
	private boolean done;
	
	public Todo(String task) {
		this.task = task;
	}	
	
	public Todo(String task, Boolean done) {
		this.task = task;
		this.done = done;
	}

	public String getTask() {
		return task;
	}

	public void setTask(String task) {
		this.task = task;
	}

	public boolean getDone() {
		return done;
	}

	public void setDone(boolean done) {
		this.done = done;
	}
}
