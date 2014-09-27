package com.github.atotto.java8horstmann.ch03.ex06;

import java.util.function.BiFunction;
import java.util.function.UnaryOperator;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.image.WritableImage;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import org.junit.Test;

public class ImageTransformTest extends Application {

	@Test
	public void test() {
		// TODO add test
	}

	public static Image transform(Image in, UnaryOperator<Color> f) {
		int width = (int) in.getWidth();
		int height = (int) in.getHeight();
		WritableImage out = new WritableImage(width, height);

		for (int x = 0; x < width; x++)
			for (int y = 0; y < height; y++)
				out.getPixelWriter().setColor(x, y,
						f.apply(in.getPixelReader().getColor(x, y)));
		return out;
	}

	public static <T> Image transform(Image in, BiFunction<Color, T, Color> f,
			T arg) {
		int width = (int) in.getWidth();
		int height = (int) in.getHeight();
		WritableImage out = new WritableImage(width, height);

		for (int x = 0; x < width; x++)
			for (int y = 0; y < height; y++)
				out.getPixelWriter().setColor(x, y,
						f.apply(in.getPixelReader().getColor(x, y), arg));
		return out;
	}

	public static UnaryOperator<Color> brighten(double factor) {
		return c -> c.deriveColor(0, 1, factor, 1);
	}

	public void start(Stage stage) {
		String path = getClass().getResource("/images/project.png").toString();
		Image src = new Image(path);
		Image dst = transform(src,
				(c, factor) -> c.deriveColor(0, 1, factor, 1), 1.2);
		// Image dst = transform(src, brighten(1.2));

		stage.setScene(new Scene(new HBox(new ImageView(src),
				new ImageView(dst))));
		stage.show();
	}

	public static void main(String[] args) {
		Application.launch(args);
	}
}
