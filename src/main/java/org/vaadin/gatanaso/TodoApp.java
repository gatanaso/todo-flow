package org.vaadin.gatanaso;

import java.util.ArrayList;
import java.util.List;

import com.vaadin.flow.component.Key;
import com.vaadin.flow.component.Tag;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.dependency.HtmlImport;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.Route;

@SuppressWarnings("serial")
@Route("")
@Tag("todo-app")
@HtmlImport("frontend://styles/shared-styles.html")
public class TodoApp extends VerticalLayout {
	
	private List<Todo> tasks = new ArrayList<>();
	
	private HorizontalLayout todoInputContainer;
	private TextField todoTxtField;
	private Div taskContainer;

    public TodoApp() {
    	todoInputContainer = setupInputContainer();
    	taskContainer = setupTaskContainer();
    	add(todoInputContainer, taskContainer);
    	setDefaultHorizontalComponentAlignment(Alignment.CENTER);
    }

	private HorizontalLayout setupInputContainer() {
		
		todoTxtField = new TextField();
    	todoTxtField.setPlaceholder("Add task...");
    	todoTxtField.addClassName("todo-input");
    	todoTxtField.addKeyUpListener(Key.ENTER, e -> addTask(todoTxtField.getValue()));
    	
    	Button addTodo = new Button("Add");
    	addTodo.addClickListener(event -> {
    		addTask(todoTxtField.getValue());
    	});

    	HorizontalLayout todoInputContainer = new HorizontalLayout();
    	todoInputContainer.add(todoTxtField, addTodo);
    	todoInputContainer.setClassName("task-input");

		return todoInputContainer;
	}
	
	private Div setupTaskContainer() {
		Div taskContainer = new Div();
    	taskContainer.setClassName("task-container");
    	return taskContainer;
	}	

	private void addTask(String taskName) {

		if (taskName != null && taskName.trim().length() > 0) {

			Todo task = new Todo(taskName);
			tasks.add(task);
			
			TodoComponent taskViewComponent = new TodoComponent(task);
			taskContainer.add(taskViewComponent);
			taskViewComponent.addDeleteTaskEventListener(e -> {
				tasks.remove(e.getTask());
				taskContainer.remove(e.getSource());
			});
			
			todoTxtField.clear();
		}
	}
}
