package com.github.atotto.java8horstmann.ch03.ex08;

import static com.github.atotto.java8horstmann.ch03.ex05.ImageTransformTest.transform;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import org.junit.Test;

import com.github.atotto.java8horstmann.ch03.ex05.ColorTransformer;

public class ImageTransformTest extends Application {

	@Test
	public void test() {
		// TODO add image test
	}

	public static ColorTransformer setFrame(Image in, Color color, int width) {
		return (x, y, c) -> {
			if (x < width || x > in.getWidth() - width || y < width
					|| y > in.getHeight() - width) {
				return color;
			} else {
				return c;
			}
		};
	}

	@Override
	public void start(Stage stage) {
		String path = getClass().getResource("/images/project.png").toString();
		Image src = new Image(path);
		Image dst = transform(src, setFrame(src, Color.RED, 5));
		stage.setScene(new Scene(new HBox(new ImageView(src),
				new ImageView(dst))));
		stage.show();
	}

	public static void main(String[] args) {
		Application.launch(args);
	}
}
