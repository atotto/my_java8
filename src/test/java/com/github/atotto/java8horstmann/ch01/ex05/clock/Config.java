package com.github.atotto.java8horstmann.ch01.ex05.clock;

import java.awt.Color;
import java.awt.Font;
import java.awt.Insets;
import java.awt.Point;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Observable;
import java.util.prefs.Preferences;

public class Config extends Observable {
	private Preferences prefs;

	private DateFormat clockFormat;
	private DateFormat simpleClockFormat;
	private Insets margin;

	private String fontname;
	private int fontsize;

	private Color backgroundColor;
	private Color fontColor;
	private Point clockPos;

	private float opacity;

	public Config() {
		// Load user preferences
		prefs = Preferences.userNodeForPackage(this.getClass());

		// Set preferences
		clockFormat = new SimpleDateFormat(prefs.get("a2.clockFormat",
				"yyyy/MM/dd HH:mm:ss"));
		simpleClockFormat = new SimpleDateFormat(prefs.get(
				"a2.simpleClockFormat", "HH:mm:ss"));
		fontsize = prefs.getInt("a2.fontsize", 20);
		fontname = prefs.get("a2.fontname", "Consolas");
		backgroundColor = new Color(prefs.getInt("a2.backgroundColor",
				Color.DARK_GRAY.getRGB()));
		fontColor = new Color(
				prefs.getInt("a2.fontColor", Color.WHITE.getRGB()));

		clockPos = new Point(prefs.getInt("a2.clockPos_x", 0), prefs.getInt(
				"a2.clockPos_y", 0));
		margin = new Insets(10, 10, 10, 10);

		opacity = prefs.getFloat("a2.opacity", 0.72f);
	}

	public Config(Config conf) {
		this.set(conf);
	}

	public void set(Config conf) {
		this.clockFormat = conf.clockFormat;
		this.simpleClockFormat = conf.simpleClockFormat;
		this.fontname = conf.fontname;
		this.fontsize = conf.fontsize;
		this.backgroundColor = conf.backgroundColor;
		this.fontColor = conf.fontColor;
		this.margin = conf.margin;
		this.clockPos = conf.clockPos;
		this.opacity = conf.opacity;

		setChanged();
		notifyObservers("all");
	}

	public void savePref() {
		// Load user preferences
		prefs = Preferences.userNodeForPackage(this.getClass());

		// Save preferences
		// clockFormat
		// simpleClockFormat
		prefs.putInt("a2.fontsize", fontsize);
		prefs.put("a2.fontname", fontname);
		prefs.putInt("a2.backgroundColor", backgroundColor.getRGB());
		prefs.putInt("a2.fontColor", fontColor.getRGB());
		prefs.putInt("a2.clockPos_x", clockPos.x);
		prefs.putInt("a2.clockPos_y", clockPos.y);
		prefs.putFloat("a2.opacity", opacity);
	}

	public String clock(Calendar cal) {
		clockFormat.setTimeZone(cal.getTimeZone());
		return clockFormat.format(cal.getTime());
	}

	public String simpleClock(Calendar cal) {
		return simpleClockFormat.format(cal.getTime());
	}

	public Font getFont() {
		return new Font(fontname, Font.PLAIN, fontsize);
	}

	public void setFont(Font font) {
		this.fontname = font.getName();
		this.fontsize = font.getSize();

		setChanged();
		notifyObservers("font");
	}

	public String getFontName() {
		return fontname;
	}

	public void setFontName(String fontname) {
		this.fontname = fontname;

		setChanged();
		notifyObservers("font");
	}

	public int getFontSize() {
		return fontsize;
	}

	public void setFontSize(int fontsize) {
		this.fontsize = fontsize;

		setChanged();
		notifyObservers("font");
	}

	public Color getBackgroundColor() {
		return backgroundColor;
	}

	public void setBackgroundColor(Color color) {
		backgroundColor = color;

		setChanged();
		notifyObservers("color");
	}

	public Color getFontColor() {
		return fontColor;
	}

	public void setFontColor(Color color) {
		fontColor = color;

		setChanged();
		notifyObservers("color");
	}

	public Insets getMargin() {
		return this.margin;
	}

	public Point getPosition() {
		return clockPos;
	}

	public void setPosition(Point pos) {
		this.clockPos = pos;
	}

	public float getOpacity() {
		return this.opacity;
	}

	public void setOpacity(float opacity) {
		this.opacity = opacity;

		setChanged();
		notifyObservers("opacity");
	}
}
