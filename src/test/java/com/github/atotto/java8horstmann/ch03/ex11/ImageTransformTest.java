package com.github.atotto.java8horstmann.ch03.ex11;

import static com.github.atotto.java8horstmann.ch03.ex05.ImageTransformTest.setFrame;
import static com.github.atotto.java8horstmann.ch03.ex05.ImageTransformTest.transform;

import java.util.function.UnaryOperator;

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

	public static ColorTransformer compose(ColorTransformer tr1,
			ColorTransformer tr2) {
		return (x, y, c) -> {
			return tr2.apply(x, y, tr1.apply(x, y, c));
		};
	}

	public static ColorTransformer allover(UnaryOperator<Color> op) {
		return (x, y, c) -> op.apply(c);
	}

	@Override
	public void start(Stage stage) {
		String path = getClass().getResource("/images/project.png").toString();
		Image src = new Image(path);
		Image dst = transform(src,
				compose(setFrame(src), allover(Color::brighter)));
		stage.setScene(new Scene(new HBox(new ImageView(src),
				new ImageView(dst))));
		stage.show();
	}

	public static void main(String[] args) {
		Application.launch(args);
	}
}
