package com.github.atotto.java8horstmann.ch04.ex01;

import javafx.application.Application;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.stage.Stage;

public class HelloJavaFX extends Application {

	VBox parent;

	public HelloJavaFX() {
		Label label = new Label("Hello, FX");
		label.setId("label");
		label.setFont(new Font(100));
		TextField textField = new TextField(label.getText());
		textField.setId("text_field");
		textField.setOnAction(event -> {
			TextField field = (TextField) event.getSource();
			label.setText(field.getText());
			label.setWrapText(true);
		});
		parent = new VBox(label, textField);
	}

	@Override
	public void start(Stage stage) throws Exception {
		stage.setTitle("ex01");
		stage.setScene(new Scene(parent));
		stage.show();
	}

	public Parent getParent() {
		return parent;
	}
}
