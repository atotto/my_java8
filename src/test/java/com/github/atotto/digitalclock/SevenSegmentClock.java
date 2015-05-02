package com.github.atotto.digitalclock;

import java.util.Calendar;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.effect.Glow;
import javafx.scene.effect.InnerShadow;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Polygon;
import javafx.scene.transform.Shear;
import javafx.util.Duration;

public class SevenSegmentClock extends Parent {
	private Calendar calendar = Calendar.getInstance();
	private Digit[] digits;
	private Group dots;
	private Timeline delayTimeline, secondTimeline;

	private Color onColor, offColor;

	private static boolean isEnable = true;

	public SevenSegmentClock(Color onColor, Color offColor) {
		this.onColor = onColor;
		this.offColor = offColor;

		// create effect for on LEDs
		Glow onEffect = new Glow(1.7f);
		onEffect.setInput(new InnerShadow());

		// create effect for on dot LEDs
		Glow onDotEffect = new Glow(1.7f);
		onDotEffect.setInput(new InnerShadow(5, Color.BLACK));

		// create digits
		digits = new Digit[7];
		for (int i = 0; i < 6; i++) {
			Digit digit = new Digit(onColor, offColor);
			digit.setLayoutX(i * 80 + ((i + 1) % 2) * 20);
			digits[i] = digit;
			getChildren().add(digit);
		}
		// create dots
		dots = new Group(new Circle(80 + 54 + 20, 44, 6, onColor), new Circle(
				80 + 54 + 19, 64, 6, onColor), new Circle((80 * 3) + 54 + 20,
				44, 6, onColor), new Circle((80 * 3) + 54 + 19, 64, 6, onColor));
		// dots.setEffect(onDotEffect);
		getChildren().add(dots);
		// update digits to current time and start timer to update every second
		refreshClocks();
	}

	private void refreshClocks() {

		calendar.setTimeInMillis(System.currentTimeMillis());

		int hours = calendar.get(Calendar.HOUR_OF_DAY);
		int minutes = calendar.get(Calendar.MINUTE);
		int seconds = calendar.get(Calendar.SECOND);

		digits[0].showNumber(hours / 10);
		digits[1].showNumber(hours % 10);
		digits[2].showNumber(minutes / 10);
		digits[3].showNumber(minutes % 10);
		digits[4].showNumber(seconds / 10);
		digits[5].showNumber(seconds % 10);

		for (int i = 0; i < 6; i++) {
			digits[i].setOnColor(onColor);
		}
		for (Node dot : dots.getChildren()) {
			((Circle) dot).setFill((seconds % 2 == 0) ? onColor : offColor);
			if (isEnable) {
				((Circle) dot).setOpacity((seconds % 2 == 0) ? 1.0 : 0.2);
			} else {
				((Circle) dot).setOpacity(0.0f);
			}
		}
	}

	public void play() {
		// wait till start of next second then start a timeline to call
		// refreshClocks() every second
		delayTimeline = new Timeline();
		delayTimeline.getKeyFrames().add(
				new KeyFrame(new Duration(
						1000 - (System.currentTimeMillis() % 1000)),
						new EventHandler<ActionEvent>() {
							@Override
							public void handle(ActionEvent event) {
								if (secondTimeline != null) {
									secondTimeline.stop();
								}
								secondTimeline = new Timeline();
								secondTimeline
										.setCycleCount(Timeline.INDEFINITE);
								secondTimeline
										.getKeyFrames()
										.add(new KeyFrame(
												Duration.seconds(1),
												new EventHandler<ActionEvent>() {
													@Override
													public void handle(
															ActionEvent event) {
														refreshClocks();
													}
												}));
								secondTimeline.play();
							}
						}));
		delayTimeline.play();
	}

	public void stop() {
		delayTimeline.stop();
		if (secondTimeline != null) {
			secondTimeline.stop();
		}
	}

	/**
	 * 7 segment LED style digit.
	 */
	public static class Digit extends Parent {
		// http://en.wikipedia.org/wiki/Seven-segment_display
		private static final boolean[][] DIGIT_COMBINATIONS = new boolean[][] {
				new boolean[] { true, true, true, true, true, true, false }, // 0
				new boolean[] { false, true, true, false, false, false, false }, // 1
				new boolean[] { true, true, false, true, true, false, true }, // 2
				new boolean[] { true, true, true, true, false, false, true }, // 3
				new boolean[] { false, true, true, false, false, true, true }, // 4
				new boolean[] { true, false, true, true, false, true, true }, // 5
				new boolean[] { true, false, true, true, true, true, true }, // 6
				new boolean[] { true, true, true, false, false, false, false }, // 7
				new boolean[] { true, true, true, true, true, true, true }, // 8
				new boolean[] { true, true, true, true, false, true, true }, // 9
		};

		private final Polygon[] polygons = new Polygon[] {
				new Polygon(12, 0, 40, 0, 40, 10, 12, 10), // A
				new Polygon(42, 0, 52, 0, 52, 50, 42, 50), // B
				new Polygon(42, 60, 52, 60, 52, 110, 42, 110), // C
				new Polygon(12, 99, 40, 99, 40, 109, 12, 109), // D
				new Polygon(0, 60, 10, 60, 10, 110, 0, 110), // E
				new Polygon(0, 0, 10, 0, 10, 50, 0, 50), // F
				new Polygon(12, 50, 40, 50, 40, 60, 12, 60), // G
		};

		private Color onColor;
		private Color offColor;

		public Digit(Color onColor, Color offColor) {
			this.onColor = onColor;
			this.offColor = offColor;
			getChildren().addAll(polygons);
			getTransforms().add(new Shear(-0.05, 0));
			showNumber(0);
		}

		public void showNumber(Integer num) {
			if (num < 0 || num > 9)
				num = 0; // default to 0 for non-valid numbers
			for (int i = 0; i < 7; i++) {
				polygons[i].setFill(DIGIT_COMBINATIONS[num][i] ? onColor
						: offColor);
				if (isEnable) {
					polygons[i].setOpacity(DIGIT_COMBINATIONS[num][i] ? 1.0
							: 0.2);
				} else {
					polygons[i].setOpacity(0.0f);
				}
			}
		}

		public Color getOnColor() {
			return onColor;
		}

		public void setOnColor(Color onColor) {
			this.onColor = onColor;
		}

		public Color getOffColor() {
			return offColor;
		}

		public void setOffColor(Color offColor) {
			this.offColor = offColor;
		}

	}

	public Color getOnColor() {
		return onColor;
	}

	public void setOnColor(Color onColor) {
		this.onColor = onColor;
	}

	public Color getOffColor() {
		return offColor;
	}

	public void setOffColor(Color offColor) {
		this.offColor = offColor;
	}

	public void off() {
		SevenSegmentClock.isEnable = false;
	}

	public void on() {
		SevenSegmentClock.isEnable = true;
	}
}