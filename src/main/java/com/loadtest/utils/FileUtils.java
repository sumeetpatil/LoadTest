package com.loadtest.utils;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * File Utility
 * @author sumeetpatil
 *
 */
public class FileUtils implements UtilConstants{

	public static Properties getProperties(){
		Properties prop = new Properties();
		InputStream input = null;
		try {
			String path = FileUtils.class.getClassLoader().getResource(LOADTEST_CONF).getPath();
			input = new FileInputStream(path);
			prop.load(input);
		} catch (IOException ex) {
			ex.printStackTrace();
		} finally {
			if (input != null) {
				try {
					input.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		return prop;
	}
}
