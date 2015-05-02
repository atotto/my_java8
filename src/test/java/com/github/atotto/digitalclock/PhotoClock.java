package com.github.atotto.digitalclock;

import javafx.application.Application;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Side;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.ColorPicker;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.MenuItem;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.scene.transform.Scale;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.stage.WindowEvent;

public class PhotoClock extends Application {

	public static void main(String[] args) {
		launch(args);
	}

	private ImageView background = new ImageView();

	private SevenSegmentClock clock;
	private boolean clockEnable = true;

	private double posX = 0;
	private double posY = 0;

	@Override
	public void start(final Stage stage) throws Exception {

		// remove window decoration
		stage.initStyle(StageStyle.UNDECORATED);

		Group root = new Group();
		Scene scene = new Scene(root, 610, 400);

		stage.setTitle("Viewer");
		stage.setResizable(true);
		stage.setScene(scene);

		// Resize the background image when window size are changed.
		background.fitWidthProperty().bind(scene.widthProperty());
		background.setPreserveRatio(true);
		background.setOnMousePressed(new EventHandler<MouseEvent>() {

			@Override
			public void handle(MouseEvent e) {
				if (e.getButton() == MouseButton.PRIMARY) {
					posX = e.getX();
					posY = e.getY();
				}
			}
		});
		background.setOnMouseDragged(new EventHandler<MouseEvent>() {

			@Override
			public void handle(MouseEvent e) {
				if (e.getButton() == MouseButton.PRIMARY) {
					double nowX = stage.getX();
					double nowY = stage.getY();
					stage.getScene().getWindow().setX(nowX + e.getX() - posX);
					stage.getScene().getWindow().setY(nowY + e.getY() - posY);
				}
			}
		});

		final ImageView setting = new ImageView(new Image(
				"digitalclock/assets/settings.png"));
		setting.setId("settings");
		setting.setOpacity(0.5);
		setting.setFitWidth(34);
		setting.setFitHeight(34);
		setting.setLayoutX(10);
		setting.setLayoutY(8);

		final ImageView fullscreen = new ImageView(new Image(
				"digitalclock/assets/fullscreen.png"));
		fullscreen.setOpacity(0.5);
		fullscreen.setFitWidth(30);
		fullscreen.setFitHeight(30);
		fullscreen.setLayoutX(50);
		fullscreen.setLayoutY(10);
		fullscreen.setOnMouseClicked(new EventHandler<MouseEvent>() {

			@Override
			public void handle(MouseEvent e) {
				stage.setFullScreen(true);
			}
		});

		stage.fullScreenProperty().addListener(new ChangeListener<Boolean>() {

			@Override
			public void changed(ObservableValue<? extends Boolean> observable,
					Boolean oldValue, Boolean newValue) {
				if (newValue != null) {
					if (newValue == true) {
						// FIXME
						setting.setOpacity(0);
						fullscreen.setOpacity(0);
						System.out.println("full");
					} else {
						// FIXME
						setting.setOpacity(0.5);
						fullscreen.setOpacity(0.5);
						System.out.println("fullout");
					}
				}
			}
		});

		// add digital clock
		clock = new SevenSegmentClock(Color.ORANGERED, Color.WHITESMOKE);

		clock.layoutXProperty().bind(scene.widthProperty().add(-400));
		clock.setLayoutY(10);
		clock.getTransforms().add(new Scale(0.83f, 0.83f, 0, 0));
		clock.play();

		// color
		final ColorPicker colorPicker = new ColorPicker(clock.getOnColor());
		colorPicker.setOpacity(0); // hide button // FIXME
		colorPicker.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent e) {
				Color newColor = colorPicker.getValue();
				clock.setOnColor(newColor);
			}
		});

		// add context menu
		final ContextMenu contextMenu = new ContextMenu();
		contextMenu.setOnShowing(new EventHandler<WindowEvent>() {
			public void handle(WindowEvent e) {
				System.out.println("showing");
			}
		});
		contextMenu.setOnShown(new EventHandler<WindowEvent>() {
			public void handle(WindowEvent e) {
				System.out.println("shown");
			}
		});

		MenuItem about = new MenuItem("Clock ON/OFF");
		about.setOnAction(new EventHandler<ActionEvent>() {
			public void handle(ActionEvent e) {
				clockEnable = !clockEnable;
				if (clockEnable) {
					System.out.println("clock on");
					clock.on();
				} else {
					System.out.println("clock off");
					clock.off();
				}
			}
		});
		MenuItem color = new MenuItem("Color");
		color.setOnAction(new EventHandler<ActionEvent>() {
			public void handle(ActionEvent e) {
				System.out.println("Color");
				colorPicker.show();
			}
		});
		MenuItem quit = new MenuItem("Quit");
		quit.setOnAction(new EventHandler<ActionEvent>() {
			public void handle(ActionEvent e) {
				System.out.println("Quit");
				System.exit(0);
			}
		});
		contextMenu.getItems().addAll(about, color, quit);

		setting.setOnMouseClicked(new EventHandler<MouseEvent>() {

			@Override
			public void handle(MouseEvent e) {
				contextMenu.show(setting, Side.BOTTOM, 0, 0);
			}
		});

		root.getChildren().addAll(background, fullscreen, setting, clock,
				colorPicker);

		stage.show();
	}

	@Override
	public void init() throws Exception {
		super.init();
	}

	@Override
	public void stop() throws Exception {
		super.stop();
		clock.stop();
	}
}
