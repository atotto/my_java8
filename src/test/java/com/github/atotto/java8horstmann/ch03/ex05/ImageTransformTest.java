package com.github.atotto.java8horstmann.ch03.ex05;

import java.io.IOException;
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
	public void test() throws IOException {
		// TODO add image test
	}

	public static Image transform(Image in, UnaryOperator<Color> f) {
		int width = (int) in.getWidth();
		int height = (int) in.getHeight();
		WritableImage out = new WritableImage(width, height);

		for (int x = 0; x < width; x++) {
			for (int y = 0; y < height; y++) {
				out.getPixelWriter().setColor(x, y,
						f.apply(in.getPixelReader().getColor(x, y)));
			}
		}
		return out;
	}

	public static Image transform(Image in, ColorTransformer f) {
		int width = (int) in.getWidth();
		int height = (int) in.getHeight();
		WritableImage out = new WritableImage(width, height);

		for (int x = 0; x < width; x++) {
			for (int y = 0; y < height; y++) {
				out.getPixelWriter().setColor(x, y,
						f.apply(x, y, in.getPixelReader().getColor(x, y)));
			}
		}
		return out;
	}

	public static ColorTransformer setFrame(Image in) {
		return (x, y, c) -> {
			if (x < 10 || x > in.getWidth() - 10 || y < 10
					|| y > in.getHeight() - 10) {
				return Color.GRAY;
			} else {
				return c;
			}
		};
	}

	@Override
	public void start(Stage stage) {
		String path = getClass().getResource("/images/project.png").toString();
		Image src = new Image(path);
		Image dst = transform(src, setFrame(src));
		stage.setScene(new Scene(new HBox(new ImageView(src),
				new ImageView(dst))));
		stage.show();
	}

	public static void main(String[] args) {
		Application.launch(args);
	}
}
