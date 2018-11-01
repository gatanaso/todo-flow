package org.vaadin.gatanaso;

import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.ComponentEvent;
import com.vaadin.flow.component.ComponentEventListener;
import com.vaadin.flow.component.HasComponents;
import com.vaadin.flow.component.HasStyle;
import com.vaadin.flow.component.Tag;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.html.Span;
import com.vaadin.flow.component.icon.Icon;
import com.vaadin.flow.component.icon.VaadinIcon;

@Tag("todo-task")
@SuppressWarnings("serial")
public class TodoComponent extends Component implements HasStyle, HasComponents {

	private Todo task;
	
	public TodoComponent(Todo task) {
		this.task = task;
		Span name = setupTitle(task);		
		Button doneBtn = setupDoneBtn(task);
		Button deleteBtn = setupDeleteBtn();
		add(name, doneBtn, deleteBtn);
	}

	private Span setupTitle(Todo task) {
		Span name = new Span(task.getTask());
		name.addClassName("task-title");
		return name;
	}

	private Button setupDeleteBtn() {
		Button deleteBtn = new Button();
		deleteBtn.addClassName("task-action");
		Icon deleteBtnIcon = new Icon(VaadinIcon.TRASH);
		deleteBtnIcon.getElement().setAttribute("slot", "prefix");
		deleteBtn.setIcon(deleteBtnIcon);
		deleteBtn.addClickListener(event -> {
			fireEvent(new DeleteTaskEvent(this, false, this.task));
		});
		return deleteBtn;
	}

	private Button setupDoneBtn(Todo task) {
		Button doneBtn = new Button();
		doneBtn.addClassName("task-action");
		Icon doneBtnIcon = new Icon(VaadinIcon.CHECK);
		doneBtnIcon.getElement().setAttribute("slot", "prefix");
		doneBtn.setIcon(doneBtnIcon);
		doneBtn.addClickListener(event-> {
			task.setDone(true);
			doneBtn.setEnabled(false);	
			setClassName("done");
		});
		return doneBtn;
	}

	public void addDeleteTaskEventListener(ComponentEventListener<DeleteTaskEvent> listener) {
		addListener(DeleteTaskEvent.class, listener);
	}
	
	class DeleteTaskEvent extends ComponentEvent<TodoComponent> {
		private Todo task;

		public DeleteTaskEvent(TodoComponent source, boolean fromClient, Todo task) {
			super(source, fromClient);
			this.task = task;
		}

		public Todo getTask() {
			return task;
		}

		public void setTask(Todo task) {
			this.task = task;
		}
	}	
}
