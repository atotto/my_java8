package com.github.atotto.java8horstmann.ch01.ex05.clock;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.font.TextLayout;
import java.awt.geom.Rectangle2D;
import java.util.GregorianCalendar;
import java.util.TimeZone;

import javax.swing.JPanel;

public class ClockPanel extends JPanel {

	private static final long serialVersionUID = 5748159979557134680L;
	private Config config;

	public ClockPanel(Config config) {
		this.config = config;
	}

	@Override
	public void paintComponent(Graphics graphics) {
		super.paintComponent(graphics);
		Graphics2D g = (Graphics2D) graphics;

		paintClock(g);
	}

	private void paintClock(Graphics2D g) {
		Point pt = new Point(getWidth() / 2, 0);

		// 背景をクリア
		g.setBackground(config.getBackgroundColor());
		g.clearRect(0, 0, getWidth(), getHeight());

		// Font settings
		g.setFont(config.getFont());
		g.setPaint(config.getFontColor());

		// 時計文字描画
		Dimension clockSize = this.getPreferredSize();
		Point clockPosition = new Point(pt.x - (int) clockSize.getWidth() / 2,
				pt.y - 5);

		g.drawString(config.clock(new GregorianCalendar(TimeZone
				.getTimeZone("Japan"))), clockPosition.x, clockPosition.y
				+ (int) clockSize.getHeight());
	}

	@Override
	public Dimension getPreferredSize() {
		Rectangle2D size = this.getClockSize();
		return new Dimension((int) size.getWidth() + 10,
				(int) size.getHeight() + 10);
	}

	private Rectangle2D getClockSize() {
		return new TextLayout("0000/00/00 00:00:00", config.getFont(),
				((Graphics2D) this.getGraphics()).getFontRenderContext())
				.getBounds();
	}

}
