package com.github.atotto.java8horstmann.ch01.ex05.clock;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSlider;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

public class PropertiesDialog extends JDialog {

	private static final long serialVersionUID = 210549325687453262L;

	private Config config;
	private Config defaultConf;

	public PropertiesDialog(Config config) {
		super(null, ModalityType.APPLICATION_MODAL);
		this.setResizable(false);

		this.config = config;
		defaultConf = new Config(config);

		createPropertiesDialog();

		setTitle("Properties");
		pack();

		addWindowListener(new PropertiesDialogListener());

		setVisible(true);
	}

	private void createPropertiesDialog() {

		setFont(new Font("Arial", Font.PLAIN, 12)); // font set for properties
													// dialog.

		GridBagLayout layout = new GridBagLayout();

		setLayout(layout);

		JPanel propertiesPanel = new JPanel();
		JPanel controlPanel = new JPanel();

		GridBagConstraints gbc = new GridBagConstraints();

		// Layout
		gbc.gridx = 0;
		gbc.gridy = 0;
		gbc.gridheight = 1;
		gbc.weightx = 1.0d;
		gbc.weighty = 20.0d;
		layout.setConstraints(propertiesPanel, gbc);

		gbc.gridx = 0;
		gbc.gridy = 1;
		gbc.gridheight = 1;
		gbc.weightx = 1.0d;
		gbc.weighty = 1.0d;
		gbc.anchor = GridBagConstraints.SOUTHEAST;
		layout.setConstraints(controlPanel, gbc);

		add(propertiesPanel);
		add(controlPanel);

		{
			GridBagLayout layoutt = new GridBagLayout();
			double label_weight = 0.3d;
			double control_weight = 0.7d;

			// propertiesPanel.setLayout(new GridLayout(4, 2));
			propertiesPanel.setLayout(layoutt);
			GridBagConstraints gbcc = new GridBagConstraints();
			gbcc.insets = new Insets(2, 2, 2, 2);

			{
				// Font name
				JLabel label = new JLabel("Font name", JLabel.RIGHT);

				// Load System fonts.
				String fonts[] = SupportedProperties.supportedFonts();

				JComboBox<String> font_name_list = new JComboBox<String>(fonts);

				font_name_list.setSelectedItem(config.getFont().getName());
				font_name_list.addItemListener(new ItemListener() {
					@Override
					public void itemStateChanged(ItemEvent e) {
						config.setFont(new Font((String) e.getItem(), config
								.getFont().getStyle(), config.getFont()
								.getSize()));
					}
				});
				propertiesPanel.add(label);
				propertiesPanel.add(font_name_list);

				// Layout
				gbcc.gridx = 0;
				gbcc.gridy = 0;
				gbcc.gridheight = 1;
				gbcc.weightx = label_weight;
				gbcc.anchor = GridBagConstraints.EAST;
				layoutt.setConstraints(label, gbcc);
				gbcc.weightx = control_weight;
				gbcc.gridx = 1;
				gbcc.anchor = GridBagConstraints.WEST;
				layoutt.setConstraints(font_name_list, gbcc);
			}
			{
				// Font size
				JLabel label = new JLabel("Font size", JLabel.RIGHT);
				JSlider font_size_slider = new JSlider(JSlider.HORIZONTAL);
				font_size_slider.setSize(400, 50);
				font_size_slider.setPreferredSize(font_size_slider.getSize());

				// slider settings
				font_size_slider.setMaximum(120);
				font_size_slider.setMinimum(10);
				font_size_slider.setMajorTickSpacing(10);
				font_size_slider.setMinorTickSpacing(2);
				font_size_slider.setPaintTicks(true);
				font_size_slider.setPaintLabels(true);

				// set saved size
				font_size_slider.setValue(config.getFont().getSize());

				font_size_slider.addChangeListener(new ChangeListener() {
					@Override
					public void stateChanged(ChangeEvent e) {
						JSlider source = (JSlider) e.getSource();
						config.setFont(new Font(config.getFont().getFontName(),
								config.getFont().getStyle(), source.getValue()));
					}
				});
				propertiesPanel.add(label);
				propertiesPanel.add(font_size_slider);

				// Layout
				gbcc.gridx = 0;
				gbcc.gridy = 1;
				gbcc.gridheight = 1;
				gbcc.weightx = label_weight;
				gbcc.anchor = GridBagConstraints.EAST;
				layoutt.setConstraints(label, gbcc);
				gbcc.gridx = 1;
				gbcc.weightx = control_weight;
				gbcc.anchor = GridBagConstraints.WEST;
				layoutt.setConstraints(font_size_slider, gbcc);
			}
			{
				// Font color
				JLabel label = new JLabel("Font color", JLabel.RIGHT);

				ColorPicker colorpicker = new ColorPicker(config.getFontColor());
				colorpicker
						.addPropertyChangeListener(new PropertyChangeListener() {

							@Override
							public void propertyChange(PropertyChangeEvent evt) {
								if (evt.getPropertyName().equals("color")) {
									config.setFontColor((Color) evt
											.getNewValue());
								}
							}
						});
				propertiesPanel.add(label);
				propertiesPanel.add(colorpicker);

				// Layout
				gbcc.gridx = 0;
				gbcc.gridy = 2;
				gbcc.gridheight = 1;
				gbcc.weightx = label_weight;
				gbcc.anchor = GridBagConstraints.EAST;
				layoutt.setConstraints(label, gbcc);
				gbcc.gridx = 1;
				gbcc.weightx = control_weight;
				gbcc.anchor = GridBagConstraints.WEST;
				layoutt.setConstraints(colorpicker, gbcc);
			}
			{
				// Background color
				JLabel label = new JLabel("Background color", JLabel.RIGHT);

				ColorPicker colorpicker = new ColorPicker(
						config.getBackgroundColor());
				colorpicker
						.addPropertyChangeListener(new PropertyChangeListener() {

							@Override
							public void propertyChange(PropertyChangeEvent evt) {
								if (evt.getPropertyName().equals("color")) {
									config.setBackgroundColor((Color) evt
											.getNewValue());
								}
							}
						});
				propertiesPanel.add(label);
				propertiesPanel.add(colorpicker);

				// Layout
				gbcc.gridx = 0;
				gbcc.gridy = 3;
				gbcc.gridheight = 10;
				gbcc.weightx = label_weight;
				gbcc.anchor = GridBagConstraints.EAST;
				layoutt.setConstraints(label, gbcc);
				gbcc.gridx = 1;
				gbcc.weightx = control_weight;
				gbcc.anchor = GridBagConstraints.WEST;
				layoutt.setConstraints(colorpicker, gbcc);
			}
			{
				// Opacity
				JLabel label = new JLabel("Opacity", JLabel.RIGHT);
				JSlider slider = new JSlider(JSlider.HORIZONTAL);
				slider.setSize(400, 50);
				slider.setPreferredSize(slider.getSize());

				// slider settings
				slider.setMaximum(100);
				slider.setMinimum(10);
				slider.setMajorTickSpacing(20);
				slider.setMinorTickSpacing(10);
				slider.setPaintTicks(true);
				slider.setPaintLabels(true);

				// set saved size
				slider.setValue((int) (config.getOpacity() * 100.0f));

				slider.addChangeListener(new ChangeListener() {
					@Override
					public void stateChanged(ChangeEvent e) {
						JSlider source = (JSlider) e.getSource();
						config.setOpacity(source.getValue() / 100.0f);
					}
				});
				propertiesPanel.add(label);
				propertiesPanel.add(slider);

				// Layout
				gbcc.gridx = 0;
				gbcc.gridy = 14;
				gbcc.gridheight = 1;
				gbcc.weightx = label_weight;
				gbcc.anchor = GridBagConstraints.EAST;
				layoutt.setConstraints(label, gbcc);
				gbcc.gridx = 1;
				gbcc.weightx = control_weight;
				gbcc.anchor = GridBagConstraints.WEST;
				layoutt.setConstraints(slider, gbcc);
			}
		}
		{
			controlPanel.setLayout(new FlowLayout(FlowLayout.RIGHT));
			// cancel
			JButton cancel = new JButton("Cancel");
			cancel.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					PropertiesDialog.this.revert(); // もとに戻す
					dispose();
				}
			});
			// ok
			JButton ok = new JButton("OK");
			ok.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					dispose();
				}
			});

			controlPanel.add(cancel, BorderLayout.CENTER);
			controlPanel.add(ok, BorderLayout.CENTER);
		}
	}

	private void revert() {
		config.set(defaultConf); // もとに戻す
	}

	@Override
	public void paint(Graphics g) {
		super.paint(g);
	}

	/**
	 * Windowがクローズされた場合
	 */
	class PropertiesDialogListener extends WindowAdapter {
		@Override
		public void windowClosing(WindowEvent event) {
			PropertiesDialog.this.revert(); // もとに戻す
			// Dialogを閉じる
			dispose();
		}
	}
}
