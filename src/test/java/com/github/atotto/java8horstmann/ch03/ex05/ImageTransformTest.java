package com.github.atotto.java8horstmann.ch03.ex05;

import java.io.File;
import java.io.IOException;
import java.util.function.UnaryOperator;

import javafx.application.Application;
import javafx.embed.swing.SwingFXUtils;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.image.WritableImage;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import javax.imageio.ImageIO;

import org.junit.Test;

@FunctionalInterface
interface ColorTransformer {
	Color apply(int x, int y, Color colorAtXY);
}

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

	public static ColorTransformer setFrame(Image src) {
		return (x, y, c) -> {
			if (x < 10 || x > src.getWidth() - 10 || y < 10
					|| y > src.getHeight() - 10) {
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
		File file = new File("out.png");
		try {
			ImageIO.write(SwingFXUtils.fromFXImage(dst, null), "png", file);
		} catch (IOException e) {
			e.printStackTrace();
		}
		stage.setScene(new Scene(new HBox(new ImageView(src),
				new ImageView(dst))));
		stage.show();
		// Platform.exit();
	}

	public static void main(String[] args) {
		Application.launch(args);
	}
}
