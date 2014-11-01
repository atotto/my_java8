package com.github.atotto.java8horstmann.ch04.ex05;

import static com.github.atotto.java8horstmann.ch04.ex05.LazyBinder.observe;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

public class Gauge extends Application {

	// Original source code is Fig4_5.
	public void start(Stage stage) {
		Button smaller = new Button("Smaller");
		Button larger = new Button("Larger");
		Rectangle gauge = new Rectangle(0, 5, 50, 15);
		Rectangle outline = new Rectangle(0, 5, 100, 15);
		outline.setFill(null);
		outline.setStroke(Color.BLACK);
		Pane pane = new Pane();
		pane.getChildren().addAll(gauge, outline);
		smaller.setOnAction(event -> gauge.setWidth(gauge.getWidth() - 10));
		larger.setOnAction(event -> gauge.setWidth(gauge.getWidth() + 10));
		smaller.disableProperty().bind(
				observe((t) -> t.intValue() <= 0, gauge.widthProperty()));
		larger.disableProperty().bind(
				observe((t) -> t.intValue() >= 100, gauge.widthProperty()));

		HBox box = new HBox(10);
		box.getChildren().addAll(smaller, pane, larger);
		Scene scene = new Scene(box);
		stage.setScene(scene);
		stage.show();
	}

	public static void main(String[] args) {
		Application.launch(args);
	}
}
