package com.github.atotto.java8horstmann.ch04.ex08;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.application.Application;
import javafx.beans.binding.Bindings;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class MyFXMLDemo extends Application implements Initializable {
	@FXML
	private TextField username;
	@FXML
	private PasswordField password;
	@FXML
	private Button okButton;

	public void initialize(URL url, ResourceBundle rb) {
		okButton.disableProperty().bind(
				Bindings.createBooleanBinding(
						() -> username.getText().length() == 0
								|| password.getText().length() == 0,
						username.textProperty(), password.textProperty()));
		okButton.setOnAction(event -> System.out.println("Verifying "
				+ username.getText() + ":" + password.getText()));
	}

	@Override
	public void start(Stage stage) {
		try {
			URL fxml = getClass().getResource("/fxml/ch04/ex08/dialog.fxml");
			Parent root = FXMLLoader.load(fxml);
			stage.setScene(new Scene(root));
			stage.show();
		} catch (IOException ex) {
			ex.printStackTrace();
			System.exit(0);
		}
	}

	public static void main(String[] args) {
		Application.launch(args);
	}
}
