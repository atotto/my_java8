package com.github.atotto.java8horstmann.ch04.ex09;

import javafx.animation.Animation;
import javafx.animation.PathTransition;
import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Ellipse;
import javafx.stage.Stage;
import javafx.util.Duration;

public class SolarSystem extends Application {

	static final int width = 600;
	static final int height = 600;
	static final int solarPos_x = width / 2;
	static final int solarPos_y = height / 2;

	static class Planet extends Circle {

		private Planet(Color color, double radius, double radiusX,
				double radiusY, double rotate, double period) {
			this.setCenterX(0);
			this.setCenterY(0);
			this.setFill(color);
			this.setRadius(radius);

			Ellipse path = new Ellipse(solarPos_x, solarPos_y, radiusX, radiusY);
			path.setRotate(rotate);

			PathTransition pathTransition = new PathTransition();
			pathTransition.setDuration(Duration.millis(period));
			pathTransition.setPath(path);
			pathTransition.setNode(this);
			pathTransition.setCycleCount(Animation.INDEFINITE);
			pathTransition.play();
		}

		static Planet earth = new Planet(Color.DARKBLUE, 12, 100, 100, 0.00005,
				1000);
		static Planet comet = new Planet(Color.BLACK, 2, 120, 600, 30, 4000);
	}

	@Override
	public void start(Stage stage) throws Exception {
		Group space = new Group();
		Circle sun = new Circle(solarPos_x, solarPos_y, 20, Color.ORANGERED);

		space.getChildren().addAll(sun, Planet.earth, Planet.comet);

		stage.setScene(new Scene(space, width, height));
		stage.show();
	}

	public static void main(String[] args) {
		Application.launch(args);
	}
}
