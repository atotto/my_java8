package com.github.atotto.digitalclock;

import javafx.application.Application;
import javafx.geometry.Side;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ColorPicker;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseButton;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.transform.Scale;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebHistory;
import javafx.scene.web.WebView;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

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
		background.setOnMousePressed((e) -> {
			if (e.getButton() == MouseButton.PRIMARY) {
				posX = e.getX();
				posY = e.getY();
			}
		});
		background.setOnMouseDragged((e) -> {
			if (e.getButton() == MouseButton.PRIMARY) {
				double nowX = stage.getX();
				double nowY = stage.getY();
				stage.getScene().getWindow().setX(nowX + e.getX() - posX);
				stage.getScene().getWindow().setY(nowY + e.getY() - posY);
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
		fullscreen.setOnMouseClicked((e) -> {
			stage.setFullScreen(true);
		});

		stage.fullScreenProperty().addListener(
				(observable, oldValue, newValue) -> {
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
				});

		// === digital clock ===
		clock = new SevenSegmentClock(Color.ORANGERED, Color.WHITESMOKE);

		clock.layoutXProperty().bind(scene.widthProperty().add(-400));
		clock.setLayoutY(10);
		clock.getTransforms().add(new Scale(0.83f, 0.83f, 0, 0));
		clock.play();

		// color
		final ColorPicker colorPicker = new ColorPicker(clock.getOnColor());
		colorPicker.setOnAction((e) -> {
			Color newColor = colorPicker.getValue();
			clock.setOnColor(newColor);
		});

		// === context menu ===
		final ContextMenu contextMenu = new ContextMenu();

		MenuItem clocksw = new MenuItem("Clock ON/OFF");
		clocksw.setOnAction((e) -> {
			clockEnable = !clockEnable;
			if (clockEnable) {
				clock.on();
			} else {
				clock.off();
			}
		});
		MenuItem backgroundSelect = new MenuItem("Select background image");
		Stage backgroundSelectorPane = createBackgroundSelectorPane(stage);
		backgroundSelect.setOnAction((e) -> {
			backgroundSelectorPane.setX(stage.getX());
			backgroundSelectorPane.setY(stage.getY());
			backgroundSelectorPane.setWidth(stage.getWidth() + 100);
			backgroundSelectorPane.setHeight(stage.getHeight() + 100);
			stage.hide();
			backgroundSelectorPane.showAndWait();
		});
		MenuItem color = new MenuItem("Color");
		color.setOnAction((e) -> {
			Stage st = new Stage();
			st.initModality(Modality.APPLICATION_MODAL);
			st.initOwner(stage);
			st.setX(stage.getX());
			st.setY(stage.getY());

			Scene sc = new Scene(colorPicker, 200, 300);
			st.setScene(sc);
			st.showAndWait();
		});
		MenuItem quit = new MenuItem("Quit");
		quit.setOnAction((e) -> {
			System.exit(0);
		});
		contextMenu.getItems().addAll(clocksw, backgroundSelect, color, quit);
		setting.setOnMouseClicked((e) -> {
			contextMenu.show(setting, Side.BOTTOM, 0, 0);
		});

		root.getChildren().addAll(background, fullscreen, setting, clock);
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

	private Stage createBackgroundSelectorPane(Stage stage) {
		Stage st = new Stage();
		st.initStyle(StageStyle.UNDECORATED);
		st.initModality(Modality.APPLICATION_MODAL);
		st.initOwner(stage);

		WebView webView = new WebView();
		WebEngine engine = webView.getEngine();

		TextField urlTextField = new TextField("http://");
		Button backButton = new Button("<<");
		backButton.setDisable(true);
		Button forwardButton = new Button(">>");
		forwardButton.setDisable(true);
		urlTextField.setOnAction((e) -> {
			engine.load(((TextField) e.getSource()).getText());
		});
		engine.setOnStatusChanged((e) -> {
			WebHistory ht = engine.getHistory();
			if (ht.getCurrentIndex() > 0) {
				backButton.setDisable(false);
			} else {
				backButton.setDisable(true);
			}
			if (ht.getCurrentIndex() < ht.getEntries().size() - 1) {
				forwardButton.setDisable(false);
			} else {
				forwardButton.setDisable(true);
			}
		});
		backButton.setOnAction((e) -> {
			WebHistory ht = engine.getHistory();
			ht.go(-1);
			urlTextField.setText(engine.getLocation());
		});
		forwardButton.setOnAction((e) -> {
			WebHistory ht = engine.getHistory();
			ht.go(1);
			urlTextField.setText(engine.getLocation());
		});
		Button ok = new Button("OK");
		ok.setOnAction((e) -> {
			background.setImage(webView.snapshot(null, null));
			stage.show();
			st.close();
		});
		Button cancel = new Button("Cancel");
		cancel.setOnAction((e) -> {
			stage.show();
			st.close();
		});
		BorderPane pane = new BorderPane();
		HBox ctrl = new HBox(ok, cancel);
		HBox box = new HBox(backButton, forwardButton, urlTextField);

		pane.setTop(box);
		pane.setCenter(webView);
		pane.setBottom(ctrl);

		Scene sc = new Scene(pane, 200, 300);
		st.setScene(sc);
		return st;
	}
}
