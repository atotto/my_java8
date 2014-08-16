package com.github.atotto.java8horstmann.ch01.ex05.clock;

import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.KeyListener;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.TimeZone;

import javax.swing.JPanel;
import javax.swing.JSlider;

public class ProgressPanel extends JPanel {

	private static final long serialVersionUID = -8483677666452465265L;

	private static ClockSlider slider_hour = new ClockSlider(24);
	private static ClockSlider slider_min = new ClockSlider(60);
	private static ClockSlider slider_sec = new ClockSlider(60);

	static class ClockSlider extends JSlider {

		private static final long serialVersionUID = 4070158171214566911L;

		public ClockSlider(int max) {
			super(JSlider.HORIZONTAL, 0, max, 0);

			this.setPaintTicks(true);
			this.setPaintLabels(true);

			MouseListener[] mouseListeners = this.getMouseListeners();
			for (MouseListener mouseListener : mouseListeners) {
				this.removeMouseListener(mouseListener);
			}

			KeyListener[] keyListeners = this.getKeyListeners();
			for (KeyListener keyListener : keyListeners) {
				this.removeKeyListener(keyListener);
			}
		}
	}

	public ProgressPanel(Config config) {

		// hour
		slider_hour.setMajorTickSpacing(3);
		slider_hour.setMinorTickSpacing(1);
		// slider_hour.set

		// min
		slider_min.setMajorTickSpacing(15);
		slider_min.setMinorTickSpacing(1);

		// sec
		slider_sec.setMajorTickSpacing(10);
		slider_sec.setMinorTickSpacing(1);

		this.setBackground(config.getBackgroundColor());
		this.setForeground(config.getFontColor());
		this.setFont(config.getFont());

		// layout
		this.setLayout(new GridLayout(3, 1));

		// add
		this.add(slider_hour);
		this.add(slider_min);
		this.add(slider_sec);

		this.updateSlider();
	}

	@Override
	public void setBackground(Color bg) {
		super.setBackground(bg);
		slider_hour.setBackground(bg);
		slider_min.setBackground(bg);
		slider_sec.setBackground(bg);
	}

	@Override
	public void setForeground(Color fg) {
		super.setForeground(fg);
		slider_hour.setForeground(fg);
		slider_min.setForeground(fg);
		slider_sec.setForeground(fg);
	}

	@Override
	public void setFont(Font font) {
		super.setFont(font);
		slider_hour.setFont(font);
		slider_min.setFont(font);
		slider_sec.setFont(font);
	}

	public void updateSlider() {
		// see
		// http://docs.oracle.com/javase/jp/6/api/java/util/GregorianCalendar.html
		GregorianCalendar calendar = new GregorianCalendar(
				TimeZone.getTimeZone("Japan"));
		slider_hour.setValue(calendar.get(Calendar.HOUR_OF_DAY));
		slider_min.setValue(calendar.get(Calendar.MINUTE));
		slider_sec.setValue(calendar.get(Calendar.SECOND));
	}

	@Override
	public synchronized void addMouseListener(MouseListener l) {
		super.addMouseListener(l);
		slider_hour.addMouseListener(l);
		slider_min.addMouseListener(l);
		slider_sec.addMouseListener(l);
	}

	@Override
	public synchronized void addMouseMotionListener(MouseMotionListener l) {
		super.addMouseMotionListener(l);
		slider_hour.addMouseMotionListener(l);
		slider_min.addMouseMotionListener(l);
		slider_sec.addMouseMotionListener(l);
	}
}
