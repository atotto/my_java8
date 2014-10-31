package com.github.atotto.java8horstmann.ch04.ex04;

import javafx.application.Application;
import javafx.beans.binding.Bindings;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;

public class CentralizedCircle extends Application {

	// Original source code is Fig4_4.
	public void start(Stage stage) {
		Circle circle = new Circle(100, 100, 100);
		circle.setFill(Color.GRAY);
		Pane pane = new Pane();
		pane.getChildren().add(circle);
		Scene scene = new Scene(pane);
		circle.centerXProperty()
				.bind(Bindings.divide(scene.widthProperty(), 2));
		circle.centerYProperty().bind(
				Bindings.divide(scene.heightProperty(), 2));
		circle.radiusProperty().bind(
				Bindings.min(scene.heightProperty().divide(2), scene
						.widthProperty().divide(2)));
		stage.setScene(scene);
		stage.show();
	}

	public static void main(String[] args) {
		Application.launch(args);
	}
}
