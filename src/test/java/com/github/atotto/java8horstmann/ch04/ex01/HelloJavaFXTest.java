package com.github.atotto.java8horstmann.ch04.ex01;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;
import static org.loadui.testfx.Assertions.verifyThat;
import static org.loadui.testfx.controls.Commons.hasText;
import javafx.scene.Parent;
import javafx.scene.control.Label;
import javafx.scene.input.KeyCode;

import org.junit.Test;
import org.junit.experimental.categories.Category;
import org.loadui.testfx.GuiTest;

@Category(org.loadui.testfx.GuiTest.class)
public class HelloJavaFXTest extends GuiTest {

	@Override
	protected Parent getRootNode() {
		HelloJavaFX app = new HelloJavaFX();
		return app.getParent();
	}

	@Test
	public void testInitialize() {
		// The label should have the string `Hello, FX` in a 100 point font.
		Label label = find((Label l) -> l.getId() == "label");
		assertThat(label.getText(), is("Hello, FX"));
		assertThat(label.getFont().getSize(), is(100.0));

		// Initialize the text field with the same string.
		verifyThat("#text_field", hasText("Hello, FX"));
	}

	@Test
	public void testUserOperation() throws Exception {
		// Update the label as the user edits the text field.
		type("Some text").push(KeyCode.ENTER);

		verifyThat("#text_field", hasText("Some text"));
		verifyThat("#label", hasText("Some text"));
	}
}
