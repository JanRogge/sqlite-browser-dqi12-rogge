package de.szut.sqlite_browser.sql;

import java.awt.Rectangle;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Properties;

public class PropLoader {

	private static final String CONFIG_FILE_PATH = "config/config.ini";

	private Properties properties;
	private Rectangle windowDimension;
	private File file;
	private FileInputStream fileInputStream;

	public PropLoader() {
		properties = new Properties();
		file = new File(CONFIG_FILE_PATH);
		try {
			fileInputStream = new FileInputStream(file);
			properties.load(fileInputStream);
			windowDimension = new Rectangle();
			windowDimension.setBounds(
					Double.valueOf(properties.getProperty("position.x"))
							.intValue(),
					Double.valueOf(properties.getProperty("position.y"))
							.intValue(), Integer.parseInt(properties
							.getProperty("size.x")), Integer
							.parseInt(properties.getProperty("size.y")));

		} catch (IOException e) {
			windowDimension = new Rectangle();
			windowDimension.setBounds(100, 100, 868, 582);
		} catch (NullPointerException e) {
			windowDimension = new Rectangle();
			windowDimension.setBounds(100, 100, 868, 582);
		}
	}

	public Rectangle getWindowDimension() {
		return windowDimension;
	}

	public void setWindowDimension(Rectangle dimension) {
		windowDimension = dimension;
		properties.setProperty("position.x",
				String.valueOf(windowDimension.getX()));
		properties.setProperty("position.y",
				String.valueOf(windowDimension.getY()));
		properties.setProperty("size.x", String.valueOf(windowDimension.width));
		properties
				.setProperty("size.y", String.valueOf(windowDimension.height));
		try {
			properties.store(new FileOutputStream(file), null);
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	}
}
