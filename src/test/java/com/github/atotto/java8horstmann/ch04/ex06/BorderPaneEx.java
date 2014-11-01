package com.github.atotto.java8horstmann.ch04.ex06;

import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class BorderPaneEx extends Application {
	public void start(Stage stage) {
		BorderPane pane = new BorderPane();
		pane.setTop(new Button("Top"));
		BorderPane.setAlignment(pane.getTop(), Pos.TOP_CENTER);
		pane.setLeft(new Button("Left"));
		pane.setCenter(new Button("Center"));
		pane.setRight(new Button("Right"));
		pane.setBottom(new Button("Bottom"));
		BorderPane.setAlignment(pane.getBottom(), Pos.BOTTOM_CENTER);
		stage.setScene(new Scene(pane));
		stage.show();
	}

	public static void main(String[] args) {
		Application.launch(args);
	}
}
